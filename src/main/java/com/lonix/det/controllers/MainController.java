package com.lonix.det.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lonex.enums.MachineType;
import com.lonex.services.JsonFileReaderService;
import com.lonix.det.models.ClientAction;
import com.lonix.det.models.Machine;
import com.lonix.det.models.MachineCategory;

@Controller
public class MainController {

	@Autowired
	JsonFileReaderService jsonReader;
	
	List<MachineCategory> machineTypes;
	List<Machine> machines;
	
	ClientAction clientAction ;
	
	@RequestMapping("/")
	public String index(Model model){ return "Index"; }
	
	@RequestMapping("/contact")
	public String contact(Model model){ return "contact"; }

	@RequestMapping("/Category/{category}")
	public ModelAndView cool(HttpServletRequest request , @PathVariable("category") String category){
		
		
		Thread writeClientThread = new Thread(new ClientWriterThread(request,category,jsonReader));
	
		writeClientThread.start();
		
		
		if(this.getMachineTypes(category))
			return new ModelAndView("MachinesPage" , "MachineTypes" , machineTypes);
		else
		{
			System.out.println("warning no such category!");
			return new ModelAndView("MachinesPage");
		}
		

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
	
	class ClientWriterThread extends Thread{
		
		JsonFileReaderService jsonReader;
		
		HttpServletRequest request;
		String category;
		
		
		public ClientWriterThread(HttpServletRequest request ,String category , JsonFileReaderService jsonReader) {
			this.category=category;
			this.request=request;
			this.jsonReader=jsonReader;
		}
		
		@Override
		public void run() {
			jsonReader.WriteClientAction(this.request, "visited category : "+this.category);
		}
	}



	/*
	@RequestMapping("/Category/{category}/{mapName}")
	public ModelAndView getMachine(@PathVariable("category") String category , @PathVariable("mapName") String mapName)
	{
		ModelAndView model = new ModelAndView("SingleMachinePage");
		
		if(this.getMachineTypes(category))
		{
				Machine machine = this.getSingleMachine(category, mapName);
				if(machine==null)
					model.addObject("machineFound" , false);
				else
				{
					model.addObject("Machine" , machine);
					model.addObject("machineFound" , true);
					}
					
					
					return model;
		}
		else
				return new ModelAndView("MachinesPage" , "msg" , "No such machine category !");
			
	
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
	
	*/
	
	 
}
