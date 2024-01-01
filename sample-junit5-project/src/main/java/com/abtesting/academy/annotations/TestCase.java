package com.abtesting.academy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Test;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Test
public @interface TestCase {

	String key() default "";
	
    String name() default ""; 
}
