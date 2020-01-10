package com.lonex.services;

import java.io.File;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonex.enums.MachineType;
import com.lonix.det.models.Machines;

@Service
public class JsonFileReaderService {

	
	
	public Machines getMachinesFromJson (MachineType machineType) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Machines machines = new Machines();
			
			switch ( machineType) {
			case  Centre :
			
				 machines = objectMapper.readValue(new File("src/main/resources/data/centre.json"), Machines.class);
				break;
			case Tour :
				 machines = objectMapper.readValue(new File("src/main/resources/data/tour.json"), Machines.class);
				
			
			}
			

			return machines;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( "There was an error in the server." );
			return null;
		}
	}
}
