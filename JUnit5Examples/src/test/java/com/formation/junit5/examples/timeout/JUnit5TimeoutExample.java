package com.formation.junit5.examples.timeout;



import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.Timeout;
//import org.junit.jupiter.api.Timeout;
import static java.time.Duration.ofMillis;
//import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class JUnit5TimeoutExample {
	
	//Global timeout
	@Rule
	public Timeout globalTimeout = Timeout.seconds(2);
	
	
	@Test   //Pass
	public void testInfiniteTametakingLoop1() throws InterruptedException 
	{
	    while (true)
	    {
	        Thread.currentThread().sleep(1000);
	    }
	}
	 
	@Test   //Fail
	public void testInfiniteTametakingLoop2() throws InterruptedException 
	{
		/*
	    while (true)
	    {
	        Thread.currentThread().sleep(5000);
	    }
	    */
	}
	
	@Test
	void timeoutExceeded() 
	{
	    // The following assertion fails with an error message similar to:
	    // execution exceeded timeout of 10 ms by 91 ms
	    assertTimeout(ofMillis(10), () -> {
	        // Simulate task that takes more than 10 ms.
	        Thread.sleep(100);
	    });
	}

	
	
	 // timed out after 500 miliseconds
    @Test
    // import org.junit.jupiter.api.Timeout;
    //@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testInfiniteTametakingLoop() throws InterruptedException 
	{
	    while (true)
	    {
	        Thread.currentThread().sleep(1000);
	    }
	}
	
}
