package com.lonex.det.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonex.det.models.Machine;
import com.lonex.det.models.MachineCategory;

@Service
public class JsonFileReaderService {

	@Autowired
	private S3ServicesImpl S3Services;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public boolean addMachine(Machine machine , String category) {
		MachineCategory machineType = new MachineCategory();
		machineType.setImageName(machine.getImageName());
		machineType.setMachineName(machine.getMachineName());
		machineType.setMapName(machine.getMapName());
		
		List<MachineCategory> machineTypeList = this.getMachineTypesFromJson(category);
		List<Machine> machineList = this.getMachinesFromJson();
		
		try {
			System.out.println("Adding machine Json : \n " + objectMapper.writeValueAsString(machine));
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
			return false;
		}
		
		if(machineList != null && machineTypeList != null)
		{
			machineList.add(0 , machine);
			machineTypeList.add(0 , machineType);
		}
		else
		{
			System.out.println("machine List or machineType List is null");
			return false;
		}
		
		this.updateJsonFiles(machineTypeList, machineList, category);
		
		return true;
	}
	
	public boolean updateJsonFiles(List<MachineCategory> machineTypes , List<Machine> machineList , String category) {
		try {
				byte[] byteArray = objectMapper.writeValueAsBytes(machineList);
				ByteArrayInputStream byteStream = new ByteArrayInputStream(byteArray);
				
				S3Services.uploadDataFile("data/machines.json", byteStream);
				
				byte[] byteArrayTour = objectMapper.writeValueAsBytes(machineTypes);
				ByteArrayInputStream byteStreamTour = new ByteArrayInputStream(byteArrayTour);

				S3Services.uploadDataFile("data/" + category.toLowerCase() +".json", byteStreamTour);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
	}
	
	public List<MachineCategory> getMachineTypesFromJson (String machineType) {
		try {	
			List<MachineCategory> machines;
			//machines = objectMapper.readValue(new ClassPathResource("/data/"+ machineType.toLowerCase() +".json").getFile(), new TypeReference<List<MachineCategory>>(){});
			machines = objectMapper.readValue(S3Services.downloadFile("data/" + machineType.toLowerCase() +".json"), new TypeReference<List<MachineCategory>>(){});
			
			return machines;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<MachineCategory> getSearchMachineCategoryList(String category , String searchQuery){
			
			List<MachineCategory> allMachines = this.getMachineTypesFromJson(category);
			List<MachineCategory> searchResult = new ArrayList<MachineCategory> ();
			char[] searchQueryArray = searchQuery.toUpperCase().toCharArray();
			if(searchQueryArray.length == 0) return allMachines;
			
			for(MachineCategory machine : allMachines) {
				boolean allMatch = true;
				char[] mapNameArray = machine.getMapName().toUpperCase().toCharArray();
				int index = 0;
	
				for(char queryChar : searchQueryArray){
					if(queryChar != mapNameArray[index]){
						allMatch = false;
						break;
					}
				  index++;
				}
				if(allMatch) searchResult.add(machine);
			}
			return searchResult;
		}
	
	public List<MachineCategory> getSearchMachineCategoryList(String searchQuery){
		
		List<MachineCategory> allMachines = this.getFullMachineCategoryList();
		List<MachineCategory> searchResult = new ArrayList<MachineCategory> ();
		char[] searchQueryArray = searchQuery.toUpperCase().toCharArray();
		if(searchQueryArray.length == 0) return allMachines;
		
		for(MachineCategory machine : allMachines) {
			boolean allMatch = true;
			char[] mapNameArray = machine.getMapName().toUpperCase().toCharArray();
			int index = 0;

			for(char queryChar : searchQueryArray){
				if(queryChar != mapNameArray[index]){
					allMatch=false;
					break;
				}
			  index++;
			}
			if(allMatch) searchResult.add(machine);			
		}
		return searchResult;
	}
	
	public List<MachineCategory> getFullMachineCategoryList() {
		List<MachineCategory> centres;
		List<MachineCategory> machines;
		
		try {
			centres = objectMapper.readValue(S3Services.downloadFile("data/centre.json"), new TypeReference<List<MachineCategory>>(){});
			machines = objectMapper.readValue(S3Services.downloadFile("data/tour.json"), new TypeReference<List<MachineCategory>>(){});
			//centres = objectMapper.readValue(new ClassPathResource("/data/centre.json").getFile(), new TypeReference<List<MachineCategory>>(){});
			//machines = objectMapper.readValue(new ClassPathResource("/data/tour.json").getFile(), new TypeReference<List<MachineCategory>>(){});
		}
		catch(Exception e) {
			e.printStackTrace(); return null;
		}

		for(MachineCategory machine : centres)
			machines.add(machine);
		
		return machines;
	}
	
	public List<Machine> getMachinesFromJson() {
		try {
			return objectMapper.readValue(S3Services.downloadFile("data/machines.json"), new TypeReference<List<Machine>>(){});
			//return objectMapper.readValue(new ClassPathResource("/data/machines.json").getFile(), new TypeReference<List<Machine>>(){});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
