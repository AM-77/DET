package com.lonix.det.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
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
public class MainController  implements ErrorController{
	
	@Autowired
	JsonFileReaderService jsonReader;
	List<MachineCategory> machineTypes;
	List<Machine> machines;
	
	@RequestMapping("/")
	public String index(Model model){ return "Index"; }
	
	@RequestMapping("/contact")
	public String contact(Model model){ return "contact"; }

	@RequestMapping("/Category/{category}")
	public ModelAndView category(HttpServletRequest request , @PathVariable("category") String category){
		if(this.getMachineTypes(category))
			return new ModelAndView("MachinesPage" , "MachineTypes" , machineTypes);
		else
			return new ModelAndView("MachinesPage");
	}
	
	public boolean getMachineTypes(String category) {
		switch (category) {
			case "centre":
					machineTypes= jsonReader.getMachineTypesFromJson(MachineType.Centre);
					return true;
			case "tour":
					machineTypes= jsonReader.getMachineTypesFromJson(MachineType.Tour);		
				return true;
			default :
				return false;
		}
	}

	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request) {
	     return new ModelAndView ("error");
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
}
