package com.project.journalApp.service;

import com.project.journalApp.api.response.WeatherResponse;
import com.project.journalApp.cache.AppCache;
import com.project.journalApp.constants.Placeholders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//@Component  service will be used to show case the buisness logic
@Slf4j
@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apikey;

 //private static final String API= "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather of" + city, WeatherResponse.class);
        if(weatherResponse !=null){
            log.info("This is working here");
            return weatherResponse;

        }
        else {
            String replace= appCache.app_Cache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,apikey);
            //  String replace=API .replace("CITY",city).replace("API_KEY",apikey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(replace, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            log.info("This is working There");

            if (body !=null) {
                redisService.set("weather of" + city,body,300l);

            }
            return body;
        }



    }

}
