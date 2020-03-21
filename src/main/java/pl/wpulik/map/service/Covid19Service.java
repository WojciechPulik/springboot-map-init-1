package pl.wpulik.map.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.map.model.Point;
import pl.wpulik.map.repository.PointRepository;

@Service
public class Covid19Service {
	
	private PointRepository pointRepository;
	private Covid19DataJoin covid19DataJoin;
	
	@Autowired
	public Covid19Service(PointRepository pointRepository, Covid19DataJoin covid19DataJoin) {
		this.pointRepository = pointRepository;
		this.covid19DataJoin = covid19DataJoin;
	}
	
	public void addAllData() {
		pointRepository.saveAll(covid19DataJoin.finalList());
	}
	
	public List<Point> getAllPoints(){
		List<Point> resultList = new ArrayList<>();
		resultList = (List<Point>) pointRepository.findAll();
		return resultList;
	}
	
	
	

}
