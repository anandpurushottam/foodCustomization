package com.example.foodorder.data.source.repo

import com.example.foodorder.data.source.remote.FoodAppService
import com.example.foodorder.feature.customize.dto.CustomizeFood
import io.reactivex.Single

class FoodOrderRepository(private val service: FoodAppService) : FoodAppService {
    override fun getCustomization(): Single<CustomizeFood> {
        return service.getCustomization()
    }

}