package com.lonix.det.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lonex.services.JsonFileReaderService;
import com.lonix.det.models.MachineCategory;

@RestController
public class RstController {
	@Autowired
	JsonFileReaderService jsonReader;
	List<MachineCategory> machineTypes;
	

	@RequestMapping("/Search")
	public List<MachineCategory> getSearch(@RequestParam(defaultValue="") String searchQuery) {
		return this.jsonReader.getSearchMachineCategoryList(searchQuery);
	}
}
