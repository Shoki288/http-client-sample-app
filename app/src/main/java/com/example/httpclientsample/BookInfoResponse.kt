package com.example.httpclientsample

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookInfoListResponse(
    @SerialName("item")
    val items: List<BookInfo>
)

@Serializable
data class BookInfo(
    @SerialName("id")
    val id: String,
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo,
    @SerialName("saleInfo")
    val saleInfo: SaleInfo,
    @SerialName("accessInfo")
    val accessInfo: AccessInfo?
)

@Serializable
data class VolumeInfo(
    @SerialName("title")
    val title: String,
    @SerialName("authors")
    private val authors: List<String> = emptyList(),
    @SerialName("publisher")
    val publisher: String = "",
    @SerialName("publishedDate")
    val publishedDate: String = "2022",  // YYYY-MM-ddで返る
    @SerialName("description")
    val description: String = "",
    @SerialName("pageCount")
    val pageCount: Int = 0,
    @SerialName("categories")
    val categories: List<String> = emptyList(),
    @SerialName("averageRating")
    private val averageRating: Int?,
    @SerialName("ratingCount")
    private val ratingCount: Int = 0,
    @SerialName("images")
    val images: ImageLinks = ImageLinks(),
    @SerialName("language")
    val language: String,
    @SerialName("previewLink")
    val previewLink: String,
    @SerialName("isFavorite")
    val isFavorite: Boolean = false
) {
    val averageReviewRate: Int
        get() = pageCount % 6
    val totalReviewCount: Int
        get() = pageCount
    val author: String
        get() = authors.joinToString().ifEmpty { "不明" }
}

@Serializable
data class ImageLinks(
    @SerialName("thumbnail")
    private val thumbnail: String = ""
) {
    val imageUrl: String
        get() = thumbnail.replace("http:", "https:")
}

@Serializable
data class SaleInfo(
    @SerialName("listPrice")
    val listPrice: Price = Price()
)

@Serializable
data class Price(
    @SerialName("price")
    val price: Int = 0
)

@Serializable
data class AccessInfo(
    @SerialName("downloadAccess")
    val downloadAccess: DownloadAccess?
)

@Serializable
data class DownloadAccess(
    @SerialName("message")
    val message: String?
)