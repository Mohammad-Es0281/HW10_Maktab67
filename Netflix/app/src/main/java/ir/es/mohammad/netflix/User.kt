package ir.es.mohammad.netflix

import android.graphics.drawable.Drawable

object User {
    var registered = false
    lateinit var profilePicId: Drawable
    lateinit var fullName: String
    lateinit var email: String
    lateinit var username: String
    lateinit var phoneNumber: String
}