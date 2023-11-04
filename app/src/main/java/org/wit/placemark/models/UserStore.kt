package org.wit.placemark.models

interface UserStore {
    fun authenticate(email: String, password: String): Boolean
    fun findByEmail(email:String) : UserModel?
    fun findAll(): List<UserModel>
    fun create(user: UserModel)
    fun update(user: UserModel)
    fun delete(user: UserModel)

    fun findUserPlacemark(email:String): List<PlacemarkModel>
}