package com.example.maks.city_test.fragments.city_chooser

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProviders
import com.example.maks.city_test.R
import com.example.maks.city_test.json_objects.Datum
import kotlinx.android.synthetic.main.fragment_citie_choser.*


class CitieChooser : Fragment() {

    lateinit var model: CitieViewModel
    val presenter: CitiePresenter?
        get() = model.citiePresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_citie_choser, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter?.citieChooser = this
    }

    override fun onPause() {
        super.onPause()
        model.citiePresenter.citieChooser = null

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        model = ViewModelProviders.of(this).get(CitieViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_continue.setOnClickListener { presenter?.getCategories() }
        country.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter?.setCountry((country.getItemAtPosition(p2) as Datum).name)
            }
        }

        region.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter?.setRegion((country.getItemAtPosition(country.selectedItemPosition) as Datum).cities[p2])
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CitieChooser()
    }
}
