package com.alibou.example;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController( SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }


    @PostMapping("/schools")
    public SchoolDTO create( 
        @RequestBody SchoolDTO schoolDTO
    ){
        var school = toSchool(schoolDTO);
        schoolRepository.save(school);
        return schoolDTO;
    }

    private School toSchool( SchoolDTO schoolDTO){
        return new School(schoolDTO.name());
    }


    @GetMapping("/schools")
    public List<School> findAll( 
    ){
            return schoolRepository.findAll();
    }
}
