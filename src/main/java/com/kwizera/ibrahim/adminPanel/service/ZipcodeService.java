package com.kwizera.ibrahim.adminPanel.service;

import com.kwizera.ibrahim.adminPanel.dto.requestDto.ZipcodeRequestDto;
import com.kwizera.ibrahim.adminPanel.entity.Zipcode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ZipcodeService {
    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto);
    public List<Zipcode> getZipcodes();
    public Zipcode getZipcode(Long zipcodeID);
    public Zipcode editZipcode(Long zipcodeID, ZipcodeRequestDto zipcodeRequestDto);
    public Zipcode deleteZipcode(Long zipcodeID);
    public Zipcode addCityToZipcode(Long zipcodeID, Long cityID);

    public Zipcode removeCityFromZipcode(Long zipcodeID);
}
