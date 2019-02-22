package com.example.fn;

import com.fnproject.fn.testing.*;
import org.junit.*;

import static org.junit.Assert.*;

public class WeatherFunctionTest {
    @Rule
    public final FnTestingRule testing = FnTestingRule.createDefault();

    @Test
    public void shouldReturnSeattle() {
       testing.givenEvent().withBody("Seattle").enqueue();
       testing.thenRun(WeatherFunction.class, "handleRequest");

       FnResult result = testing.getOnlyResult();
       assertEquals("Hello, Seattle!", result.getBodyAsString());
    }
 }