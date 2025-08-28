package com.coffeetaste.repository

import com.coffeetaste.model.CoffeeTypeOption
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CoffeeTypeOptionRepository : MongoRepository<CoffeeTypeOption, String> {
    fun findByName(name: String): CoffeeTypeOption?
    fun existsByName(name: String): Boolean
}
