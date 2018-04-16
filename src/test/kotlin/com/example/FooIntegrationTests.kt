package com.example

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult

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
				// TODO expectBody + KT-5464 workaround, see https://jira.spring.io/browse/SPR-15692?focusedCommentId=158700#comment-158700
				.returnResult<String>().responseBody.apply { assertEquals("foo", this) }
	}

	@Test
	fun fooRouterTest() {
		client.get().uri("/router/foo").exchange()
				.expectStatus().isOk
				// TODO expectBody + KT-5464 workaround, see https://jira.spring.io/browse/SPR-15692?focusedCommentId=158700#comment-158700
				.returnResult<String>().responseBody.apply { assertEquals("foo", this) }
	}
}