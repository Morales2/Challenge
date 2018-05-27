package com.morales.marvelapp.data.source

import android.os.Parcel
import android.os.Parcelable
import com.morales.marvelapp.data.source.api.Image
import com.morales.marvelapp.ui.base.adapters.AdapterConstants
import com.morales.marvelapp.ui.base.adapters.ViewType
import com.morales.marvelapp.utils.extensions.createParcel

data class MarvelPage(
        val offset: Int,
        val limit: Int,
        val charactersItem: List<MarvelCharacterItem>
) : Parcelable {

    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { MarvelPage(it) }
    }

    protected constructor(parcelIn: Parcel) : this(
            parcelIn.readInt(),
            parcelIn.readInt(),
            mutableListOf<MarvelCharacterItem>().apply {
                parcelIn.readTypedList(this, MarvelCharacterItem.CREATOR)
            }
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeInt(offset)
            writeInt(limit)
            writeTypedList(charactersItem)
        }
    }

    override fun describeContents() = 0
}

data class MarvelCharacterItem(
        val id: Int,
        val name: String,
        val description: String,
        val thumbnail: Image,
        val favorite: Boolean
) : ViewType, Parcelable {

    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { MarvelCharacterItem(it) }
    }

    private constructor(parcelIn: Parcel) : this(
            parcelIn.readInt(),
            parcelIn.readString(),
            parcelIn.readString(),
            Image(parcelIn.readString(), parcelIn.readString()),
            parcelIn.readInt() != 0
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeInt(id)
            writeString(name)
            writeString(description)
            writeString(thumbnail.path)
            writeString(thumbnail.extension)
            writeInt(if (favorite) 1 else 0)
        }
    }

    override fun describeContents() = 0

    override fun getViewType() = AdapterConstants.CHARACTERS
}
