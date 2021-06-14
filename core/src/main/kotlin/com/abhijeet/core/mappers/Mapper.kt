package com.abhijeet.core.mappers

interface Mapper<in F, out T> {
    suspend fun map(from: F): T
}