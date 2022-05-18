package com.atlassian.rateLimiterProblem.controller;

import com.atlassian.rateLimiterProblem.configs.ConfigService;
import com.atlassian.rateLimiterProblem.factory.InterceptorFactory;
import com.atlassian.rateLimiterProblem.rateLimiters.Interceptor;

public class SomeController {

    ConfigService configService = new ConfigService();
    Boolean isRateLimiterEnabled = configService.getStatusOfRateLimiting();
    InterceptorFactory interceptorFactory = new InterceptorFactory();
    Interceptor interceptor = interceptorFactory.getOrCreateInterceptor();


    void someABCAPI(String api_dev_key){
        if(isRateLimiterEnabled){
            interceptor.intercept(api_dev_key);
            //throttle
        }

        process();
    }

    void process(){
        System.out.println("Request Process Successfully");
    }
}
