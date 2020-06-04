package com.mba.mindvalley.model


data class CategoryResponse(
    val data: CategoryResponseData
)

data class CategoryResponseData(
    val categories: List<CategoryResponseDataCategory>
)

data class CategoryResponseDataCategory(
    val name: String
)
