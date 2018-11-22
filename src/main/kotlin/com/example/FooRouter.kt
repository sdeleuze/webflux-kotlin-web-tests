package com.example

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.toMono

@Configuration
class FooRouter {

	@Bean
	fun router(repository: FooRepository) = router {
		"/router".nest {
			GET("/foo") { ok().syncBody(repository.foo()) }
            GET("/foo/with/error") { ResponseStatusException(HttpStatus.NOT_FOUND, "No Foo found").toMono() }
		}
	}
}