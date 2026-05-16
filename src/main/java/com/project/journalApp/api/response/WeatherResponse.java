package com.project.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class WeatherResponse {
    private  Location location;
   private Current current;

    @Getter
    @Setter
    public  class Current{
        @JsonProperty("observation_time")
        private String observationTime;
        @JsonProperty("temperature")
        private int temperature;
        @JsonProperty("weather_code")
        private int weatherCode;
        @JsonProperty("weather_descriptions")
        private ArrayList<String> weatherDescriptions;
        private int humidity;
        private int feelsLike;


    }
    @Getter
    @Setter
    public class Location{
        public String name;
        public String country;
        public String region;
        public String lat;
        public String lon;
        public String timezone_id;
        public String localtime;
        public int localtime_epoch;
        public String utc_offset;
    }



}
