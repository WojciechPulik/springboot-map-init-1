package pl.wpulik.map.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.map.model.Point;

@Service
public class Covid19DataJoin {
	
	private Covid19Parser covid19Parser;
	private List<Point> confirmed;
	private List<Point> deaths;
	private List<Point> recovered;
	
	@Autowired
	public Covid19DataJoin(Covid19Parser covid19Parser) {
		this.covid19Parser = covid19Parser;
	}


	public Covid19DataJoin() {
		this.confirmed = new ArrayList<>();
		this.deaths = new ArrayList<>();
		this.recovered = new ArrayList<>();
	}
	
	public List<Point> finalList() {
		confirmed = covid19Parser.confirmed();
		deaths = covid19Parser.deaths();
		recovered = covid19Parser.recovered();
		List<Point> resultList = new ArrayList<>();
		
		for(int i = 0; i < confirmed.size(); i++) {
			resultList.add(new Point(confirmed.get(i).getLat(), confirmed.get(i).getLon(),
					("Confirmed: " + confirmed.get(i).getText() + 
							" Deaths: " + deaths.get(i).getText() +  
							" Recovery: " + recovered.get(i).getText())));
		}
		return resultList;
	}
	
	
	

}
