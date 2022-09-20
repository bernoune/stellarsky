package com.bernoune.stellarsky.common.extensions

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset



fun Context.loadJSONFromAsset(): String? {
    val json: String?
    json = try {
        val inputStream: InputStream = this.assets.open("fr_lat_lng_city.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charset.forName("UTF-8"))
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }
    return json
}
