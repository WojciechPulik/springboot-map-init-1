package pl.wpulik.map.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.wpulik.map.model.Point;


@Repository
public interface PointRepository extends CrudRepository<Point, Long>{

}
