package com.nordryd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Annotation for a class (typically an interface) whose only purpose is utility
 * and reference, with no derived classes.
 * </p>
 * 
 * <p>
 * <b>NOTE:</b> There's really no reason for implementing this custom annotation
 * other than Java practice on my part.
 * </p>
 * 
 * @author Nordryd
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface PluginUtility {
}
