package com.t3.microservice.books.unit;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

import com.t3.microservice.books.models.entities.Book;
import com.t3.microservice.books.models.services.IBookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceUnitTest {
    
	@Autowired
	IBookService bookService;

    // MockBean is the annotation provided by Spring that wraps mockito one

    // Annotation that can be used to add mocks to a Spring ApplicationContext.

    // If any existing single bean of the same type defined in the context will be replaced by the mock, if no existing bean is defined a new one will be added.

    @MockBean
    private RestTemplate template;

    @Test
    public void testGetBooks() throws IOException {

        // Parsing mock file
    	// List<Book> mRs = JsonUtils.jsonFile2Object("ken.json", MangaResult.class);
    		
        // Mocking remote service
        // when(template.getForEntity(any(String.class), any(Class.class))).thenReturn(new ResponseEntity("127.0.0.1:8200/api/books", String.class));

        // I search for goku but system will use mocked response containing only ken, so I can check that mock is used.

//        List<Book> books = bookService.findAll();
//        assertThat(books).isNotNull()
//            .isNotEmpty()
//            .allMatch(p -> p.getTitle()
//                .toLowerCase()
//                .contains("ken"));

    }
}
