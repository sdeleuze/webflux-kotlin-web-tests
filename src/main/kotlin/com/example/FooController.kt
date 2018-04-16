package com.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/controller")
class FooController(private val repository: FooRepository) {

	@GetMapping("/foo")
	fun foo() = repository.foo()
}