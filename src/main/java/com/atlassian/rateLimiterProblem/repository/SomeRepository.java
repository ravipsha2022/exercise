package com.atlassian.rateLimiterProblem.repository;

import com.atlassian.rateLimiterProblem.configs.ConfigService;
import com.atlassian.rateLimiterProblem.models.Customer;
import com.atlassian.rateLimiterProblem.services.PricingService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SomeRepository {

   static Map<String , Customer> customers;
   PricingService pricingService = new PricingService();
   ConfigService configService = new ConfigService();

   static {
       customers = new ConcurrentHashMap<>();
   }

   public Customer getOrCreateCustomer(String ap_dev_key , int currentWindow){
       customers.computeIfAbsent(ap_dev_key,(k)-> createNewCustomer(ap_dev_key,currentWindow));
       return customers.get(ap_dev_key);
   }

   Customer createNewCustomer(String api_dev_key, int currentWindow){
       Customer.builder()
               .currentWindow(currentWindow)
               .credits(pricingService.getCredits(api_dev_key))
               .requestCount(new AtomicInteger(0))
               .quota(new Semaphore(configService.getMaxRequestCount()))
               .build();
   }

}
