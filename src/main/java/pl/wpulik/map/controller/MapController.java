package pl.wpulik.map.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.wpulik.map.service.Covid19Parser;
import pl.wpulik.map.service.Covid19Service;

@Controller
public class MapController {
	
	private Covid19Service covid19Service;
	private Covid19Parser covid19Parser;
	
	public MapController(Covid19Service covid19Service) {
		this.covid19Service = covid19Service;
	}

	@GetMapping("/")
	public String getMap(Model model) {
		covid19Service.addAllData();
		
		model.addAttribute("points", covid19Service.getAllPoints());
		return "map";
	}

}
