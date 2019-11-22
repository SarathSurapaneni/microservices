package com.cts.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class ItemServiceClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${itemservic.rest.getitemurl}")
	private String itemServiceURL;

	@HystrixCommand(fallbackMethod = "callItemService_Fallback")
	public String callItemService(String itemName) {
		System.out.println("Getting Item Service details for " + itemName);
		String response = restTemplate
				.exchange(itemServiceURL
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<String>() {
			}, itemName).getBody();

		System.out.println("Response Received as " + response + " -  " + new Date());

		//return "NORMAL FLOW !!! - Item Name -  " + itemName + " :::  Item Details " + response + " -  " + new Date();
		
		
		return response;
	}
	
	@SuppressWarnings("unused")
	private String callItemService_Fallback(String itemName) {
		System.out.println("Item Service is down!!! fallback route enabled...");
		return "CIRCUIT BREAKER ENABLED!!!No Response From Item Service at this moment. Service will be back shortly - " + new Date();
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
