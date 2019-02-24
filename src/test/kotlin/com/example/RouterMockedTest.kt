package com.example

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

class RouterMockedTest {

	@Test
	fun fooTest() {
		val repository = mockk<FooRepository>()
		every { repository.foo() }  returns "foo"
		val router = RouterConfiguration().router(repository)
		val client = WebTestClient.bindToRouterFunction(router).build()
		client.get()
				.uri("/router/foo")
				.exchange()
				.expectStatus().isOk
				.expectBody<String>().isEqualTo("foo")
	}
}