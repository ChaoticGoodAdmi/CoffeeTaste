package com.coffeetaste.repository

import com.coffeetaste.model.CoffeeExperience
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Simple repository interface for persisting and retrieving coffee experiences.
 *
 * Extending [MongoRepository] provides a standard set of CRUD operations
 * without the need to write boilerplate code.  Additional custom
 * query methods can be defined here if necessary.
 */
interface CoffeeExperienceRepository : MongoRepository<CoffeeExperience, String>
