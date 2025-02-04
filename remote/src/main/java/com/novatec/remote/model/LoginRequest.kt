package com.novatec.remote.model

import com.novatec.core.Constants
import com.google.gson.annotations.SerializedName

/**
 * Representation for a [LoginRequest] fetched from the API
 */
data class LoginRequest(@SerializedName("phoneNum") val phoneNum: String,
                        @SerializedName("userType") val userType: String = Constants.ROLE_DRIVER)