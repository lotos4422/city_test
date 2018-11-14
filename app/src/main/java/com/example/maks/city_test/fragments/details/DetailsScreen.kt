package com.example.maks.city_test.fragments.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.maks.city_test.R
import kotlinx.android.synthetic.main.fragment_details_screen.*


class DetailsScreen : Fragment() {

    lateinit var model: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_details_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (model.pagerAdapter == null)
            model.pagerAdapter = PagerFragmentAdapter(fragmentManager!!,
                Bundle().apply { this.putParcelable("data",arguments?.getParcelable("data"));this.putInt("category",arguments?.getInt("category")!!) })

        pager.adapter = model.pagerAdapter
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionMap -> {
                    pager.setCurrentItem(0, true)
                }
                R.id.actionCategory -> {
                    pager.setCurrentItem(1, true)
                }
            }
            true
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            DetailsScreen()

    }
}
