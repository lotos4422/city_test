package com.example.maks.city_test.fragments.details.details_frag

import androidx.lifecycle.ViewModel
import com.example.maks.city_test.json_objects.City

class DetailsFragViewModel: ViewModel() {
    var detailsFragmentPresenter=DetailsFragmentPresenter()
    var city: City? = null
    var category: Int? = null

}
