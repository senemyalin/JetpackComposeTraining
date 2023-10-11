package com.senemyalin.jetpackcomposetraining.common

import com.google.gson.Gson

object Extensions {
    inline fun <reified T> T.toJson(): String {
        return try {
            Gson().toJson(this)
        } catch (ex: Exception) {
            ""
        }
    }

    inline fun <reified T> String.fromJson(): T? {
        return try {
            Gson().fromJson(this, T::class.java)
        } catch (ex: Exception) {
            null
        }
    }
}