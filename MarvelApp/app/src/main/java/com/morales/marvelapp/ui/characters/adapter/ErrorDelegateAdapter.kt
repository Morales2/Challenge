package com.morales.marvelapp.ui.characters.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.morales.marvelapp.R
import com.morales.marvelapp.ui.base.adapters.ViewType
import com.morales.marvelapp.ui.base.adapters.ViewTypeDelegateAdapter
import com.morales.marvelapp.utils.extensions.inflate

class ErrorDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.characters_item_error))
}