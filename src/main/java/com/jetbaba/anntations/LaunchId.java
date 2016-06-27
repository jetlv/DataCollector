package com.jetbaba.anntations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author jet
 * <br>
 * The annotated com.milanoo.Trigger will be invoked if the user input specific id
 * 
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LaunchId {
	String id();
}
