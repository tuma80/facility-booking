package com.iclub.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;

import com.iclub.member.vo.UserProfile;

public class CommonUtils {
	public static Date parseDate(String dateStr){
		SimpleDateFormat f = new SimpleDateFormat("d MMM yyyy");
		Date strippedDate = null;
		try {
			strippedDate = f.parse(dateStr);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return strippedDate;
	}
	public static String formatDate(Date date){
		SimpleDateFormat f = new SimpleDateFormat("d MMM yyyy");
		String strippedDate = null;
		try {
			strippedDate = f.format(date);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return strippedDate;
	}
	public static String formatDate(Date date,String pattern){
		SimpleDateFormat f = new SimpleDateFormat(pattern);
		String strippedDate = null;
		try {
			strippedDate = f.format(date);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return strippedDate;
	}	
	public static Date parseDate(String dateStr,String pattern){
		SimpleDateFormat f = new SimpleDateFormat(pattern);
		Date strippedDate = null;
		try {
			strippedDate = f.parse(dateStr);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return strippedDate;
	}	
	public static UserProfile getCurrentUserProfile(){
		return (UserProfile)SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		
	}
}
