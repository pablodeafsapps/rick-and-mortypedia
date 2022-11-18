package org.pablodeafsapps.rickandmortypedia.data.utils

import retrofit2.Converter
import retrofit2.Retrofit

private const val DEFAULT_BASE_URL: String = "https://rickandmortyapi.com/api/"

fun getRetrofitInstance(
    baseUrl: String = DEFAULT_BASE_URL, converterFactory: Converter.Factory
): Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(converterFactory).build()
