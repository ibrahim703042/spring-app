package com.kwizera.ibrahim.adminPanel.service.impl;

import com.kwizera.ibrahim.adminPanel.dto.requestDto.ZipcodeRequestDto;
import com.kwizera.ibrahim.adminPanel.entity.City;
import com.kwizera.ibrahim.adminPanel.entity.Zipcode;
import com.kwizera.ibrahim.adminPanel.repository.ZipcodeRepository;
import com.kwizera.ibrahim.adminPanel.service.CityService;
import com.kwizera.ibrahim.adminPanel.service.ZipcodeService;
import jakarta.transaction.Transactional;
import lombok.NonNull;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ZipcodeServiceImpl implements ZipcodeService {
    private final ZipcodeRepository zipcodeRepository;
    private final CityService cityService;

    //@Autowired
    public ZipcodeServiceImpl(ZipcodeRepository zipcodeRepository, CityService cityService) {
        this.zipcodeRepository = zipcodeRepository;
        this.cityService = cityService;
    }

    @Transactional
    @Override
    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcode = new Zipcode();
        zipcode.setName(zipcodeRequestDto.getName());
        if(zipcodeRequestDto.getCityId() == Long.parseLong(null)){
            return zipcodeRepository.save(zipcode);
        }
        City city = cityService.getCity(zipcodeRequestDto.getCityId());
        zipcode.setCity(city);
        return zipcodeRepository.save(zipcode);
    }

    @Override
    public List<Zipcode> getZipcodes() {
        return StreamSupport
                .stream(zipcodeRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    @NonNull
    @Override
    public Zipcode getZipcode(@NonNull Long zipcodeID) {
        return zipcodeRepository.findById(zipcodeID).orElseThrow(()
                ->new IllegalArgumentException("Zipcode with zipcodeID: " + zipcodeID + " could not be found"));
    }

    @Override
    public Zipcode editZipcode(Long zipcodeID, ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcode = getZipcode(zipcodeID);
        zipcode.setName(zipcodeRequestDto.getName());
        if(zipcodeRequestDto.getCityId() != Long.parseLong(null)){
            return zipcode;
        }
        City city = cityService.getCity(zipcodeRequestDto.getCityId());
        zipcode.setCity(city);
        return zipcode;
    }

    @Override
    public Zipcode deleteZipcode(Long zipcodeID) {
        Zipcode zipcode = getZipcode(zipcodeID);
        zipcodeRepository.delete(zipcode);
        return zipcode;
    }

    @Transactional
    @Override
    public Zipcode addCityToZipcode(Long zipcodeID, Long cityID) {
        Zipcode zipcode = getZipcode(zipcodeID);
        City city = cityService.getCity(cityID);
        if (Objects.nonNull(zipcode.getCity())){
            throw new IllegalArgumentException("zipcode already has a city");
        }
        zipcode.setCity(city);
        return zipcode;
    }

    @Transactional
    @Override
    public Zipcode removeCityFromZipcode(Long zipcodeID) {
        Zipcode zipcode = getZipcode(zipcodeID);
        if (Objects.nonNull(zipcode.getCity())){
            throw new IllegalArgumentException("zipcode does not have a city");
        }
        zipcode.setCity(null);
        return zipcode;
    }
}
