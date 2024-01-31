package com.kwizera.ibrahim.adminPanel.service;

import com.kwizera.ibrahim.adminPanel.dto.requestDto.CityRequestDto;
import com.kwizera.ibrahim.adminPanel.entity.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    public City addCity(CityRequestDto cityRequestDto);
    public List<City> getCities();
    public City getCity(Long cityID);
    public City editCity(Long cityID, CityRequestDto cityRequestDto);
    public City deleteCity(Long cityID);
}
