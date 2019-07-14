import android.content.Context
import com.example.foodorder.FoodAppApplication
import com.example.foodorder.data.source.remote.ErrorHandlerInterceptor
import com.example.foodorder.data.source.remote.FoodAppService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Provide "make" methods to create instances of [FoodAppService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */


object NetworkModule {

    private var BASE_URL = "https://api.myjson.com/"

    fun makeFoodService(isDebug: Boolean): FoodAppService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(isDebug), FoodAppApplication.getApplcationContext()
        )
        return makeFoodService(okHttpClient, makeGson(), BASE_URL)
    }

    private fun makeFoodService(okHttpClient: OkHttpClient, gson: Gson, baseUrl: String): FoodAppService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(FoodAppService::class.java)
    }


    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, context: Context): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(ErrorHandlerInterceptor(context))
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(ChuckInterceptor(context))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

}