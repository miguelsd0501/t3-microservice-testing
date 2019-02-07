package com.t3.microservice.books.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.t3.microservice.books.controllers.BookController;
import com.t3.microservice.books.models.entities.Book;
import com.t3.microservice.books.models.services.IBookService;

@RunWith(SpringRunner.class)
//@WebMvcTest(value = BookController.class, secure = false)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Sql("/import.sql")
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

//	@Autowired
//	private IBookService bookService;

//		Course mockCourse = new Course("Course1", "Spring", "10 Steps",
//				Arrays.asList("Learn Maven", "Import Project", "First Example",
//						"Second Example"));
//	Book mockBook = new Book((long)100, "El mejor", "Este libro es el mejor de todos", 20, (long)2);

	String exampleBookJson = "{\"title\":\"Spring\",\"description\":\"10 Steps\",\"price\":34, \"authorId\":3}";

	@Test
	public void retrieveDetailsForBook() throws Exception {

//		Mockito.when(
//				bookService.findById(Mockito.anyLong())).thenReturn(mockBook);

				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/books/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println("----------" + result.getResponse());
		String expected = "{id:1,title:Andrés,description:Guzmán,price:4,authorId:1,createdAt:2018-01-01}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void createBook() throws Exception {
//		Book mockBook = new Book("No tengo idea", "No tengo idea de que poner aquí", 200, (long)2);

		// studentService.addCourse to respond back with mockCourse
//		Mockito.when(
//				bookService.save(
//						Mockito.any(Book.class))).thenReturn(mockBook);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/books")
				.accept(MediaType.APPLICATION_JSON).content(exampleBookJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

//		assertEquals("http://localhost/students/Student1/courses/1",
//				response.getHeader(HttpHeaders.LOCATION));

	}




}

