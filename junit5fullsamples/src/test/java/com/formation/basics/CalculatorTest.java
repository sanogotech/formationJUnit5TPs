package com.formation.basics;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.formation.customer.service.Calculator;

public class CalculatorTest {
	
	private static int  x;
	private static int  y;
	
	@BeforeAll
	public static void init(){
		//init  All
		System.out.println("Before All init() method called");
		x = 18;
		y = 27;
	}
	
	
	@Test
	@DisplayName("Add operation test")
	public void addNumber() {
		
		//Init //local
		//TODO
		System.out.println("Init for   addNumber() ...");
		
		//Call  Service // reponse
		System.out.println("Call Service / Response  ...");
		int resultactual = Calculator.add(x, y);
		
		//Assertions.assertEquals(expected ,resultactual , "Message");
		System.out.println("Assertions  ...");
		Assertions.assertEquals(45,resultactual , "18 + 27 should equal 45");
	}
	
	
	@AfterAll
	public static void cleanUp(){
		System.out.println("After All cleanUp() method called");
	}


}
