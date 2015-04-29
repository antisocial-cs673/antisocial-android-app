package com.antisocial.app.webapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Igibek on 4/24/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class UserDataResponse {
    @JsonProperty
    private int Score;
    @JsonProperty
    private int TotalBlockedTime;
    @JsonProperty
    private int Rank;
    public void setScore(int score) {
        Score = score;
    }

    public void setTotalBlockedTime(int totalBlockedTime) {
        TotalBlockedTime = totalBlockedTime;
    }

    public int getScore() {
        return Score;
    }

    public int getTotalBlockedTime() {
        return TotalBlockedTime;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public int getRank() {
        return Rank;
    }
}
