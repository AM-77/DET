package com.lonex.det.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lonex.det.models.Machine;
import com.lonex.det.models.MachineCategory;
import com.lonex.det.services.JsonFileReaderService;

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
	public Machine getMachine(@PathVariable("category") String category , @PathVariable("mapName") String mapName){
		if(this.getMachineTypes(category)) return this.getSingleMachine(category, mapName);
		else return null;
	}
	
	@RequestMapping("/Search/{category}")
	public List<MachineCategory> cool( @PathVariable("category") String category,@RequestParam(defaultValue="") String searchQuery){
		machineTypes= jsonReader.getSearchMachineCategoryList(category , searchQuery);	
		return machineTypes;
	}
	
	@RequestMapping("/admin/deleteMachine")
	public boolean removeMachine(@RequestParam(defaultValue="") String mapName) {
		boolean removed=false;
		machines = jsonReader.getMachinesFromJson();
		
		for(Machine listMachine : machines)
			if(listMachine.getMapName().equals(mapName))
			{
				machines.remove(listMachine);
				removed = true;
				break;
			}
		if(!removed)
			return false;
			
		removed = false;
		
		machineTypes= jsonReader.getMachineTypesFromJson("centre");
		for(MachineCategory listMachine : machineTypes)
			if(listMachine.getMapName().equals(mapName))
			{
				System.out.println("found mapname in categ centre! " + mapName);
				machineTypes.remove(listMachine);
				removed = true;
				break;
			}
		if(removed)
		{
			return jsonReader.updateJsonFiles(machineTypes, machines, "centre");
		}
		
		machineTypes= jsonReader.getMachineTypesFromJson("tour");
		for(MachineCategory listMachine : machineTypes)
			if(listMachine.getMapName().equals(mapName))
			{
				System.out.println("found mapname in categ tour! " + mapName);
				machineTypes.remove(listMachine);
				removed = true;
				break;
			}
		
		if(removed)
		{
			return jsonReader.updateJsonFiles(machineTypes, machines, "tour");
		}
		
		return false;
	}
	
	public Machine getSingleMachine(String category , String mapName) {
		
		Machine machine = new Machine();
		boolean machineFound=false;
		machines = jsonReader.getMachinesFromJson();
	
		for(Machine stockMachine : machines)
			if(stockMachine.getMapName().equals( mapName )) {
				machineFound=true;
				machine=stockMachine;
				break;
			}

		for(MachineCategory mt : machineTypes)
			if(mt.getMapName().equals(mapName)) {
				machine.setImageName(mt.getImageName());
				break;
			}
			
		if(machineFound) return machine;
		else return null;
	}

	public boolean getMachineTypes(String category) {
		machineTypes= jsonReader.getMachineTypesFromJson(category);
		return true;
	}

}
