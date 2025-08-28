package com.coffeetaste.repository

import com.coffeetaste.model.ProcessingMethod
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProcessingMethodRepository : MongoRepository<ProcessingMethod, String> {
    fun findByName(name: String): ProcessingMethod?
    fun existsByName(name: String): Boolean
}
