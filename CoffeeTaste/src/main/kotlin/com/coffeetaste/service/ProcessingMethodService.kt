package com.coffeetaste.service

import com.coffeetaste.model.ProcessingMethod
import com.coffeetaste.repository.ProcessingMethodRepository
import org.springframework.stereotype.Service

@Service
class ProcessingMethodService(private val repository: ProcessingMethodRepository) {

    fun addProcessingMethod(method: ProcessingMethod): ProcessingMethod {
        if (repository.existsByName(method.name)) {
            throw IllegalArgumentException("Processing method with name ${'$'}{method.name} already exists")
        }
        return repository.save(method)
    }

    fun deleteProcessingMethod(id: String) {
        repository.deleteById(id)
    }

    fun listProcessingMethods(): List<ProcessingMethod> {
        return repository.findAll()
    }

    fun findByName(name: String): ProcessingMethod? {
        return repository.findByName(name)
    }
}
