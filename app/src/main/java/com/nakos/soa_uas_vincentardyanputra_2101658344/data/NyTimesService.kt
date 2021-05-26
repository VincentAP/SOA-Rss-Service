package com.nakos.soa_uas_vincentardyanputra_2101658344.data

import com.nakos.soa_uas_vincentardyanputra_2101658344.model.NyTimesItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NyTimesService {

    @GET("services/xml/rss/nyt/Arts.xml")
    fun getNyTimesItem(): Single<NyTimesItem>
}