package com.example

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient

@RunWith(SpringRunner::class)
@WebFluxTest // TODO Take in account functional routers when using @WebFluxTest
class FooRouterTests {

	@Autowired
	private lateinit var client: WebTestClient

	@MockBean
	private lateinit var repository: FooRepository

	@Test
	fun fooTest() {
		given(this.repository.foo()).willReturn("foo")
		client
				.get()
				.uri("/router/foo")
				.exchange()
				.expectStatus().isOk
				.expectBody(String::class.java) // TODO Add a expectBody<String>() extension
					.returnResult().responseBody.apply { assertEquals("foo", this) } // TODO Document or add and extension for .returnResult().responseBody.apply { } as a workaround to expect { }
	}
}