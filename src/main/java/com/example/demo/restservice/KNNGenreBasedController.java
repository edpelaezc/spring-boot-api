package com.example.demo.restservice;

import com.example.demo.helpers.KNN;
import com.example.demo.helpers.KNNGenreBased;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
public class KNNGenreBasedController {
    @PostMapping("/knnGenreBased")
    public KNNGenreBased knnGenreBased(@RequestBody GenresForm genres) throws IOException, InterruptedException {
        // ProcessBuilder pb = new ProcessBuilder("python3","/home/edroot/scripts/knn.py", movie.getMovie_title());
        ProcessBuilder pb = new ProcessBuilder(
                "python3",
                "/Users/eduardopelaez/Downloads/demo/src/main/resources/knn_genre_based.py",
                String.join(",", genres.getGenres())
        );

        Process p = pb.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String input =  in.lines().collect(Collectors.joining());
        return new KNNGenreBased(200, input);
    }
}
