package com.lorentzos.slicknode.channel

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Channel
import com.google.android.gms.wearable.ChannelApi
import com.google.android.gms.wearable.Wearable.ChannelApi
import rx.Observable
import rx.subscriptions.Subscriptions

/**
 *
 */
class ChannelListenerObservable : Observable<Channel> {

  constructor(googleApiClient: GoogleApiClient, capability: String) : super(OnSubscribe {
    if (it.isUnsubscribed) {
      return@OnSubscribe
    }


    val listener = object : ChannelApi.ChannelListener {
      override fun onOutputClosed(p0: Channel?, p1: Int, p2: Int) {
        throw UnsupportedOperationException()
      }

      override fun onChannelOpened(p0: Channel) {
        it.onNext(p0)
      }

      override fun onInputClosed(p0: Channel?, p1: Int, p2: Int) {
        throw UnsupportedOperationException()
      }

      override fun onChannelClosed(p0: Channel?, p1: Int, p2: Int) {
        throw UnsupportedOperationException()
      }

    }

    ChannelApi.addListener(googleApiClient, listener)

    it.add(Subscriptions.create { ChannelApi.removeListener(googleApiClient, listener) })

  })

}
