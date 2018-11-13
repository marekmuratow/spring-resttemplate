package pl.entito.demo;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomRestTemplateRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RestTemplate restTemplate;

	public void getForEntityBookFromJson() {
		logger.info("get for entity");
		ResponseEntity<Book> response = restTemplate.getForEntity("http://localhost:8080/book/1", Book.class);
		Book book = response.getBody();
		logger.info(response.getStatusCode().name() + " " + book);
	}

	public void getForEntityBookListFromJson() {
		logger.info("get for entity list");
		ResponseEntity<Book[]> response = restTemplate.getForEntity("http://localhost:8080/books", Book[].class);
		List<Book> books = Arrays.asList(response.getBody());
		System.out.print(response.getStatusCode().name() + " ");
		books.stream().forEach(System.out::println);
	}

	public void getForObjectBookFromJson() {
		logger.info("get for object");
		Book forObject = restTemplate.getForObject("http://localhost:8080/book/1", Book.class);
		logger.info("" + forObject);
	}

	public void getForObjectBookListFromJson() {
		logger.info("get for object list");
		Book[] response = restTemplate.getForObject("http://localhost:8080/books", Book[].class);
		List<Book> books = Arrays.asList(response);
		books.stream().forEach(System.out::println);
	}

	public void postForLocationBook() {
		logger.info("post for location");
		HttpEntity<Book> request = new HttpEntity<>(createRandomBook());
		URI responseLocation = restTemplate.postForLocation("http://localhost:8080/addbookLocation", request);
		logger.info("POSTED URI " + responseLocation);
	}

	public void postForEntityBook() {
		logger.info("post for entity");
		HttpEntity<Book> request = new HttpEntity<>(createRandomBook());
		ResponseEntity<Book> responseEntity = restTemplate.postForEntity("http://localhost:8080/addbookEntity", request,
				Book.class);
		logger.info("Response entity " + responseEntity);
	}

	public void exchange() {
		logger.info("exchange as POST");
		HttpEntity<Book> request = new HttpEntity<>(createRandomBook());
		ResponseEntity<Book> exchangeResult = restTemplate.exchange("http://localhost:8080/addbookEntity",
				HttpMethod.POST, request, Book.class);
		logger.info("exchange " + exchangeResult);
	}

	public void put() {
		logger.info("PUT/Update");
		restTemplate.put("http://localhost:8080/update/1", new HttpEntity<>(createRandomBook()));
	}

	public void delete() {
		logger.info("DELETE");
		restTemplate.delete("http://localhost:8080/deleteBook/1");
	}

	public void options() {
		Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow("http://localhost:8080/addbookEntity");
		logger.info("Options: " + optionsForAllow);
	}

	private Book createRandomBook() {
		return new Book("Rest" + new Random().nextInt(), "Book");
	}
}
