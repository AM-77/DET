package com.lonix.det.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lonex.enums.MachineType;
import com.lonex.services.JsonFileReaderService;
import com.lonix.det.models.Machine;
import com.lonix.det.models.MachineCategory;

@RestController
public class RstController {
	@Autowired
	JsonFileReaderService jsonReader;
	List<MachineCategory> machineTypes;
	List<Machine> machines;
	

	@RequestMapping("/Search")
	public List<MachineCategory> getSearch(@RequestParam(defaultValue="") String searchQuery ) {
		return this.jsonReader.getSearchMachineCategoryList(searchQuery);
	}
	
	@RequestMapping("/Category/{category}/{mapName}")
	public Machine getMachine(@PathVariable("category") String category , @PathVariable("mapName") String mapName)
	{
		
		
		if(this.getMachineTypes(category))
		{
				Machine machine = this.getSingleMachine(category, mapName);
				return machine;}
		else
				return null;
			
	
		}
	
	@RequestMapping("/Search/{category}")
	public List<MachineCategory> cool(HttpServletRequest request , @PathVariable("category") String category,@RequestParam(defaultValue="") String searchQuery){
		
		switch (category) {
		
		case "centre":
			 machineTypes= jsonReader.getSearchMachineCategoryList(MachineType.Centre , searchQuery);
			 return machineTypes;
		case "tour":
			 machineTypes= jsonReader.getSearchMachineCategoryList(MachineType.Tour , searchQuery);		
			return machineTypes;
			
			default :{
				System.out.println("warning no such category!");
				return new ArrayList<MachineCategory>();
			}
	
}
		

	}
	
	public Machine getSingleMachine(String category , String mapName) {
		
		Machine machine = new Machine();
		boolean machineFound=false;
		
		machines = jsonReader.getMachinesFromJson();
	
		for(Machine stockMachine : machines)
		{
			if(stockMachine.getMapName().equals( mapName )) {
				machineFound=true;
				machine=stockMachine;
				break;
			}
		}
		
		for(MachineCategory mt : machineTypes)
		{
			if(mt.getMapName().equals(mapName)) 
			{
				machine.setImageName(mt.getImageName());
				break;
			}
			
			
		}
		
		if(machineFound)
			return machine;
		else
			return null;
	}

	
	

	
	public boolean getMachineTypes(String category) {

		switch (category) {
				
				case "centre":
					 machineTypes= jsonReader.getMachineTypesFromJson(MachineType.Centre);
					 return true;
				case "tour":
					 machineTypes= jsonReader.getMachineTypesFromJson(MachineType.Tour);		
					return true;
					
					default :{
						System.out.println("warning no such category!");
						return false;
					}
			
		}
	}
}
