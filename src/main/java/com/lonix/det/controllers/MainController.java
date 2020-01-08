package com.lonix.det.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonix.det.models.Machines;
import java.io.File;

@RestController
public class MainController {

	@GetMapping("/")
	public String cool(){

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Machines machines = objectMapper.readValue(new File("src/main/resources/data/machines.json"), Machines.class);

			return "Data retrieved: there is " + machines.getMachines().size() + " machine.";
		} catch (Exception e) {
			e.printStackTrace();
			return "There was an error in the server.";
		}

	}

}
