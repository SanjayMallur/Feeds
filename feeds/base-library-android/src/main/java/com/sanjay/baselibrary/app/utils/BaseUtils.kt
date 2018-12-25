package com.sanjay.baselibrary.app.utils

/**
 * Created by Sanjay
 * Utility class to provide base methods
 */
object BaseUtils {

    fun isEmpty(list: List<*>?): Boolean {
        return list == null || list.isEmpty()
    }

    fun equals(str1: String?, str2: String?): Boolean {
        return if (str1 == null) str2 == null else str1 == str2
    }
}
