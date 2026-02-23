package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	public int getRandomNumber()
	{
		Random r=new Random();
		int randomNumber=r.nextInt(5000);
		return randomNumber;
	}
	public String getSystemDateYYYYDDMM()
	{
		Date dateObj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateObj);
		return date;
	}
	public String getRequiredDateYYYYDDMM(int days)
	{
		SimpleDateFormat sif=new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal=sif.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String reqDate=sif.format(cal.getTime());		
		return reqDate;
	}
}
