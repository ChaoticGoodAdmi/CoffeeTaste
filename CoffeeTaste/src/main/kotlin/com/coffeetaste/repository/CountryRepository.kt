package com.coffeetaste.repository

import com.coffeetaste.model.Country
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Repository for managing countries in the catalog.  Provides convenience
 * methods for finding countries by name or continent and for checking
 * whether a country already exists.
 */
interface CountryRepository : MongoRepository<Country, String> {
    fun findByName(name: String): Country?
    fun findByContinent(continent: String): List<Country>
    fun existsByName(name: String): Boolean
}
