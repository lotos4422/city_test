package com.example.maks.city_test.fragments.city_chooser

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.maks.city_test.MApplication
import com.example.maks.city_test.MainViewModel
import com.example.maks.city_test.R
import com.example.maks.city_test.json_objects.Cities
import com.example.maks.city_test.json_objects.City
import com.example.maks.city_test.json_objects.Datum
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.android.synthetic.main.fragment_citie_choser.view.*

class CitiePresenter {

    var city: City? = null

    var citieChooser: CitieChooser? = null
        set(value) {
            field = value
            if (field != null) {
                val mainModel = ViewModelProviders.of(citieChooser?.activity!!).get(MainViewModel::class.java)
                if (mainModel.cities == null)
                    updateCities(mainModel)
                else initSpinners(mainModel.cities!!)
            }
        }

    fun setCountry(string: String) {
        val mainModel = ViewModelProviders.of(citieChooser?.activity!!).get(MainViewModel::class.java)
        val cities =
            mainModel.cities!!.data.find { it.name.equals(string) }
        val regionAdapter =
            ArrayAdapter<City>(citieChooser?.context, R.layout.support_simple_spinner_dropdown_item, cities!!.cities)
        citieChooser?.view!!.region.adapter = regionAdapter
    }

    fun setRegion(city: City) {
        this.city = city
    }


    fun updateCities(mainModel: MainViewModel) {
        try {
            MApplication.api.getCites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)
        } catch (e: Exception) {
        }
    }

    var observer = object : DisposableSingleObserver<Cities>() {
        override fun onSuccess(t: Cities) {
            val mainModel = ViewModelProviders.of(citieChooser?.activity!!).get(MainViewModel::class.java)
            mainModel.cities = t
            initSpinners(mainModel.cities!!)
        }

        override fun onError(e: Throwable) {
            citieChooser = citieChooser
        }
    }

    fun initSpinners(cities: Cities) {
        var country = citieChooser?.view?.country
        val coutnryAdapter =
            ArrayAdapter<Datum>(citieChooser?.context, R.layout.support_simple_spinner_dropdown_item, cities.data)
        country?.adapter = coutnryAdapter
    }

    fun getCategories() {
        if (city != null)
            Navigation.findNavController(citieChooser!!.view!!)
                .navigate(R.id.categoryChooser, Bundle().apply { this.putParcelable("data", city) })

    }
}