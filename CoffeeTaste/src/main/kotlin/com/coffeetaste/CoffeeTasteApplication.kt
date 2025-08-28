package com.coffeetaste

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main entry point for the CoffeeTaste application.  
 *
 * This class uses the `@SpringBootApplication` annotation which enables
 * component scanning, auto configuration and property support.  When
 * executed it will start an embedded server (by default Undertow or Tomcat)
 * and expose the configured REST endpoints.
 */
@SpringBootApplication
class CoffeeTasteApplication

fun main(args: Array<String>) {
    runApplication<CoffeeTasteApplication>(*args)
}
