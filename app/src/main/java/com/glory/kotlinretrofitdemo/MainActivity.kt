package com.glory.kotlinretrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.glory.kotlinretrofitdemo.databinding.ActivityMainBinding
import com.glory.kotlinretrofitdemo.model.PlantDTO
import com.glory.kotlinretrofitdemo.model.PlantList
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var plantAdapter : ChildOakPlantAdapter
    lateinit var binding : ActivityMainBinding


    var log : String  = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecyclerView()

        val service = RetrofitClient.apiClient?.create(PlantService::class.java)
        val call = service?.getAllPlant()
        call?.enqueue(object : Callback<PlantList>{
            override fun onResponse(call: Call<PlantList>, response: Response<PlantList>) {
                val body  = response?.body()
                val plants= body?.plants

                var size = plants?.size
                plantAdapter.submitList(plants)
                Log.e(log, "plants " + size)

            }

            override fun onFailure(call: Call<PlantList>, t: Throwable) {
                Toast.makeText(this@MainActivity,
                    "Something went wrong", Toast.LENGTH_LONG).show()
            }

        })

//        getPlants()

    }

    fun getPlants(){
        val service = RetrofitClient.apiClient?.create(PlantService::class.java)
        service?.getOakPlant()?.enqueue(object : Callback<PlantList>{

            override fun onResponse(call: Call<PlantList>, response: Response<PlantList>) {
                val body  = response?.body()
                val plants = body?.plants
                var size = plants?.size
                plantAdapter.submitList(plants)
                Log.e(log, " oak plants  " + size)
            }

            override fun onFailure(call: Call<PlantList>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No internet connection "
                        + t.message, Toast.LENGTH_LONG).show()

            }


        })
    }

   fun initRecyclerView(){
       binding.plantRecyclerview.apply {
           plantAdapter = ChildOakPlantAdapter(this@MainActivity)
           layoutManager = LinearLayoutManager(this@MainActivity,
               LinearLayoutManager.VERTICAL, false)
                this.setHasFixedSize(true)
            adapter = plantAdapter
        }
    }

//    fun initRecyclerView2(){
//        plantAdapter = PlantAdapter(this)
//       val layoutManager = LinearLayoutManager(this@MainActivity,
//            LinearLayoutManager.HORIZONTAL, false)
//        binding.plantRecyclerview.setHasFixedSize(true)
//        binding.plantRecyclerview.layoutManager = layoutManager
//        binding.plantRecyclerview.adapter = plantAdapter
//    }

}
