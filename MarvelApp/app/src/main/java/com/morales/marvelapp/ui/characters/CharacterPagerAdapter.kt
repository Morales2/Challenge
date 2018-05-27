package com.morales.marvelapp.ui.characters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class CharacterPagerAdapter(
        mFragmentManager: FragmentManager?,
        var mTabCount: Int = 2
) : FragmentStatePagerAdapter(mFragmentManager) {

    private val mFragmentList : MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mTabCount
    }

    fun addFragment(fragment: Fragment) {
        if(mFragmentList.count() < mTabCount) {
            mFragmentList.add(fragment)
        }
    }
}