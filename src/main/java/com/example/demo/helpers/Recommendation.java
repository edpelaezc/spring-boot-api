package com.example.demo.helpers;

public class Recommendation {
    public String movie_title;
    public String plot_keywords;
    public String imdb_score;

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getPlot_keywords() {
        return plot_keywords;
    }

    public void setPlot_keywords(String plot_keywords) {
        this.plot_keywords = plot_keywords;
    }

    public String getImdb_score() {
        return imdb_score;
    }

    public void setImdb_score(String imdb_score) {
        this.imdb_score = imdb_score;
    }
}
