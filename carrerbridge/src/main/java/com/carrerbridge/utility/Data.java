package com.carrerbridge.utility;

public class Data {
	
	public static String getMessageBody(String otp, String name) {
	    return "<!DOCTYPE html>\n" +
	            "<html lang=\"en\">\n" +
	            "<head>\n" +
	            "    <meta charset=\"UTF-8\">\n" +
	            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
	            "    <title>OTP Verification</title>\n" +
	            "    <style>\n" +
	            "        body {\n" +
	            "            font-family: Arial, sans-serif;\n" +
	            "            background-color: #f4f4f4;\n" +
	            "            margin: 0;\n" +
	            "            padding: 0;\n" +
	            "        }\n" +
	            "        .email-container {\n" +
	            "            background-color: #ffffff;\n" +
	            "            padding: 20px;\n" +
	            "            margin: 0 auto;\n" +
	            "            width: 100%;\n" +
	            "            max-width: 600px;\n" +
	            "            border-radius: 8px;\n" +
	            "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
	            "        }\n" +
	            "        .email-header {\n" +
	            "            background-color: #5C2FC2;\n" +
	            "            padding: 20px;\n" +
	            "            color: white;\n" +
	            "            text-align: center;\n" +
	            "            border-radius: 8px 8px 0 0;\n" +
	            "        }\n" +
	            "        .email-header h1 {\n" +
	            "            margin: 0;\n" +
	            "        }\n" +
	            "        .email-body {\n" +
	            "            padding: 20px;\n" +
	            "            color: #333333;\n" +
	            "        }\n" +
	            "        .otp {\n" +
	            "            font-size: 24px;\n" +
	            "            font-weight: bold;\n" +
	            "            background-color: #f0f0f0;\n" +
	            "            padding: 10px;\n" +
	            "            text-align: center;\n" +
	            "            margin: 20px 0;\n" +
	            "            border-radius: 5px;\n" +
	            "        }\n" +
	            "        .email-footer {\n" +
	            "            padding: 20px;\n" +
	            "            text-align: center;\n" +
	            "            font-size: 12px;\n" +
	            "            color: #777777;\n" +
	            "        }\n" +
	            "    </style>\n" +
	            "</head>\n" +
	            "<body>\n" +
	            "    <div class=\"email-container\">\n" +
	            "        <div class=\"email-header\">\n" +
	            "            <h1>OTP Verification For Password Reset</h1>\n" +
	            "        </div>\n" +
	            "        <div class=\"email-body\">\n" +
	            "            <p>Dear "+ name +"</p>\n" +
	            "            <p>Your One-Time Password (OTP) is:</p>\n" +
	            "            <div class=\"otp\">" + otp + "</div>\n" +
	            "            <p>Please use this OTP to complete your verification. The OTP is valid for 10 minutes.</p>\n" +
	            "        </div>\n" +
	            "        <div class=\"email-footer\">\n" +
	            "            <p>Thank you for using our service!</p>\n" +
	            "            <p>&copy; 2024 CarrerBridge. All rights reserved.</p>\n" +
	            "        </div>\n" +
	            "    </div>\n" +
	            "</body>\n" +
	            "</html>";
	}

}
