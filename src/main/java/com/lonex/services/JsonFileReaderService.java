package com.lonex.services;

import java.io.File;
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
