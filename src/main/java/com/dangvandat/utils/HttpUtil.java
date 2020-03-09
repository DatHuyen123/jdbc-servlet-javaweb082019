package com.dangvandat.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	
	private String stringValue;
	
	public HttpUtil(String value) {
		this.stringValue = value;
	}
	
	public <T> 	T toModel(Class<T> tClass)  {
		try {
			return new ObjectMapper().readValue(stringValue, tClass);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	} 
	
	public static HttpUtil of (BufferedReader reader) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			String line;
			while((line = reader.readLine()) != null) {
				stringBuilder.append(line);	
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}		
		return new HttpUtil(stringBuilder.toString());
	}
}
