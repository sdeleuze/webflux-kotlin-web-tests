package com.example

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.*

@RunWith(SpringRunner::class)
@WebFluxTest
@Import(FooRouter::class) // See https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing-spring-boot-applications-testing-autoconfigured-webflux-tests
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
				// TODO expectBody + KT-5464 workaround, see https://jira.spring.io/browse/SPR-15692?focusedCommentId=158700#comment-158700
				// TODO Lack of suggestion of this extension, see https://youtrack.jetbrains.com/issue/KT-23834
				.expectBody<String>().returnResult().apply { assertEquals("foo", responseBody) }
	}
}