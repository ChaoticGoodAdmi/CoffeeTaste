package com.coffeetaste.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Coffee types define how the coffee is packaged when consumed.  Initially
 * only two values were supported (drip bag or roasted beans).  This catalog
 * allows new options to be added via the API.
 */
@Document(collection = "coffeeTypes")
data class CoffeeTypeOption(
    @Id
    val id: String? = null,
    val name: String
)
