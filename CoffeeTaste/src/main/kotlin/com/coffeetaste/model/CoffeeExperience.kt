package com.coffeetaste.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Represents a single coffee tasting experience.  Each instance stores both
 * objective and subjective attributes about a coffee lot that a user has
 * tasted.  The document is persisted in the `experiences` collection in
 * MongoDB.
 *
 * @property id optional unique identifier assigned by MongoDB
 * @property coffeeType whether the coffee was a pre‑ground drip bag or
 *         whole roasted beans
 * @property roasterCompany name of the company that roasted the coffee
 * @property originCountry country of origin for the coffee beans (e.g. "Colombia")
 * @property originContinent continent for the country (e.g. "South America")
 * @property processingMethod how the coffee was processed (washed, natural,
 *         anaerobic, etc.)
 * @property lotName descriptive name or code for the coffee lot
 * @property tasteProfile free‑form text describing the dominant flavors and
 *         aromas experienced while tasting
 * @property aftertaste free‑form text describing the finish or lingering
 *         flavors after swallowing
 * @property rating numeric rating from 0 (worst) to 10 (best); must be an
 *         integer and is validated at the API layer
 */
@Document(collection = "experiences")
data class CoffeeExperience(
    @Id
    val id: String? = null,
    val coffeeType: CoffeeType,
    val roasterCompany: String,
    val originCountry: String,
    val originContinent: String,
    val processingMethod: String,
    val lotName: String,
    val tasteProfile: String,
    val aftertaste: String,
    val rating: Int
)
