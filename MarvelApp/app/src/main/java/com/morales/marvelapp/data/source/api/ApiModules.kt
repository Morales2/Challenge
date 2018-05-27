package com.morales.marvelapp.data.source.api

class CharacterDataWrapper (val data : CharacterDataContainer)

class CharacterDataContainer(
        val results : List<Character>,
        val offset : Int?,
        val limit : Int?
)

class Character (
        val id : Int,
        val name : String,
        val description : String,
        val thumbnail : Image
)

class Image (
        val path : String,
        val extension : String
)