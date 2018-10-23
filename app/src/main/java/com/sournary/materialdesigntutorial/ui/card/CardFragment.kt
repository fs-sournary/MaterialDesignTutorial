package com.sournary.materialdesigntutorial.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.Weather
import com.sournary.materialdesigntutorial.ui.BaseFragment
import com.sournary.materialdesigntutorial.util.setSupportActionBar

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
class CardFragment : BaseFragment() {

    private lateinit var rootView: View
    private lateinit var weatherRecyclerView: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_card, container, false)
        initToolbar()
        initWeatherRecycler()
        initExpandButton()
        return rootView
    }

    private fun initToolbar() {
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar_card)
        setSupportActionBar(toolbar) {}
    }

    private fun initWeatherRecycler() {
        weatherRecyclerView = rootView.findViewById(R.id.recycler)
        weatherAdapter = WeatherAdapter()
        weatherRecyclerView.adapter = weatherAdapter
    }

    private fun initExpandButton() {
        val expandButton = rootView.findViewById<MaterialButton>(R.id.button_expand)
        expandButton.setOnClickListener {
            if (weatherRecyclerView.visibility == View.GONE) {
                weatherRecyclerView.visibility = View.VISIBLE
                expandButton.text = "COLLAPSE"
            } else {
                weatherRecyclerView.visibility = View.GONE
                expandButton.text = "EXPAND"
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weatherAdapter.submitList(getWeatherList())
    }

    private fun getWeatherList(): List<Weather> {
        val weatherList = mutableListOf<Weather>()

        val tuesday = Weather(
            "Tuesday",
            R.drawable.ic_sun,
            "/12",
            "24"
        )
        weatherList.add(tuesday)

        val wednesday = Weather(
            "Wednesday",
            R.drawable.ic_snowing,
            "/14",
            "22"
        )
        weatherList.add(wednesday)

        val thursday = Weather(
            "Thursday",
            R.drawable.ic_sun,
            "/18",
            "25"
        )
        weatherList.add(thursday)

        val friday = Weather(
            "Friday",
            R.drawable.ic_sun,
            "/16",
            "20"
        )
        weatherList.add(friday)

        val saturday = Weather(
            "Saturday",
            R.drawable.ic_sunny_snowing,
            "/10",
            "16"
        )
        weatherList.add(saturday)

        return weatherList
    }

    override fun onBackPress() {
        NavHostFragment.findNavController(this).popBackStack()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPress()
        }
        return true
    }
}
