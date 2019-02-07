package com.t3.microservice.books;

import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.t3.microservice.books.integration.BookControllerIntegrationTest;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@TestPropertySource(locations = "classpath:application.properties")
public class T3MicroserviceBooksApplicationTests {
	
	public static void main(String params[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		Result result = junit.run(BookControllerIntegrationTest.class);
		//resultReport(result);
	}
//
//	@Test
//	public void contextLoads() {
//	}

}

