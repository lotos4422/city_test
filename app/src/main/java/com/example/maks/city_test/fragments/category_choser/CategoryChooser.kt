package com.example.maks.city_test.fragments.category_choser

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.maks.city_test.R
import kotlinx.android.synthetic.main.fragment_category_choser.*


class CategoryChooser : Fragment(), OnCardClickListener {
    override fun onCardClick(id: Int) {
        Navigation.findNavController(this.view!!)
            .navigate(R.id.detailsScreen, Bundle().apply {
                this.putParcelable("data", model.citie)
                this.putInt("category", id + 6)
            })
    }

    lateinit var model: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        model.citie = arguments?.getParcelable("data")

        return inflater.inflate(R.layout.fragment_category_choser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(context!!, 2)
        val adapter = RvAdapter()
        adapter.onCardClickListener = this
        recyclerView.adapter = adapter

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
            CategoryChooser()
    }
}
