package com.posidex.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	public static final String dateFormat = "dd-MM-yyyy";
	public static String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonUtils.dateFormat);
        return dateFormat.format(date);
	}
}
