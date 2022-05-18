package com.atlassian.rateLimiterProblem.configs;

import lombok.Getter;

import java.time.Duration;

@Getter
public class ConfigService {

    boolean isThrottlingEnabled = true;


    boolean isFixedWindowRateLimiterEnabled = true;
    boolean isLeakyBucktRateLimniterEnabled = false;
    int maxAllowedCreditsPerWindow = 10;

    int maxRequestCount =10;
    Duration windowSize = Duration.ofSeconds(1);

    public boolean getStatusOfRateLimiting(){
        return isThrottlingEnabled;
    }

    public  boolean getFixedWindowStatus(){
        return this.isFixedWindowRateLimiterEnabled;
    }

    public boolean getLeakyBuckeyWindowStatus(){
        return this.isLeakyBucktRateLimniterEnabled;
    }
}
