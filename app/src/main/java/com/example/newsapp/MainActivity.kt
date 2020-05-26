package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshLayout.setOnRefreshListener {
            fetch()
        }
        fetch()
    }

    private fun fetch()
    {
        refreshLayout.isRefreshing = true
        val request = ServiceBuilder.buildService(NewsapiEndpoints::class.java)
        val call = request.getNews(api_key)

        call.enqueue(object : Callback<NewsApiData> {
            override fun onResponse(call: Call<NewsApiData>, response: Response<NewsApiData>) {
                refreshLayout.isRefreshing = false
                if (response.isSuccessful){
                    progress_bar.visibility = View.GONE
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = NewsAdapter(response.body()!!.results,this@MainActivity)
                    }
                }
            }
            override fun onFailure(call: Call<NewsApiData>, t: Throwable) {
                refreshLayout.isRefreshing = false
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
