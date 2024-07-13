package com.mahumane.todolist.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

	
    public static String getDateTime() {
    	LocalDateTime now = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
    	String dateTime = now.format(formatter);
    	
    	return dateTime;
    }
}
