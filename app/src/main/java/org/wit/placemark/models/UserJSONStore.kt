package org.wit.placemark.models

import android.content.Context
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.placemark.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_USER_FILE = "user.json"
val userListType: Type = object : TypeToken<ArrayList<UserModel>>() {}.type

fun generateRandomUserId(): Long {
    return Random().nextLong()
}
class UserJSONStore(private val context: Context) : UserStore {

    var users = mutableListOf<UserModel>()

    init {
        if (exists(context, JSON_USER_FILE)) {
            deserialize()
        }
    }

    override fun authenticate(email: String, password: String): Boolean {
        val user = users.find { it.email == email  }
        return password == user?.password
    }

    override fun findUserPlacemark(email: String): MutableList<PlacemarkModel> {
        val user = users.find { it.email == email  }
        return user!!.placemaks
    }

    override fun findAll(): MutableList<UserModel> {
        return users
    }

    override fun findByEmail(email: String): UserModel? {
        return users.find { it.email == email }
    }

    override fun create(user: UserModel) {
        user.id = generateRandomUserId()
        users.add(user)
        serialize()
    }


    override fun update(user: UserModel) {
        val userList = findAll() as ArrayList<UserModel>
        val foundUser = userList.find { u -> u.id == user.id }
        foundUser?.apply {
            name = user.name
            email = user.email
            password = user.password
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(users, userListType)
        write(context, JSON_USER_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_USER_FILE)
        users = gsonBuilder.fromJson(jsonString, userListType)
    }

    override fun delete(user: UserModel) {
        users.remove(user)
        serialize()
    }
}
