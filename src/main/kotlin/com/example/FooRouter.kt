package com.example

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.router

@Configuration
class FooRouter {

	@Bean
	fun router(repository: FooRepository) = router {
		"/router".nest {
			GET("/foo") { ok().syncBody(repository.foo()) }
		}
	}
}