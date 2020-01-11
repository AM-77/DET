package com.lonix.det.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lonex.enums.MachineType;
import com.lonex.services.JsonFileReaderService;
import com.lonix.det.models.Machine;
import com.lonix.det.models.MachineCategory;

@Controller
public class MainController {

	@Autowired
	JsonFileReaderService jsonReader;
	
	List<MachineCategory> machineTypes;
	List<Machine> machines;
	
	@RequestMapping("/")
	public String index(Model model){
		return "Index";

	}
	
	@RequestMapping("/Category/{category}")
	public ModelAndView cool(@PathVariable("category") String category){
		
		switch (category) {
		
		case "centre":
			 machineTypes= jsonReader.getMachineTypesFromJson(MachineType.Centre);
			break;
		case "tour":
			 machineTypes= jsonReader.getMachineTypesFromJson(MachineType.Tour);		
			break;
			
			default :{
				System.out.println("warning no such category!");
				return new ModelAndView("MachinesPage");
			}
				
		}
		
		return new ModelAndView("MachinesPage" , "MachineTypes" , machineTypes);

	}
	
	@RequestMapping("/Category/{category}/{mapName}")
	public ModelAndView getMachine(@PathVariable("category") String category , @PathVariable("mapName") String mapName)
	{
		ModelAndView model = new ModelAndView("SingleMachinePage");
		
		switch (category) {
		
		case "centre":
			 machineTypes= jsonReader.getMachineTypesFromJson(MachineType.Centre);
			break;
		case "tour":
			 machineTypes= jsonReader.getMachineTypesFromJson(MachineType.Tour);		
			break;
			
			default :{
				System.out.println("warning no such category!");
				return new ModelAndView("MachinesPage" , "msg" , "No such machine category !");
			}
			
		}
			
			Machine machine = new Machine();
			
			machines = jsonReader.getMachinesFromJson();
		
			for(Machine stockMachine : machines)
			{
				if(stockMachine.getMapName().equals( mapName )) {
					
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
			 
			model.addObject("Machine" , machine);
			
			return model;
	
	}
	

}
