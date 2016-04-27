package com.jetbaba.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 关于异常的工具类.
 * 
 * @author calvin
 */
public class Exceptions {

	/**
	 * 将CheckedException转换为UncheckedException.
	 */
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}

	/**
	 * 将ErrorStack转化为String.
	 */
	public static String getStackTraceAsString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
	
	/**
	 * 将ErrorStack转化为String,且只保留一定的字符长度，方便持久化.
	 */
	public static String getShortStackTraceAsString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		String stackTrace =  stringWriter.toString();
		
		if(stackTrace.length() > 2000) {
			return stackTrace.substring(0, 1990);
		} else {
			return stackTrace;
		}
	}

	/**
	 * 判断异常是否由某些底层的异常引起,返回这个底层异常
	 */
	public static Exception isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
		Throwable cause = ex;
		while (cause != null) {
			for (Class<? extends Exception> causeClass : causeExceptionClasses) {
				if (causeClass.isInstance(cause)) {
					return (Exception) cause;
				}
			}
			cause = cause.getCause();
		}
		return null;
	}
}
