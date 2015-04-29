package com.antisocial.app.webapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Igibek on 4/23/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDataRequest implements Serializable {
    @JsonProperty
    private int AppCount;

    @JsonProperty
    private int Minutes;

    public UserDataRequest(int appCount, int minutes){
        this.AppCount = appCount;
        this.Minutes = minutes;
    }

    public int getAppCount() {
        return AppCount;
    }

    public void setAppCount(int appCount) {
        AppCount = appCount;
    }

    public int getMinutes() {
        return Minutes;
    }

    public void setMinutes(int minutes) {
        Minutes = minutes;
    }
}
