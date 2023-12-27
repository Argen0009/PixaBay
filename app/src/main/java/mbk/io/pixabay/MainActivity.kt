package mbk.io.pixabay

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import mbk.io.pixabay.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var adapter = ImageAdapter(arrayListOf())

    var page = 1

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
        binding.recyclerView.adapter = adapter
    }

    private fun initClickers() {
        with(binding) {
            nextPageBtn.setOnClickListener {
                page++
                getImages(page)
            }
            searchBtn.setOnClickListener {
                getImages(1)
            }
        }
    }

    private fun ActivityMainBinding.getImages(page: Int) {
        progressView.isVisible = true
        RetrofitService().api.getImages(wordEd.text.toString(), page = page)
            .enqueue(object : retrofit2.Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            progressView.isVisible = true
                            adapter.addNewImages(it.hits as ArrayList<ImageModel>)
                        }
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}
