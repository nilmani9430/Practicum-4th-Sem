package com.example.agromart.model.map

data class MapResponse (
    val message: String="",
    val data: Data?=null
)

data class Data (
    val polyline: Polyline,
    val nearby: List<Nearby>
)

data class Nearby (
    val businessStatus: String,
    val geometry: Geometry,
    val icon: String,
    val iconBackgroundColor: String,
    val iconMaskBaseuri: String,
    val name: String,
    val placeid: String,
    val rating: Double,
    val reference: String,
    val scope: String,
    val types: List<String>,
    val userRatingsTotal: Long,
    val vicinity: String,
    val openingHours: OpeningHours? = null,
    val photos: List<Photo>? = null
)

data class Geometry (
    val location: Location,
    val viewport: Viewport
)

data class Location (
    val lat: Double,
    val lng: Double
)

data class Viewport (
    val northeast: Location,
    val southwest: Location
)

data class OpeningHours (
    val openNow: Boolean
)

data class Photo (
    val height: Long,
    val htmlAttributions: List<String>,
    val photoReference: String,
    val width: Long
)

data class Polyline (
    val points: String
)
