package bol.bconnex.settlement.business.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

public class UtilityService {
	private static final Logger logger = Logger.getLogger(UtilityService.class);
	public static Date getDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		return new Date(cal.getTime().getTime());
	}
	public static String getStrDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		return dateFormat.format(cal.getTime());
	}
	public static Date backDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return new Date(cal.getTime().getTime());
	}
	public static String backStrDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}
	public static Timestamp strToTimestamp(String str){
		Logger logger = Logger.getLogger(UtilityService.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmm");
		try {
			java.util.Date date = sdf.parse(str);
			java.sql.Timestamp ts = new Timestamp(date.getTime());
			return ts;
		} catch (ParseException e) {
			logger.debug("Exception occur while convert string to timestamp", e);
			return null;
		}
	}
	public static java.sql.Date strToDate(String str){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		try {
			java.util.Date date = sdf.parse(str);
			java.sql.Date dd = new java.sql.Date(date.getTime());
			return dd;
		} catch (ParseException e) {
			logger.debug("Exception occur while convert string to timestamp", e);
			return null;
		}
	}
	public static String dateToStr(java.sql.Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		return sdf.format(date);
	}
	public static java.sql.Date strToDate02(String str){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date date = sdf.parse(str);
			java.sql.Date dd = new java.sql.Date(date.getTime());
			return dd;
		} catch (ParseException e) {
			logger.debug("Exception occur while convert string to timestamp", e);
			return null;
		}
	}
	public static String buildRefference(String trace){
		 GregorianCalendar gc = new GregorianCalendar();
		 int jDate = gc.get(GregorianCalendar.DAY_OF_YEAR);
		 DecimalFormat numFormatter = new DecimalFormat("000");
		 String jDateFormat = numFormatter.format(jDate);
		 SimpleDateFormat simpleDateFormatY = new SimpleDateFormat("yyyy");
		 String y = simpleDateFormatY.format(new java.util.Date()).substring(3);
		 SimpleDateFormat simpleDateFormatH = new SimpleDateFormat("HH");
		 String hh = simpleDateFormatH.format(new java.util.Date());
		 String field37 = y+jDateFormat+hh+trace;
		return field37;
	}
}
