package com.example

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.*

@WebFluxTest
@Import(RouterConfiguration::class) // See https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing-spring-boot-applications-testing-autoconfigured-webflux-tests
class RouterWebFluxTest(@Autowired val client: WebTestClient) {

	@MockkBean
	private lateinit var repository: FooRepository

	@Test
	fun fooTest() {
		every { repository.foo() }  returns "foo"
		client.get()
				.uri("/router/foo")
				.exchange()
				.expectStatus().isOk
				.expectBody<String>().isEqualTo("foo")
	}
}