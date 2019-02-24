package com.example

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.*

@WebFluxTest
class ControllerWebFluxTest(@Autowired val client: WebTestClient) {

	@MockkBean
	private lateinit var repository: FooRepository

	@Test
	fun fooTest() {
		every { repository.foo() } returns "foo"
		client.get().uri("/controller/foo").exchange()
				.expectStatus().isOk
				.expectBody<String>().isEqualTo("foo")
	}
}