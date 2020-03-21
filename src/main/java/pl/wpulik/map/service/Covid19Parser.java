package pl.wpulik.map.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.wpulik.map.model.Point;

@Service
public class Covid19Parser {
	
	private static final String URL_CONFIRMED= "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/"
			+ "csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	private static final String URL_DEATHS= "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/"
			+ "csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Deaths.csv";
	private static final String URL_RECOVERED= "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/"
			+ "csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Recovered.csv";
	
	private String date;
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	
	private List<Point> getCovidData(String url, String date) {
		List<Point> points = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		String values = restTemplate.getForObject(url, String.class);
		
		StringReader stringReader = new StringReader(values);
		try {
		CSVParser parse = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);
		for(CSVRecord strings: parse) {
			double lat = Double.parseDouble(strings.get("Lat"));
			double lon = Double.parseDouble(strings.get("Long"));
			String text = strings.get(date);
			points.add(new Point(lat, lon, text));
		}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return points;
	}
	
	public List<Point> confirmed( ){
		List <Point> resultList = getCovidData(URL_CONFIRMED, "3/20/20");
		return resultList;
	}
	public List<Point> deaths( ){
		List <Point> resultList = getCovidData(URL_DEATHS, "3/20/20");
		return resultList;
	}
	public List<Point> recovered( ){
		List <Point> resultList = getCovidData(URL_RECOVERED, "3/20/20");
		return resultList;
	}
	

}
