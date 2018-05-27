package com.morales.marvelapp.ui.characters.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.morales.marvelapp.data.source.MarvelCharacterItem
import com.morales.marvelapp.ui.base.adapters.AdapterConstants
import com.morales.marvelapp.ui.base.adapters.ViewType
import com.morales.marvelapp.ui.base.adapters.ViewTypeDelegateAdapter
import java.util.*

class CharacterAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    private val emptyItem = object : ViewType {
        override fun getViewType() = AdapterConstants.ERROR
    }

    init {
        delegateAdapters.apply {
            put(AdapterConstants.LOADING, LoadingDelegateAdapter())
            put(AdapterConstants.CHARACTERS, CharacterDelegateAdapter())
            put(AdapterConstants.ERROR, ErrorDelegateAdapter())
        }
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items[position].getViewType()
    }


    fun addCharacters(characters: List<MarvelCharacterItem>) {
        items.apply {
            val initPosition = size - 1
            removeAt(initPosition)
            notifyItemRemoved(initPosition)
            addAll(characters)
            add(loadingItem)
            notifyItemRangeChanged(initPosition, size + 1)
        }
    }

    fun placeEmptyItem() {
        items.apply {
            clear()
            add(emptyItem)
            notifyDataSetChanged()
        }
    }

    fun clearAndAddCharacters(characters: List<MarvelCharacterItem>) {
        items.apply {
            clear()
            notifyItemRangeRemoved(0, getLastPosition())
            addAll(characters)
            add(loadingItem)
            notifyItemRangeInserted(0, size)
        }
    }

    fun getCharacters(): List<MarvelCharacterItem> {
        return items
                .filter { it.getViewType() == AdapterConstants.CHARACTERS }
                .map { it as MarvelCharacterItem }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}