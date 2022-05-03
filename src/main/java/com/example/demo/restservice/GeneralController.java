package com.example.demo.restservice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import static com.example.demo.helpers.Queries.getAllGenres;


@RestController
public class GeneralController {

    @CrossOrigin
    @GetMapping("/genres")
    public GenresForm getGenres() {
        var response = new GenresForm();
        response.setGenres(getAllGenres());
        return response;
    }
}
