package com.mba.mindvalley.model

data class NewEpisodeResponse(
    val data: NewEpisodeResponseData
)

data class NewEpisodeResponseData(
    val media: List<NewEpisodeResponseDataMedia>
)

data class NewEpisodeResponseDataMedia(
    val type: String,
    val title: String,
    val coverAsset: NewEpisodeResponseDataMediaCoverAsset,
    val channel: NewEpisodeResponseDataMediaChannel
)

data class NewEpisodeResponseDataMediaChannel(
    val title: String
)

data class NewEpisodeResponseDataMediaCoverAsset(
    val url: String
)
