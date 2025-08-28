package com.coffeetaste.service

import com.coffeetaste.model.CoffeeTypeOption
import com.coffeetaste.repository.CoffeeTypeOptionRepository
import org.springframework.stereotype.Service

/**
 * Service for managing coffee type options.
 * Provides CRUD operations and validation.
 */
@Service
class CoffeeTypeService(
    private val coffeeTypeRepository: CoffeeTypeOptionRepository
) {

    /**
     * Add a new coffee type option.
     * Throws IllegalArgumentException if a coffee type with the same name already exists.
     */
    fun addCoffeeType(option: CoffeeTypeOption): CoffeeTypeOption {
        if (coffeeTypeRepository.existsByName(option.name)) {
            throw IllegalArgumentException("Coffee type with name '${'$'}{option.name}' already exists")
        }
        return coffeeTypeRepository.save(option)
    }

    /**
     * Delete an existing coffee type by its identifier.
     */
    fun deleteCoffeeType(id: String) {
        coffeeTypeRepository.deleteById(id)
    }

    /**
     * List all available coffee type options.
     */
    fun listCoffeeTypes(): List<CoffeeTypeOption> = coffeeTypeRepository.findAll()

    /**
     * Find a coffee type by its name. Returns null if none found.
     */
    fun findByName(name: String): CoffeeTypeOption? = coffeeTypeRepository.findByName(name)
}
