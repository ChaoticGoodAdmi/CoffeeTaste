package com.coffeetaste.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Processing methods describe how the coffee was processed after harvest
 * (e.g. washed, anaerobic, honey).  This catalog allows the frontend
 * to present a list of valid processing methods and also lets users
 * maintain the list via the API.
 */
@Document(collection = "processingMethods")
data class ProcessingMethod(
    @Id
    val id: String? = null,
    val name: String
)
