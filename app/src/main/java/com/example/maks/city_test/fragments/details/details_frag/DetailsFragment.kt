package com.example.maks.city_test.fragments.details.details_frag

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maks.city_test.R
import com.example.maks.city_test.fragments.city_chooser.CitieViewModel
import kotlinx.android.synthetic.main.details_recycler_view.*

class DetailsFragment : Fragment() {

    lateinit var model: DetailsFragViewModel
    lateinit var bundle: Bundle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.details_recycler_view, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        model = ViewModelProviders.of(this).get(DetailsFragViewModel::class.java)
        model.city = bundle?.getParcelable("data")
        model.category = bundle?.getInt("category")


    }

    override fun onResume() {
        super.onResume()
        model.detailsFragmentPresenter.detailsFragment = this
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        model.detailsFragmentPresenter.getAdapter()
    }


}