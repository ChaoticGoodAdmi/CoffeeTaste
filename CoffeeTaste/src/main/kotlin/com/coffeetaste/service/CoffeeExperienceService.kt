package com.coffeetaste.service

import com.coffeetaste.model.CoffeeExperience
import com.coffeetaste.repository.CoffeeExperienceRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

/**
 * Service layer encapsulating the business logic for creating and querying
 * coffee tasting experiences.  Using a service promotes separation of
 * concerns and keeps controllers light weight.
 *
 * @param repository Spring Data repository providing CRUD operations
 * @param mongoTemplate low‑level template used to build dynamic queries
 */
@Service
class CoffeeExperienceService(
    private val repository: CoffeeExperienceRepository,
    private val mongoTemplate: MongoTemplate
) {

    /**
     * Persist a new coffee experience.
     *
     * @param experience the entity to be saved
     * @return the saved entity
     */
    fun save(experience: CoffeeExperience): CoffeeExperience = repository.save(experience)

    /**
     * Retrieve all coffee experiences matching the supplied filters.  Filters
     * are optional; if none are provided, all experiences are returned.
     *
     * @param roasterCompany optional exact match on the roaster company name
     * @param continent optional exact match on the bean’s continent
     * @param country optional exact match on the bean’s country
     * @param minRating optional minimum rating (inclusive)
     * @param maxRating optional maximum rating (inclusive)
     * @param search optional free‑text search against several fields (case
     *               insensitive)
     * @return list of matching experiences
     */
    fun findByFilters(
        roasterCompany: String?,
        continent: String?,
        country: String?,
        minRating: Int?,
        maxRating: Int?,
        search: String?
    ): List<CoffeeExperience> {
        val criteriaList = mutableListOf<Criteria>()

        // Exact matches for simple fields
        if (!roasterCompany.isNullOrBlank()) {
            criteriaList.add(Criteria.where("roasterCompany").`is`(roasterCompany))
        }
        if (!continent.isNullOrBlank()) {
            criteriaList.add(Criteria.where("originContinent").`is`(continent))
        }
        if (!country.isNullOrBlank()) {
            criteriaList.add(Criteria.where("originCountry").`is`(country))
        }
        if (minRating != null) {
            criteriaList.add(Criteria.where("rating").gte(minRating))
        }
        if (maxRating != null) {
            criteriaList.add(Criteria.where("rating").lte(maxRating))
        }

        // Free‑text search across multiple fields using a case‑insensitive regex
        if (!search.isNullOrBlank()) {
            val pattern = ".*" + Regex.escape(search) + ".*"
            val searchCriteria = Criteria().orOperator(
                Criteria.where("tasteProfile").regex(pattern, "i"),
                Criteria.where("aftertaste").regex(pattern, "i"),
                Criteria.where("lotName").regex(pattern, "i"),
                Criteria.where("processingMethod").regex(pattern, "i"),
                Criteria.where("roasterCompany").regex(pattern, "i"),
                Criteria.where("originCountry").regex(pattern, "i"),
                Criteria.where("originContinent").regex(pattern, "i")
            )
            criteriaList.add(searchCriteria)
        }

        val query = Query()
        if (criteriaList.isNotEmpty()) {
            // Combine all criteria using AND
            query.addCriteria(Criteria().andOperator(*criteriaList.toTypedArray()))
        }

        return mongoTemplate.find(query, CoffeeExperience::class.java)
    }

    /**
     * Retrieve all experiences without any filtering.
     */
    fun findAll(): List<CoffeeExperience> = repository.findAll()

    /**
     * Fetch a single experience by its identifier.
     */
    fun findById(id: String): CoffeeExperience? = repository.findById(id).orElse(null)
}
