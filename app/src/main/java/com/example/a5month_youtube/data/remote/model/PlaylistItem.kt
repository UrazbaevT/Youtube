package com.example.a5month_youtube.data.remote.model

data class PlaylistItem(
    val kind: String?,
    val etag: String?,
    val nextPageToken: String?,
    val items: List<Item?>?,
    val snippet: Item.Snippet?,
    val pageInfo: PageInfo?
) {
    data class Item(
        val kind: String?,
        val etag: String?,
        val id: String?,
        var date: String?,
        val snippet: Snippet?,
        val contentDetails: ContentDetails?
    ) {
        data class Snippet(
            val publishedAt: String?,
            val channelId: String?,
            val title: String?,
            val description: String?,
            val thumbnails: Thumbnails?,
            val channelTitle: String?,
            val playlistId: String?,
            val position: Int?,
            val resourceId: ResourceId?,
            val videoOwnerChannelTitle: String?,
            val videoOwnerChannelId: String?
        ) {
            data class Thumbnails(
                val default: Default?,
                val medium: Medium?,
                val high: High?,
                val standard: Standard?,
                val maxres: Maxres?
            ) {
                data class Default(
                    val url: String?,
                    val width: Int?,
                    val height: Int?
                )

                data class Medium(
                    val url: String?,
                    val width: Int?,
                    val height: Int?
                )

                data class High(
                    val url: String?,
                    val width: Int?,
                    val height: Int?
                )

                data class Standard(
                    val url: String?,
                    val width: Int?,
                    val height: Int?
                )

                data class Maxres(
                    val url: String?,
                    val width: Int?,
                    val height: Int?
                )
            }

            data class ResourceId(
                val kind: String?,
                val videoId: String?
            )
        }

        data class ContentDetails(
            val videoId: String?,
            val videoPublishedAt: String?,
            val duration: String,
        )
    }

    data class PageInfo(
        val totalResults: Int?,
        val resultsPerPage: Int?
    )
}