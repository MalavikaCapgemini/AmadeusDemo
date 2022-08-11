package com.assignment.amadeus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assignment.amadeus.adapter.CityAdapter
import com.assignment.amadeus.data.local.CityDatabase
import com.assignment.amadeus.databinding.ActivityMainBinding
import com.assignment.amadeus.viewmodel.MainViewModel
import com.assignment.amadeus.viewmodel.MyViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // private val adapter by lazy { CityAdapter() }
    private lateinit var adapter: CityAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val cityDatabase = CityDatabase.getInstance(this)
        val myViewModelFactory = MyViewModelFactory(cityDatabase!!)

        viewModel = ViewModelProvider(this, myViewModelFactory).get(MainViewModel::class.java)
        adapter = CityAdapter(CityAdapter.OnClickListener { cityDetails ->
            Toast.makeText(applicationContext, "${cityDetails.id}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MapsActivity::class.java)
            val bundle = Bundle()
            bundle.putString("cityName", cityDetails.cityName.toString())
            bundle.putString("country", cityDetails.country.toString())
            bundle.putDouble("longitude", cityDetails.longitude.toDouble())
            bundle.putDouble("latitude", cityDetails.latitude.toDouble())
            bundle.putInt("zoom", cityDetails.zoom.toInt())
            bundle.putInt("time", cityDetails.time.toInt())
            bundle.putDouble("temp", cityDetails.temp.toDouble())
            bundle.putDouble("speed", cityDetails.speed.toDouble())
            bundle.putString("main", cityDetails.main.toString())
            bundle.putString("description", cityDetails.description.toString())
            intent.putExtras(bundle)
            startActivity(intent)
        })


        viewModel.cityList.observe(this, Observer { result ->
            adapter.submitList(result)
            binding.recyclerView.adapter = adapter


        })
        viewModel.loading.observe(this, Observer { loading ->
            binding.progressBar.isVisible = loading
        })
        binding.txtSearch.addTextChangedListener {

            if (it.toString().isNotEmpty()) {
                viewModel.searchCityBYName(it.toString())
                viewModel.cityList.observe(this, Observer { result ->
                    adapter.submitList(result)
                    binding.recyclerView.adapter = adapter


                })
            } else {
                viewModel.searchAllCity()
                viewModel.cityList.observe(this, Observer { result ->
                    adapter.submitList(result)
                    binding.recyclerView.adapter = adapter


                })
                /* viewModel.cities.observe(this, Observer { result ->
                     adapter.submitList(result)
                     binding.recyclerView.adapter = adapter
                     // Log.e("result", "==>$result")
                 })*/
            }
            viewModel.loading.observe(this, Observer { loading ->
                binding.progressBar.isVisible = loading
            })

        }

    }


}