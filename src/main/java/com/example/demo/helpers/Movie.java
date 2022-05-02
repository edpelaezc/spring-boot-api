package com.example.demo.helpers;

public class Movie {
    public String movie_title;
    public String imdb_score;
    public String num_user_for_reviews;
    public String score;

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getImdb_score() {
        return imdb_score;
    }

    public void setImdb_score(String imdb_score) {
        this.imdb_score = imdb_score;
    }

    public String getNum_user_for_reviews() {
        return num_user_for_reviews;
    }

    public void setNum_user_for_reviews(String num_user_for_reviews) {
        this.num_user_for_reviews = num_user_for_reviews;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
