package pl.wpulik.map.service;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
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
	
	
	public String getDate() {
		Date date = DateUtils.addDays(new Date(), -1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yy");
		return dateFormat.format(date);
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
		List <Point> resultList = getCovidData(URL_CONFIRMED, getDate());
		return resultList;
	}
	public List<Point> deaths( ){
		List <Point> resultList = getCovidData(URL_DEATHS, getDate());
		return resultList;
	}
	public List<Point> recovered( ){
		List <Point> resultList = getCovidData(URL_RECOVERED, getDate());
		return resultList;
	}
	

}
