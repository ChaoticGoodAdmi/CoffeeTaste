package com.coffeetaste.repository

import com.coffeetaste.model.RoasterCompany
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RoasterCompanyRepository : MongoRepository<RoasterCompany, String> {
    fun findByName(name: String): RoasterCompany?
    fun existsByName(name: String): Boolean
}
