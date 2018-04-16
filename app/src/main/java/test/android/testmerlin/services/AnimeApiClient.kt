package test.android.testmerlin.services

import android.content.Context
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import test.android.testmerlin.models.AnimeApiResponse
import test.android.testmerlin.utils.App
import test.android.testmerlin.utils.Utils
import java.io.File
import java.util.concurrent.TimeUnit

class AnimeApiClient(mContext: Context) {

    init {
        AnimeApiClient.mContext = mContext
    }

    interface AnimeApiInterface {

        @get:GET("api/edge/anime")
        val animeList: Call<AnimeApiResponse>

        //@GET("{path}.json")
        //Call<RedditApiResponse> getPostsFromCategories(@Path("path") String category);
    }

    companion object {

        val BASE_URL = "https://kitsu.io/"

        private var retrofit: Retrofit? = null

        private var mContext: Context? = null

        //setup cache
        // 10 MiB
        val apiClient: Retrofit?
            get() {
                val httpCacheDirectory = File(App.getContext().cacheDir, "responses")
                val cacheSize = 10 * 1024 * 1024
                val cache = Cache(httpCacheDirectory, cacheSize.toLong())

                val client = OkHttpClient.Builder()
                        .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .cache(cache)
                        .build()

                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build()
                }
                return retrofit
            }

        private val REWRITE_CACHE_CONTROL_INTERCEPTOR = Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            if (Utils.isNetworkConnected()) {
                val maxAge = 60 // read from cache for 1 minute
                originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build()
            } else {
                val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build()
            }
        }
    }
}
