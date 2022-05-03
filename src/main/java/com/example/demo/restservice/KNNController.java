package com.example.demo.restservice;

import com.example.demo.helpers.ColdStart;
import com.example.demo.helpers.KNN;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
public class KNNController {

    @CrossOrigin
    @PostMapping("/recommendations")
    public KNN knn(@RequestBody MovieForm movie) throws IOException, InterruptedException {
        // ProcessBuilder pb = new
        // ProcessBuilder("python3","/home/edroot/scripts/knn.py",
        // movie.getMovie_title());
        ProcessBuilder pb = new ProcessBuilder("python3", System.getProperty("user.dir") + "/src/main/resources/knn.py",
                movie.getMovie_title());
        Process p = pb.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String input = in.lines().collect(Collectors.joining());
        return new KNN(200, input);
    }
}
