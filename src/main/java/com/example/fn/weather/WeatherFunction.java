package com.example.fn.weather;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import com.example.fn.weather.models.Weather;

// http://api.apixu.com/v1/current.json?key=518cb86fff374394bf5211954190103&q=Paris
public class WeatherFunction {
    private final String API_URL  = "http://api.apixu.com";
    private final String API_PATH = "/v1/current.json";
    private final String API_KEY  = "518cb86fff374394bf5211954190103";

    public String handleRequest(String city) {
        if(city == "") city = "Paris";

        Weather weather = getWeather(city);

        return "Temp: " + weather.getCurrent().getTempF();
    }

    private Weather getWeather(String city) {
        Client client = ClientBuilder.newClient();
        return client.target(API_URL)
                     .path(API_PATH)
                     .queryParam("key", API_KEY)
                     .queryParam("q", city)
                     .request()
                     .get(Weather.class);

    }

    /*
    {
      "location": {
         "name":"Paris",
         "region":"Ile-de-France",
         "country":"France",
         "lat":48.87,
         "lon":2.33,
         "tz_id":"Europe/Paris",
         "localtime_epoch":1551475803,
         "localtime":"2019-03-01 22:30"
       },
       "current": {
         "last_updated_epoch":1551474907,
         "last_updated":"2019-03-01 22:15",
         "temp_c":10.0,
         "temp_f":50.0,
         "is_day":0,
         "condition": {
           "text":"Partly cloudy",
           "icon":"//cdn.apixu.com/weather/64x64/night/116.png",
           "code":1003
         },
         "wind_mph":5.6,
         "wind_kph":9.0,
         "wind_degree":240,
         "wind_dir":"WSW",
         "pressure_mb":1021.0,
         "pressure_in":30.6,
         "precip_mm":0.2,
         "precip_in":0.01,
         "humidity":82,
         "cloud":75,
         "feelslike_c":8.8,
         "feelslike_f":47.9,
         "vis_km":10.0,
         "vis_miles":6.0,
         "uv":0.0
       }
     }
    */
}