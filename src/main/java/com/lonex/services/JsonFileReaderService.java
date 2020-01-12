package com.lonex.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonex.enums.MachineType;
import com.lonix.det.models.Machine;
import com.lonix.det.models.MachineCategory;

@Service
public class JsonFileReaderService {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	public List<MachineCategory> getMachineTypesFromJson (MachineType machineType) {
		try {
		
			List<MachineCategory> machines ;
			
			switch ( machineType) {
			case  Centre :
				 machines = objectMapper.readValue(new File("src/main/resources/data/centre.json"), new TypeReference<List<MachineCategory>>(){});
				break;
			case Tour :
				 machines = objectMapper.readValue(new File("src/main/resources/data/tour.json"), new TypeReference<List<MachineCategory>>(){});
				break;
			default :
				machines=null;
			}
			

			return machines;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( "There was an error in the server." );
			return null;
		}
	}
	public List<MachineCategory> getSearchMachineCategoryList(String searchQuery){
		
		List<MachineCategory> allMachines = this.getFullMachineCategoryList();
		List<MachineCategory> searchResult = new ArrayList<MachineCategory> ();
		
		char[] searchQueryArray = searchQuery.toUpperCase().toCharArray();
		
		
		
		for(MachineCategory machine : allMachines) {
			boolean allMatch = true;
			char[] mapNameArray = machine.getMapName().toUpperCase().toCharArray();
			int index = 0;

			for(char queryChar : searchQueryArray)
			{
			
				if(queryChar != mapNameArray[index])
				{
					allMatch=false;
					break;
				}
			
			  index++;
			}
			
			if(allMatch)
			{
				System.out.println(machine.getMapName() + " valide !");
				searchResult.add(machine);
			}
			if(searchQueryArray.length==0)
				return allMachines;
			
		}
		
		return searchResult;
	}
	
	
	public List<MachineCategory> getFullMachineCategoryList() {
		List<MachineCategory> centres;
		List<MachineCategory> machines;
		
		try {
				centres = objectMapper.readValue(new File("src/main/resources/data/centre.json"), new TypeReference<List<MachineCategory>>(){});
				machines = objectMapper.readValue(new File("src/main/resources/data/tour.json"), new TypeReference<List<MachineCategory>>(){});
			}catch(Exception e) {e.printStackTrace(); return null;}
		
		for(MachineCategory machine : centres)
		{
			machines.add(machine);
		}
		
		return machines;
	}
	

	public List<Machine> getMachinesFromJson() {
		// TODO Auto-generated method stub
		try {
			return objectMapper.readValue(new File("src/main/resources/data/machines.json"), new TypeReference<List<Machine>>(){});
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( "There was an error in the server." );
			return null;
		}
	}
}
