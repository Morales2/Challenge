package com.morales.marvelapp.ui.characters.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.morales.marvelapp.R
import com.morales.marvelapp.data.source.MarvelCharacterItem
import com.morales.marvelapp.ui.base.adapters.ViewType
import com.morales.marvelapp.ui.base.adapters.ViewTypeDelegateAdapter
import com.morales.marvelapp.utils.extensions.inflate
import com.morales.marvelapp.utils.extensions.loadImg
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as MarvelCharacterItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.character_item)) {

        fun bind(item: MarvelCharacterItem) = with(itemView) {
            thumbnail.loadImg(item.thumbnail)
            name.text = item.name
        }
    }
}