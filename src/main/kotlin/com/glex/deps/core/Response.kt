package com.glex.deps.core

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val success: Boolean? = null,
    val data: String? = null,
)