package mbk.io.pixabay

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {


    @GET("api/")
    fun getImages(
        @Query("q") keyWord: String,
        @Query("key") key: String = "41477530-ffdd6097e5aec9973dfe92f0b",
        @Query("page") page:Int,
        @Query("per_page") perPage: Int = 3
    ): Call<PixaModel>

}