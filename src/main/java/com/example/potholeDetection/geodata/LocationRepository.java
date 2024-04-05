package com.example.potholeDetection.geodata;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.ListCrudRepository;


@Repository
public interface LocationRepository extends ListCrudRepository<Location, Long>{

    Location findByLatitudeAndLongitude(double latitude, double longitude);
}
