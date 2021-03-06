package com.example.homeawaytestapp.model.api.data.details

data class Venue(
    val allowMenuUrlEdit: Boolean,
    val attributes: Attributes,
    val beenHere: BeenHere,
    val bestPhoto: BestPhoto,
    val canonicalUrl: String,
    val categories: List<Category>,
    val colors: Colors,
    val contact: Contact,
    val createdAt: Int,
    val defaultHours: DefaultHours,
    val dislike: Boolean,
    val hereNow: HereNow,
    val hours: Hours,
    val id: String,
    val inbox: Inbox,
    val likes: Likes,
    val listed: Listed,
    val location: Location,
    val name: String,
    val ok: Boolean,
    val page: Page,
    val pageUpdates: PageUpdates,
    val photos: Photos,
    val popular: Popular,
    val price: Price,
    val rating: Double,
    val ratingColor: String,
    val ratingSignals: Int,
    val reasons: Reasons,
    val seasonalHours: List<Any>,
    val shortUrl: String,
    val specials: Specials,
    val stats: Stats,
    val storeId: String,
    val timeZone: String,
    val tips: VenueTips,
    val url: String,
    val verified: Boolean
)