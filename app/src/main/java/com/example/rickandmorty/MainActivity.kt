package com.example.rickandmorty

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.models.RickAndMortyData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var linearlayoutManager: LinearLayoutManager
    private var pageNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateUI()
        getData()

        rv_characters_list.hasFixedSize()
        linearlayoutManager = LinearLayoutManager(this)
        rv_characters_list.layoutManager = linearlayoutManager

        btn_next.setOnClickListener {
            pageNumber += 1
            updateUI()
            getData()
        }
        btn_previous.setOnClickListener {
            pageNumber -= 1
            updateUI()
            getData()
        }
    }

    private fun getData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(RickAndMortyService::class.java)

        val retrofitData = retrofitBuilder.getCharacterData(pageNumber)

        retrofitData.enqueue(object : Callback<RickAndMortyData?> {
            override fun onResponse(
                call: Call<RickAndMortyData?>,
                response: Response<RickAndMortyData?>
            ) {
                val responseBody = response.body()
                if (responseBody != null) {
                    Log.e(" Rick and morty data", responseBody.toString())
                    characterListAdapter =
                        CharacterListAdapter(this@MainActivity, responseBody.results)
                    rv_characters_list.adapter = characterListAdapter
                    progress_bar_main.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<RickAndMortyData?>, t: Throwable) {
                d("Main Activity", "onFailure:  ", t)
            }
        })
    }

    private fun updateUI() {
        progress_bar_main.visibility = View.VISIBLE
        if (pageNumber == 1) {
            btn_previous.visibility = View.GONE
        } else if (pageNumber > 1) {
            btn_previous.visibility = View.VISIBLE
        }
        if (pageNumber == Constants.MAX_NUMBER_OF_PAGES) {
            btn_next.visibility = View.GONE
        }
    }
}