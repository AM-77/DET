package com.lonex.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.lonex.enums.MachineType;
import com.lonix.det.models.Machine;
import com.lonix.det.models.MachineCategory;
import org.springframework.core.io.ClassPathResource;

@Service
public class JsonFileReaderService {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	public List<MachineCategory> getMachineTypesFromJson (MachineType machineType) {
		try {	
			List<MachineCategory> machines;
			
			switch ( machineType) {
				case  Centre :
					machines = objectMapper.readValue(new ClassPathResource("/data/centre.json").getFile(), new TypeReference<List<MachineCategory>>(){});
					break;
				case Tour :
					machines = objectMapper.readValue(new ClassPathResource("/data/tour.json").getFile(), new TypeReference<List<MachineCategory>>(){});
					break;
				default:
					machines=null;
			}
		
			return machines;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<MachineCategory> getSearchMachineCategoryList(MachineType category , String searchQuery){
			
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
				centres = objectMapper.readValue(new ClassPathResource("/data/centre.json").getFile(), new TypeReference<List<MachineCategory>>(){});
				machines = objectMapper.readValue(new ClassPathResource("/data/tour.json").getFile(), new TypeReference<List<MachineCategory>>(){});
			}catch(Exception e) {e.printStackTrace(); return null;}
		
		for(MachineCategory machine : centres)
		{
			machines.add(machine);
		}
		
		return machines;
	}
	

	public List<Machine> getMachinesFromJson() {
		try {
			return objectMapper.readValue(new ClassPathResource("/data/machines.json").getFile(), new TypeReference<List<Machine>>(){});
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( "failed to read Json file" );
			return null;
		}
	}
	
}
