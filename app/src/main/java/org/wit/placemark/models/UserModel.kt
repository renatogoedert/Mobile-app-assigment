package org.wit.placemark.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(var id: Long = 0,
                     var name: String = "",
                     var email: String = "",
                     var password: String = "",
                     var placemaks: MutableList<PlacemarkModel>) : Parcelable