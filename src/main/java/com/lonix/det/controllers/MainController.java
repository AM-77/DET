package com.lonix.det.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lonex.enums.MachineType;
import com.lonex.services.JsonFileReaderService;
import com.lonix.det.models.Machines;

@Controller
public class MainController {

	@Autowired
	JsonFileReaderService jsonReader;
	
	Machines machines = new Machines();
	
	@RequestMapping("/hello")
	public String index(Model model){
		
		
		
		return "Index";

	}
	
	@RequestMapping("/Category/{category}")
	public ModelAndView cool(@PathVariable("category") String category){
		
		switch (category) {
		
		case "centre":
			jsonReader.getMachinesFromJson(MachineType.Centre);
			break;
		case "tour":
			jsonReader.getMachinesFromJson(MachineType.Tour);
			break;
			
			default :
				return new ModelAndView("error" , "msg" , "category undefined !");
			
		}
		
		return new ModelAndView("MachinesPage" , "Machines" , machines);

	}

}
