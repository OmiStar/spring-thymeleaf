package com.coolcoder.spring.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coolcoder.spring.location.entities.Location;
import com.coolcoder.spring.location.service.LocationService;

@Controller
public class LocationController {
	
	@Autowired
	private LocationService service;
	
	@RequestMapping("/show")
	public String showCreate() {
		return "createLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		Location saveLocation = service.saveLocation(location);
		String msg="Location Saved with Id : "+ saveLocation.getId();
		modelMap.addAttribute("msg",msg);
		
		return "createLocation";
	}
	
	@RequestMapping("/displyLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations=service.getAllLocation();
		modelMap.addAttribute("locations",locations);
		return "displyLocations";
	}
	
	@RequestMapping("/delete")
	public String deleteLocation(@RequestParam("id") int id , ModelMap map) {
		//Location location =service.getLocatioById(id);
		Location location=new Location();
		location.setId(id);
		service.deleteLocation(location);
		List<Location> locations=service.getAllLocation();
		map.addAttribute("locations",locations);
		return "displyLocations";
	}
	
	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id") int id,ModelMap map) {
		Location locations=service.getLocatioById(id);
		map.addAttribute("location", locations);
		return "updateLocation";
	}
	
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		service.updateLocation(location);
		List<Location> locations=service.getAllLocation();
		modelMap.addAttribute("locations",locations);
		return "displyLocations";
	}

}
