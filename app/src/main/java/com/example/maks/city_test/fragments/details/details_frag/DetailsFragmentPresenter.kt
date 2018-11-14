package com.example.maks.city_test.fragments.details.details_frag

import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.maks.city_test.MApplication
import com.example.maks.city_test.MainViewModel
import com.example.maks.city_test.R
import com.example.maks.city_test.fragments.details.details_frag.DetailsFragment
import com.example.maks.city_test.json_objects.Cities
import com.example.maks.city_test.json_objects.Companies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.details_recycler_view.view.*

class DetailsFragmentPresenter {


    var detailsFragment: DetailsFragment? = null
        set(value) {
            field = value
            if (field != null && companies != null)
                setAdapterToRv()
            else {
                if (field != null)
                    getAdapter()
            }
        }
    var companies: Companies? = null

    fun getAdapter() {
        try {
            MApplication.api.getCompaniesByCategory(
                detailsFragment!!.model.city?.id!!
                ,detailsFragment!!.model.category!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)
        } catch (e: Exception) {
            Log.d("TAG","T")
        }
    }

    var observer = object : DisposableSingleObserver<Companies>() {
        override fun onSuccess(t: Companies) {
            companies = t
            setAdapterToRv()

        }

        override fun onError(e: Throwable) {
            getAdapter()
        }
    }

    private fun setAdapterToRv() {
        val adapter = RvAdapter()
        adapter.data = companies?.data!!
        detailsFragment?.view?.recyclerView?.adapter = adapter
    }

}