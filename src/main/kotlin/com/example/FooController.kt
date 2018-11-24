package com.example

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/controller")
class FooController(private val repository: FooRepository) {

	@GetMapping("/foo")
	fun foo() = repository.foo()

    @GetMapping("/foo/with/error")
	fun fooWithError(): Nothing = throw ResponseStatusException(HttpStatus.NOT_FOUND, "No Foo found")
}