package com.morales.marvelapp.ui.characters

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.Menu
import com.morales.marvelapp.R
import com.squareup.picasso.Picasso
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_characters.*
import javax.inject.Inject

class CharactersActivity : DaggerAppCompatActivity() {

    private lateinit var pagerAdapter: CharacterPagerAdapter

    @Inject
    lateinit var mPresenter: CharactersContract.Presenter

    @Inject
    lateinit var charactersFragmentProvider: Lazy<CharactersFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
        Picasso.with(this).setIndicatorsEnabled(true)

        setSupportActionBar(toolbar)

        tab_layout.apply {
            addTab(newTab().setText(R.string.characters))
            addTab(newTab().setText(R.string.favorites))
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab) {
                    view_pager.currentItem = tab.position
                    toolbar.title = tab.text
                }
            })
        }

        val charactersFragment = charactersFragmentProvider.get()
        val favoritesFragment = Fragment()

        view_pager.apply {
            offscreenPageLimit = tab_layout.tabCount
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
            pagerAdapter = CharacterPagerAdapter(supportFragmentManager).apply {
                addFragment(charactersFragment)
                addFragment(favoritesFragment)
            }
            adapter = pagerAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_characters, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search_button)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
        }
    }
}
