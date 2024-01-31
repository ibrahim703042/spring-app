package com.kwizera.ibrahim.adminPanel.controller;

import com.kwizera.ibrahim.adminPanel.dto.requestDto.ZipcodeRequestDto;
import com.kwizera.ibrahim.adminPanel.entity.Zipcode;
import com.kwizera.ibrahim.adminPanel.service.ZipcodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/zipcode")
public class ZipcodeController {

    private final ZipcodeService zipcodeService;

    //@Autowired
    public ZipcodeController(ZipcodeService zipcodeService) {
        this.zipcodeService = zipcodeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Zipcode> addZipcode(@RequestBody final ZipcodeRequestDto zipcodeRequestDto){
        Zipcode zipcode = zipcodeService.addZipcode(zipcodeRequestDto);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Zipcode> getZipcodeId(@PathVariable final long id){
        Zipcode zipcode = zipcodeService.getZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Zipcode>> getZipcode(){
        List<Zipcode> zipcodes = zipcodeService.getZipcodes();
        return new ResponseEntity<List<Zipcode>>(zipcodes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Zipcode> deleteZipcode(@PathVariable final  long id){
        Zipcode zipcode = zipcodeService.deleteZipcode(id);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Zipcode> editZipcode(@PathVariable final long id, @RequestBody final ZipcodeRequestDto zipcodeRequestDto){
        Zipcode zipcode = zipcodeService.editZipcode(id,zipcodeRequestDto);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @PostMapping("/addCity/{cityId}/to/{zipcodeId}")
    public ResponseEntity<Zipcode> addCity(@PathVariable final long cityId, @PathVariable final long zipcodeId) {
        Zipcode zipcode = zipcodeService.addCityToZipcode(cityId, zipcodeId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @PostMapping("/removeCity/from/{zipcodeId}")
    public ResponseEntity<Zipcode> removeCity(@PathVariable final long zipcodeId) {
        Zipcode zipcode = zipcodeService.removeCityFromZipcode(zipcodeId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }
}
