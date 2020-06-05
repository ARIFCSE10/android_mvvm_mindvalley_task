package com.mba.mindvalley.model

data class ChannelResponse(
    val data: ChannelResponseData
)

data class ChannelResponseData(
    val channels: List<Channel>
)

data class Channel(
    val title: String,
    val series: List<Series>,
    val mediaCount: Long,
    val latestMedia: List<LatestMedia>,
    val id: String? = null,
    val iconAsset: IconAsset? = null,
    val coverAsset: CoverAsset,
    val slug: String? = null
)

data class CoverAsset(
    val url: String
)

data class IconAsset(
    val thumbnailUrl: String? = null,
    val url: String? = null
)

data class LatestMedia(
    val type: String,
    val title: String,
    val coverAsset: CoverAsset
)

data class Series(
    val title: String,
    val coverAsset: CoverAsset,
    val id: String? = null
)
