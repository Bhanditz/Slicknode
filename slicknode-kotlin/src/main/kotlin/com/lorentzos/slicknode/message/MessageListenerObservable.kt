/*
 * Copyright 2016 Dionysis Lorentzos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lorentzos.slicknode.message

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.Wearable.MessageApi
import com.lorentzos.slicknode.internal.MultipleEmissionLambda
import rx.Observable
import rx.subscriptions.Subscriptions

/**
 * An [Observable] which registers a listener on [MessageApi] to receive events.
 *
 * It automatically removes the listener when the subscriber unsubscribes.
 */
class MessageListenerObservable : Observable<MessageEvent> {

  constructor(googleApiClient: GoogleApiClient) : super(Observable.OnSubscribe {
    if (it.isUnsubscribed) {
      return@OnSubscribe
    }

    val listener = MultipleEmissionLambda(it)

    MessageApi.addListener(googleApiClient, listener)

    it.add(Subscriptions.create { MessageApi.removeListener(googleApiClient, listener) })

  })

}