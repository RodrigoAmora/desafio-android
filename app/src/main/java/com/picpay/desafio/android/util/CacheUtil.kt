package com.picpay.desafio.android.util

import com.orhanobut.hawk.Hawk


class CacheUtil<T> {
    fun getCache(key: String?): T? {
        return Hawk.get(key)
    }

    fun saveCache(key: String?, t: T) {
        Hawk.put<Any>(key, t)
    }

    fun removeCache(key: String?) {
        Hawk.delete(key)
    }
}