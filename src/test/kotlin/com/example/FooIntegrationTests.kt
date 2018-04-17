package com.example

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.*

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureWebTestClient
class FooIntegrationTests {

	@Autowired
	private lateinit var client: WebTestClient

	@Test
	fun fooControllerTest() {
		client.get().uri("/controller/foo").exchange()
				.expectStatus().isOk
				// .expectBody<String>().isEqualTo("foo") with Spring Framework 5.O.6
				.expectBody<String>().returnResult().apply { assertEquals("foo", responseBody) }
	}

	@Test
	fun fooRouterTest() {
		client.get().uri("/router/foo").exchange()
				.expectStatus().isOk
				// .expectBody<String>().isEqualTo("foo") with Spring Framework 5.O.6
				.expectBody<String>().returnResult().apply { assertEquals("foo", responseBody) }
	}
}