package com.example.foodorder.data.source.remote

import com.example.foodorder.feature.customize.dto.CustomizeFood
import io.reactivex.Single
import retrofit2.http.*

/**
 * Defines the abstract methods used for interacting with the Food API
 */

interface FoodAppService {

    //Get customization
    @GET("bins/19u0sf")
    fun getCustomization(): Single<CustomizeFood>


}

