package com.coffeetaste.service

import com.coffeetaste.model.RoasterCompany
import com.coffeetaste.repository.RoasterCompanyRepository
import org.springframework.stereotype.Service

/**
 * Service for managing roaster companies.
 * Provides CRUD operations and validation.
 */
@Service
class RoasterCompanyService(
    private val roasterCompanyRepository: RoasterCompanyRepository
) {

    /**
     * Add a new roaster company.
     * Throws IllegalArgumentException if a company with the same name already exists.
     */
    fun addRoasterCompany(company: RoasterCompany): RoasterCompany {
        if (roasterCompanyRepository.existsByName(company.name)) {
            throw IllegalArgumentException("Roaster company with name '${'$'}{company.name}' already exists")
        }
        return roasterCompanyRepository.save(company)
    }

    /**
     * Delete a roaster company by its identifier.
     */
    fun deleteRoasterCompany(id: String) {
        roasterCompanyRepository.deleteById(id)
    }

    /**
     * List all available roaster companies.
     */
    fun listRoasterCompanies(): List<RoasterCompany> = roasterCompanyRepository.findAll()

    /**
     * Find a roaster company by name. Returns null if none found.
     */
    fun findByName(name: String): RoasterCompany? = roasterCompanyRepository.findByName(name)
}
