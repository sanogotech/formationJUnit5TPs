package com.formation.basics;

/**
JUnit Jupiter supports the following annotations for configuring tests and extending the framework.
Unless otherwise stated, all core annotations are located in the org.junit.jupiter.api package in
the junit-jupiter-api module
 * 
 */
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.formation.customer.service.Calculator;

public class MySampleCalculatorTest {
	
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
	@RepeatedTest(1)
	public void addNumber(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		
		//Init //local
		System.out.println("Test :" + testInfo.getDisplayName());
		System.out.println("Running test  -> " + repetitionInfo.getCurrentRepetition());
		
		//Call  Service // reponse
		int resultactual = Calculator.add(x, y);
		
		//Assertions.assertEquals(expected ,resultactual , "Message");
		Assertions.assertEquals(45,resultactual , "18 + 27 should equal 45");
	}
	
	@Test
	@DisplayName("Subtract operation test")
	@RepeatedTest(5)
	public void subtractNumber(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		
		//Init //local
		System.out.println("Running test -> " + repetitionInfo.getCurrentRepetition());

		
		//Call  Service // reponse
		int resultactual = Calculator.subtract(x, y);
		
		//Assertions.assertEquals(expected ,resultactual , "Message");
		Assertions.assertEquals(-9,resultactual , "18 - 27 should equal -9");
	}
	
	
	public void subtractNumberNEW(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		
		//Init //local
		System.out.println("-- Test New subtractNumber ---:" + testInfo.getDisplayName());
		//Call  Service // reponse
		int resultactual = Calculator.subtract(x, y);
		
		//Assertions.assertEquals(expected ,resultactual , "Message");
		Assertions.assertEquals(-9,resultactual , "18 - 27 should equal -9");
	}
	
	
	public void subtractNumberOLD() {
		
		//Init //local
		//Call  Service // reponse
		int resultactual = Calculator.subtract(x, y);
		
		//Assertions.assertEquals(expected ,resultactual , "Message");
		Assertions.assertEquals(-9,resultactual , "18 - 27 should equal -9");
	}

	
	
	@AfterAll
	public static void cleanUp(){
		System.out.println("After All cleanUp() method called");
	}

}
