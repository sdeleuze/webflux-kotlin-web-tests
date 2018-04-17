This projects shows how to use `@WebTestClient` with Kotlin and a WebFlux server (annotation-based or functional):
 - [Integration test](https://github.com/sdeleuze/webflux-kotlin-web-tests/blob/master/src/test/kotlin/com/example/FooIntegrationTests.kt)
 - [`@WebFluxTest` with `@RestController`](https://github.com/sdeleuze/webflux-kotlin-web-tests/blob/master/src/test/kotlin/com/example/FooControllerTests.kt)
 - [`@WebFluxTest` with functional router](https://github.com/sdeleuze/webflux-kotlin-web-tests/blob/master/src/test/kotlin/com/example/FooRouterTests.kt)

See [SPR-15692 branch](https://github.com/sdeleuze/webflux-kotlin-web-tests/commits/SPR-15692)
for an improved version that leverages `expectBody` thanks to
[SPR-15692](https://jira.spring.io/browse/SPR-15692) fix.

See also [KT-23834](https://youtrack.jetbrains.com/issue/KT-23834) for the lack of suggestion
for `expectBody` in IDEA.

