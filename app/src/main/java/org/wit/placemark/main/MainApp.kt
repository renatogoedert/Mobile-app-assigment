package org.wit.placemark.main

import android.app.Application
import org.wit.placemark.models.PlacemarkJSONStore
import org.wit.placemark.models.PlacemarkMemStore
import org.wit.placemark.models.PlacemarkStore
import org.wit.placemark.models.UserJSONStore
import org.wit.placemark.models.UserModel
import org.wit.placemark.models.UserStore
import timber.log.Timber
import timber.log.Timber.Forest.i

class MainApp : Application() {

    lateinit var placemarks: PlacemarkStore
    lateinit var users: UserStore
    lateinit var loggedUser: String

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        placemarks = PlacemarkJSONStore(applicationContext)
        users = UserJSONStore(applicationContext)
        loggedUser = ""
        i("Placemark started")
    }
}