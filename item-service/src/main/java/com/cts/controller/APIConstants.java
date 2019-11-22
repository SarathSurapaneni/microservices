package com.cts.controller;

/**
 * 
 * API Constants for Item Service 
 *
 */
public class APIConstants {
	
	private APIConstants(){}
	
	public static final String BASE_URL="service2";
	public static final String ADD_ITEM=BASE_URL+"/item";
	public static final String GET_ITEMS=BASE_URL+"/items";
	public static final String GET_ITEM_BY_NAME=BASE_URL+"/items/{itemname}";

}
