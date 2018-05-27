package com.morales.marvelapp.ui.base

import dagger.android.support.DaggerFragment
import rx.subscriptions.CompositeSubscription

open class RxBaseFragment : DaggerFragment() {

    protected var subscriptions = CompositeSubscription()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        subscriptions.clear()
    }
}