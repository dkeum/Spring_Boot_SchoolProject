package com.alibou.example;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; 

public interface StudentRespository extends JpaRepository<Student, Integer>  {
    
    List<Student> findAllByfirstNameContaining(String firstName);
}
