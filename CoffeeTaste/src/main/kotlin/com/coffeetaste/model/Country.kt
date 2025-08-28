package com.coffeetaste.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


/**
 * Represents a country in the catalog. Each country belongs to a continent
 * and can be referenced from a coffee tasting experience.  The frontend
 * uses this catalog to populate drop m-downs for origin country selection.
 */
@Document(collection = "countries")
data class Country(
    @Id
    val id: String? = null,
    val name: String,
    val continent: String
)
