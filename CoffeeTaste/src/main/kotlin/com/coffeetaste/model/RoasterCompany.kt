package com.coffeetaste.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Roaster companies catalog.  Experiences must reference a roaster
 * company from this catalog.  Users can manage (add/delete) companies
 * via dedicated REST endpoints.
 */
@Document(collection = "roasterCompanies")
data class RoasterCompany(
    @Id
    val id: String? = null,
    val name: String
)
