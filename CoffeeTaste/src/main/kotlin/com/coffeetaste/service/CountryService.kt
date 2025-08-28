package com.coffeetaste.service

import com.coffeetaste.model.Country
import com.coffeetaste.repository.CountryRepository
import org.springframework.stereotype.Service

@Service
class CountryService(private val countryRepository: CountryRepository) {

    fun addCountry(country: Country): Country {
        if (countryRepository.existsByName(country.name)) {
            throw IllegalArgumentException("Country with name ${'$'}{country.name} already exists")
        }
        return countryRepository.save(country)
    }

    fun deleteCountry(id: String) {
        countryRepository.deleteById(id)
    }

    fun listContinents(): List<String> {
        return countryRepository.findAll().map { it.continent }.distinct()
    }

    fun listCountries(continent: String?): List<Country> {
        return if (continent == null) {
            countryRepository.findAll()
        } else {
            countryRepository.findByContinent(continent)
        }
    }

    fun findByName(name: String): Country? {
        return countryRepository.findByName(name)
    }
}
