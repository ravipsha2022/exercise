package com.atlassian.rateLimiterProblem.rateLimiters;

import com.atlassian.rateLimiterProblem.interfaces.RateLimiter;
import lombok.Setter;

@Setter
public class Interceptor {
    RateLimiter rateLimiterStrategy;

    public void intercept(String api_dev_key){
        rateLimiterStrategy.preHandle(api_dev_key);
    }
}
