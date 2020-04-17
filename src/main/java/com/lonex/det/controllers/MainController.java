package com.lonex.det.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lonex.det.models.Column;
import com.lonex.det.models.Feature;
import com.lonex.det.models.Machine;
import com.lonex.det.models.MachineCategory;
import com.lonex.det.services.JsonFileReaderService;
import com.lonex.det.services.S3ServicesImpl;

@Controller
public class MainController  implements ErrorController{
	@Autowired
	S3ServicesImpl s3service;
	@Autowired
	JsonFileReaderService jsonReader;
	List<MachineCategory> machineTypes;
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
	
	@ModelAttribute("machine")
	public Machine getMachine()
	{
		Machine machine = new Machine();
		machine.setColumns(new AutoPopulatingList<Column>(Column.class));
		return machine;
	}
	
	public boolean getMachineTypes(String category) {
		machineTypes= jsonReader.getMachineTypesFromJson(category);
		return true;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "loginForm";
    }
	
	@RequestMapping("/admin/addMachine")
	public ModelAndView addMachine()
	{
		ModelAndView model = new ModelAndView("addForm");
		model.addObject("machineList" , jsonReader.getMachinesFromJson());
		return model ;
	}
	
	@RequestMapping("/admin/addFeature")
	public ModelAndView insertFeature(@RequestParam Integer featCount , @RequestParam Integer colCount)
	{
		ModelAndView model = new ModelAndView("featureInsert");
		model.addObject("featCount",featCount);
		model.addObject("colCount",colCount);
		return model ;
	}
	
	@RequestMapping("/admin/addColumn")
	public ModelAndView insertColumn(@RequestParam Integer colCount)
	{
		ModelAndView model = new ModelAndView("columnInsert");
		model.addObject("colCount",colCount);
		return model ;
	}
	
	@RequestMapping(value="/admin/addMachine", method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView addMachine(@ModelAttribute("machine") Machine machine , @RequestParam String category , @RequestParam MultipartFile image)
	{
		ModelAndView model = new ModelAndView("addForm");
		model.addObject("machineList" , jsonReader.getMachinesFromJson());
		if(image.getSize() != 0l)
		{
			if(image.getSize() >= 5242880l )//5mb
			{
				model.addObject("error" , true);
				model.addObject("errorMsg" , "image size must be lower than 5MB");
				return model;
			}
		
			System.out.println("creating image : " + image.getOriginalFilename());
			
			boolean uploaded = false;
			try {
				uploaded = s3service.uploadFile("" + image.getOriginalFilename(), image.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
			if(!uploaded)
			{
				model.addObject( "error" , true );
				model.addObject("errorMsg" , "failed to upload image!");
				return model;
			}
		}
		else
		{
			model.addObject( "error" , true );
			model.addObject("errorMsg" , "no image attached.");
			return model;
		}

		machine.setImageName("elasticbeanstalk-us-east-2-667976969638.s3.us-east-2.amazonaws.com/vendors/product/upload/" + image.getOriginalFilename());
		machine.setMachineName(machine.getMapName());
		
		boolean success = false;
		success = jsonReader.addMachine(machine , category);
		
		if(success)
		{
			System.out.println("category = " + category);
			System.out.println("machine name = " + machine.getMapName());
			System.out.println("image name = " + machine.getImageName());
			for(Column c : machine.getColumns()) {
				System.out.println("Column : " + c.getTitle());
				for(Feature f : c.getFeatures())
					System.out.println("Feature: " + f.getFeature() + " value: " + f.getValue());
			}
			
			//updateMachines to get the new addedMachine
			model.addObject("machineList" , jsonReader.getMachinesFromJson());
			model.addObject("success" , success);
			return model ;
		}
		else
		{
			model.addObject( "error" , true );
			model.addObject("errorMsg" , "couldn't write machine data in Json File");
			return model;
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
