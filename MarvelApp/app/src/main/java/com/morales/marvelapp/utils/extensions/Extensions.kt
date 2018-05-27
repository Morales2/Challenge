package com.morales.marvelapp.utils.extensions

import android.os.Parcel
import android.os.Parcelable
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@Throws(NoSuchAlgorithmException::class)
fun String.md5(): String {
    // Create MD5 Hash
    val digest = MessageDigest
            .getInstance("MD5")
    digest.update(this.toByteArray())
    val messageDigest = digest.digest()
    val bigInt = BigInteger(1, messageDigest)
    var hashText = bigInt.toString(16)
    // Now we need to zero pad it if you actually want the full 32
    // chars.
    while (hashText.length < 32) {
        hashText = "0$hashText"
    }
    return hashText
}

inline fun <reified T : Parcelable> createParcel(
        crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }