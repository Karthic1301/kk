package com.ast.HealthCare.Dao;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Repository;

@Repository
public class MessageDao {
	 static int smsDecryptOffsetValue = 25;
	 static char[] chars = {
		        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
		        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
		        'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
		        'y', 'z', '0', '1', '2', '3', '4', '5',
		        '6', '7', '8', '9', 'A', 'B', 'C', 'D',
		        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
		        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
		        'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@',
		        '#', '$', '%', '^', '&', '(', ')', '+',
		        '-', '*', '/', '[', ']', '{', '}', '=',
		        '<', '>', '?', '_', '"', '.', ',', ' '
		    };
		    
	
	public void Message1(String pid) {
		try {
			System.out.println(pid);
			String recipient = "7871416741";
		
			String de = "Your_Appointment_was-cancelled...Please_Contact_your_Hospital_for_further_details.";
	        String requestUrl  = "http://hpsms.dial4sms.com/api/web2sms.php?workingkey=Ad7c35f5c147ae04c43e71ba210bba8d2&to="+pid+"&sender=ANNASW&message="+de;
		    URL url = null;
		    url = new URL(requestUrl);
		    HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		    // to get response from a url
		    uc.setRequestMethod("GET");
		    uc.connect();
		    int code = uc.getResponseCode();
		    System.out.println(uc.getResponseCode());
		    System.out.println(code);
		    System.out.println(uc.getResponseMessage());
		    uc.disconnect();
		  //http://hpsms.dial4sms.com/api/web2sms.php?workingkey=Ad7c35f5c147ae04c43e71ba210bba8d2&to=7871416741&sender=ANNASW&message=Your Appointment was cancelled. Please contact your hospital.
		}
		catch(Exception ex) {
		System.out.println(ex.getMessage());
		}
	}
	
	public void MessageNextvisitAlert(String pid,String content) {
		try {
			System.out.println(pid);
			String recipient = "7871416741";
		
			String de = "Your_Appointment_was-cancelled...Please_Contact_your_Hospital_for_further_details.";
	        String requestUrl  = "http://hpsms.dial4sms.com/api/web2sms.php?workingkey=Ad7c35f5c147ae04c43e71ba210bba8d2&to="+pid+"&sender=ANNASW&message="+content;
		    URL url = null;
		    url = new URL(requestUrl);
		    HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		    System.out.println(uc.getResponseMessage());
		    uc.disconnect();
		  //http://hpsms.dial4sms.com/api/web2sms.php?workingkey=Ad7c35f5c147ae04c43e71ba210bba8d2&to=7871416741&sender=ANNASW&message=Your Appointment was cancelled. Please contact your hospital.
		}
		catch(Exception ex) {
		System.out.println(ex.getMessage());
		}
	}
	
	public String Message(String pid,String content) {
		String res = "sucess";
		try {
			System.out.println(pid);
			content = decrypt(content, smsDecryptOffsetValue);
			content = content.replace(" ", "%20");
			System.out.println(content+" h "+ pid);
			String requestUrl  = "http://hpsms.dial4sms.com/api/web2sms.php?workingkey=Ad7c35f5c147ae04c43e71ba210bba8d2&to="+pid+"&sender=ANNASW&message="+content;
		    URL url = null;
		    url = new URL(requestUrl);
		    HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		    // to get response from a url
		    uc.setRequestMethod("GET");
		    uc.connect();
		    int code = uc.getResponseCode();
		    System.out.println(uc.getResponseCode());
		    System.out.println(code);
		    System.out.println(requestUrl);
		    System.out.println(uc.getResponseMessage());
		    uc.disconnect();
		  //http://hpsms.dial4sms.com/api/web2sms.php?workingkey=Ad7c35f5c147ae04c43e71ba210bba8d2&to=7871416741&sender=ANNASW&message=Your Appointment was cancelled. Please contact your hospital.
		}
		catch(Exception ex) {
		System.out.println(ex.getMessage());
		}
		return res;
	}
	
	 String decrypt(String cip, int offset)
	    {
	        char[] cipher = cip.toCharArray();
	        for (int i = 0; i < cipher.length; i++) {
	            for (int j = 0; j < chars.length; j++) {
	                if (j >= offset && cipher[i] == chars[j]) {
	                    cipher[i] = chars[j - offset];
	                    break;
	                }
	                if (cipher[i] == chars[j] && j < offset) {
	                    cipher[i] = chars[(chars.length - offset +1) + j];
	                    break;
	                }
	            }
	        }
	        return String.valueOf(cipher);
	    }

}