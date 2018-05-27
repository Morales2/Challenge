package com.morales.marvelapp.ui.characters

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morales.marvelapp.R
import com.morales.marvelapp.data.Character
import com.morales.marvelapp.data.source.MarvelPage
import com.morales.marvelapp.di.ActivityScoped
import com.morales.marvelapp.ui.base.RxBaseFragment
import com.morales.marvelapp.ui.base.adapters.AdapterConstants
import com.morales.marvelapp.ui.characters.adapter.CharacterAdapter
import com.morales.marvelapp.utils.InfiniteScrollListener
import com.morales.marvelapp.utils.extensions.inflate
import kotlinx.android.synthetic.main.fragment_characters.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

@ActivityScoped
class CharactersFragment @Inject constructor() : RxBaseFragment(), CharactersContract.View {

    override fun setLoadingIndicator(active: Boolean) {

    }

    override fun showAllCharacters(characters: List<Character>) {

    }

    override fun showFavouriteCharacters(characters: List<Character>) {

    }

    override fun showCharacterDetailsUi(characterId: String) {

    }

    override fun showLoadingCharactersError() {

    }

    @Inject
    override lateinit var mPresenter: CharactersContract.Presenter

    companion object {
        private const val KEY_MARVEL_CHARACTERS = "marvelCharacters"
    }

    @Inject
    lateinit var charactersManager: CharactersManager
    private var marvelCharacters: MarvelPage? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_characters)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        characters_list.apply {
            setHasFixedSize(true)
            val gridLayout = GridLayoutManager(context, 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (adapter.getItemViewType(position)) {
                            AdapterConstants.CHARACTERS -> 1
                            else -> 2
                        }
                    }
                }
                layoutManager = this
            }
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestCharacters() }, gridLayout))
        }

        swipe_container.setOnRefreshListener {
            marvelCharacters = null
            subscriptions.clear()
            requestCharacters()
        }

        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_MARVEL_CHARACTERS)) {
            marvelCharacters = savedInstanceState.get(KEY_MARVEL_CHARACTERS) as MarvelPage
            (characters_list.adapter as CharacterAdapter).clearAndAddCharacters(marvelCharacters!!.charactersItem)
        } else {
            requestCharacters()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val characters = (characters_list.adapter as CharacterAdapter).getCharacters()
        if (marvelCharacters != null && characters.isNotEmpty()) {
            outState.putParcelable(KEY_MARVEL_CHARACTERS, marvelCharacters?.copy(charactersItem = characters))
        }
    }

    private fun initAdapter() {
        if (characters_list.adapter == null) {
            characters_list.adapter = CharacterAdapter()
        }
    }

    private fun requestCharacters() {
        val actualOffset = marvelCharacters?.offset ?: 0
        val actualLimit = marvelCharacters?.limit ?: 0
        val nextOffset = actualOffset + actualLimit
        val subscription = charactersManager.getCharacters(nextOffset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedCharacters ->
                            marvelCharacters = retrievedCharacters
                            (characters_list.adapter as CharacterAdapter).addCharacters(retrievedCharacters.charactersItem)
                            swipe_container.isRefreshing = false
                        },
                        { e ->
                            (characters_list.adapter as CharacterAdapter).placeEmptyItem()
                            swipe_container.isRefreshing = false
                            Log.d(getString(R.string.app_name), e.stackTrace.toString())
                        })
        subscriptions.add(subscription)
    }
}
