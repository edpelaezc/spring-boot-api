package com.example.demo.restservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.example.demo.helpers.ColdStart;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColdStartController {

    @CrossOrigin
    @GetMapping("/coldstart")
    public ColdStart coldStart(@RequestParam(value = "name", defaultValue = "World") String name)
            throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("python3",
                System.getProperty("user.dir") + "/src/main/resources/simple_recomender.py");
        // System.out.println(System.getProperty("user.dir"));
        // ProcessBuilder pb = new
        // ProcessBuilder("python3","/home/edroot/scripts/simple_recomender.py");
        Process p = pb.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String movies = in.lines().collect(Collectors.joining());
        return new ColdStart(200, movies);
    }
}