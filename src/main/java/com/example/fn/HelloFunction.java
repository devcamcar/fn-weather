package com.example.fn;

public class HelloFunction {
    private final String API_KEY = "96218ee2112692608dabb26f6ebd6135";

    public String handleRequest(String city) {
        if(city == null) return "";

        return "Hello, " + city + "!";
    }
}