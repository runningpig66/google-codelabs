package com.example.reply.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * A class which represents an account
 */
data class Account(
    /** Unique ID of a user **/
    val id: Long,
    /** User's first name **/
    @get:StringRes val firstName: Int,
    /** User's last name **/
    @get:StringRes val lastName: Int,
    /** User's email address **/
    @get:StringRes val email: Int,
    /** User's avatar image resource id **/
    @get:DrawableRes val avatar: Int
)
