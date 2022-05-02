package com.example.demo.helpers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KNN {
    private final long status;
    private final Recommendation[] recommendations;

    public KNN(long status, String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        this.status = status;
        this.recommendations = mapper.readValue(content, Recommendation[].class);
    }

    public long getStatus() {
        return status;
    }

    public Recommendation[] getRecommendations() {
        return recommendations;
    }
}
