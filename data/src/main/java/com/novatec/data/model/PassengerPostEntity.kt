package com.novatec.data.model

import com.novatec.core.EPostStatus
import com.novatec.core.EPostType

/**
 * Representation for a [PassengerPostEntity] fetched from the API
 */
data class PassengerPostEntity(val id: Long,
                               val from: PlaceEntity,
                               val to: PlaceEntity,
                               val price: Int,
                               val departureDate: String,
                               val createdDate: String,
                               val updatedDate: String,
                               val finishedDate: String? = null,
                               val timeFirstPart: Boolean,
                               val timeSecondPart: Boolean,
                               val timeThirdPart: Boolean,
                               val timeFourthPart: Boolean,
                               val airConditioner: Boolean,
                               val profile: ProfileEntity,
                               val remark: String? = null,
                               val postStatus: EPostStatus,
                               val myLastOffer: MyOfferEntity? = null,
                               val agreedOffer: AgreedOfferEntity? = null,
                               val imageList: List<ImageEntity> = listOf(),
                               val seat: Int,
                               val postType: EPostType = EPostType.PASSENGER_SM)

data class MyOfferEntity(val id: Long,
                         val postId: Long,
                         val repliedPostId: Long,
                         val status: String,
                         val message: String,
                         val submitDate: String,
                         val priceInt: Int,
                         val seat: Int)


data class AgreedOfferEntity(val message: String,
                             val priceInt: Int,
                             val seat: Int)