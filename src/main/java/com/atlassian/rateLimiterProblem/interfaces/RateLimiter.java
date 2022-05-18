package com.atlassian.rateLimiterProblem.interfaces;

public interface RateLimiter {

    void preHandle(String api_dev_key);
}
