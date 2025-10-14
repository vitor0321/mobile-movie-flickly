package com.walcker.flickly.products.movies.features.data.mapper

import com.walcker.flickly.products.movies.features.data.mapper.GenreResponseMapper.toDomain
import com.walcker.flickly.products.movies.features.data.models.GenreResponse
import com.walcker.flickly.products.movies.features.ui.preview.mockData.genre1TestData
import com.walcker.flickly.products.movies.features.ui.preview.mockData.genre2TestData
import com.walcker.flickly.products.movies.mockData.data.genreResponseTestData
import kotlin.test.Test
import kotlin.test.assertEquals

internal class GenreResponseMapperTest {

    @Test
    fun `given genre response when mapping to domain then should map all fields correctly`() {
        // Given
        val genreResponse = genreResponseTestData

        // When
        val result = genreResponse.toDomain()

        // Then
        assertEquals(genre1TestData, result)
    }

    @Test
    fun `given Adventure genre response when mapping to domain then should create correct domain object`() {
        // Given
        val genreResponse = GenreResponse(id = 2, name = "Adventure")

        // When
        val result = genreResponse.toDomain()

        // Then
        assertEquals(genre2TestData, result)
    }
}
