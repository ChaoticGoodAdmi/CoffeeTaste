package com.coffeetaste.controller

import com.coffeetaste.model.CoffeeExperience
import com.coffeetaste.service.CoffeeExperienceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * REST controller exposing endpoints for managing coffee experiences.  The
 * controller delegates to [CoffeeExperienceService] for business logic.
 */
@RestController
@RequestMapping("/api/experiences")
@CrossOrigin(origins = ["*"])
class CoffeeExperienceController(
    private val service: CoffeeExperienceService
) {

    /**
     * Create a new coffee tasting experience.  The incoming request body is
     * validated at the controller layer; specifically the rating must be
     * between 0 and 10 inclusive.  If validation passes the record is
     * persisted and the saved document returned.
     */
    @PostMapping
    fun createExperience(@RequestBody experience: CoffeeExperience): ResponseEntity<Any> {
        // Simple validation for rating range
        if (experience.rating !in 0..10) {
            val body = mapOf("error" to "Rating must be between 0 and 10 inclusive")
            return ResponseEntity(body, HttpStatus.BAD_REQUEST)
        }
        val saved = service.save(experience)
        return ResponseEntity.status(HttpStatus.CREATED).body(saved)
    }

    /**
     * Retrieve experiences optionally filtered by several query parameters.
     *
     * @param roasterCompany exact match on roaster company
     * @param continent exact match on continent of origin
     * @param country exact match on country of origin
     * @param minRating minimum rating (inclusive)
     * @param maxRating maximum rating (inclusive)
     * @param search free‑text search across multiple fields
     */
    @GetMapping
    fun getExperiences(
        @RequestParam(required = false) roasterCompany: String?,
        @RequestParam(required = false) continent: String?,
        @RequestParam(required = false) country: String?,
        @RequestParam(required = false) minRating: Int?,
        @RequestParam(required = false) maxRating: Int?,
        @RequestParam(required = false) search: String?
    ): ResponseEntity<List<CoffeeExperience>> {
        val results = service.findByFilters(
            roasterCompany = roasterCompany,
            continent = continent,
            country = country,
            minRating = minRating,
            maxRating = maxRating,
            search = search
        )
        return ResponseEntity.ok(results)
    }

    /**
     * Retrieve a single experience by its identifier.  Returns a 404 status
     * code if no matching document is found.
     */
    @GetMapping("/{id}")
    fun getExperienceById(@PathVariable id: String): ResponseEntity<Any> {
        val experience = service.findById(id)
        return if (experience != null) {
            ResponseEntity.ok(experience)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Experience not found"))
        }
    }
}
