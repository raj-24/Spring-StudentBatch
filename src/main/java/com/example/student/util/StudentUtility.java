package com.example.student.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudentUtility {
	public static String getapirespnum() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		String formattedDateTime = now.format(formatter);
		int millis = now.getNano() / 1000000;
		String finalString = "STDRES" + formattedDateTime + String.format("%04d", millis);
		return finalString;
	}
	
	public static String getapireqnum() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		String formattedDateTime = now.format(formatter);
		int millis = now.getNano() / 1000000;
		String finalString = "STDREQ" + formattedDateTime + String.format("%04d", millis);
		return finalString;
	}
	public static void main(String[] args) {
		System.out.println(getapireqnum());
		System.out.println(getapirespnum());
	}
}
