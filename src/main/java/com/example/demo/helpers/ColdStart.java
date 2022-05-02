package com.example.demo.helpers;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ColdStart {
    private final long status;
    private final Movie[] movies;

    public ColdStart(long id, String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        this.status = id;
        this.movies = mapper.readValue(content, Movie[].class);
    }

    public long getStatus() {
        return status;
    }

    public Movie[] getMovies() {
        return movies;
    }
}