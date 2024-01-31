package com.kwizera.ibrahim.adminPanel.service.impl;

import com.kwizera.ibrahim.adminPanel.dto.requestDto.CityRequestDto;
import com.kwizera.ibrahim.adminPanel.entity.City;
import com.kwizera.ibrahim.adminPanel.repository.CityRepository;
import com.kwizera.ibrahim.adminPanel.service.CityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    //@Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City addCity(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.getName());
        return cityRepository.save(city);
    }

    @Override
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        cityRepository.findAll().forEach(cities::add);
        return cities;
    }

    @Override
    public City getCity(Long cityID) {
        return cityRepository.findById(cityID).orElseThrow(()
                ->new IllegalArgumentException("City with cityId: " + cityID + " could not be found"));
    }

    @Transactional
    @Override
    public City editCity(Long cityID, CityRequestDto cityRequestDto) {
        City city = getCity(cityID);
        city.setName(cityRequestDto.getName());
        return city;
    }

    @Override
    public City deleteCity(Long cityID) {
        City city = getCity(cityID);
        cityRepository.delete(city);
        return city;
    }

}
