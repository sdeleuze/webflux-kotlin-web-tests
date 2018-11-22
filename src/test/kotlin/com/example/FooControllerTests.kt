package com.example

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@RunWith(SpringRunner::class)
@WebFluxTest
@ImportAutoConfiguration(ErrorWebFluxAutoConfiguration::class)
class FooControllerTests {

	@Autowired
	private lateinit var client: WebTestClient

	@MockBean
	private lateinit var repository: FooRepository

	@Test
	fun fooTest() {
		given(repository.foo()).willReturn("foo")
		client.get().uri("/controller/foo").exchange()
				.expectStatus().isOk
				.expectBody<String>().isEqualTo("foo")
	}

    @Test
    fun fooError() {
        client
                .get().uri("/controller/foo/with/error").exchange()
                .expectStatus().isNotFound
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("No Foo found")
    }
}