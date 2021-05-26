package com.nakos.soa_uas_vincentardyanputra_2101658344.data

import com.nakos.soa_uas_vincentardyanputra_2101658344.model.News24Item
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface News24Service {

    @GET("articles/fin24/tech/rss")
    fun getNews24Item(): Single<News24Item>
}