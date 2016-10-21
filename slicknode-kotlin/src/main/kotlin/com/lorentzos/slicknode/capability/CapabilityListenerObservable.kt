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

@file:Suppress("unused")

package com.lorentzos.slicknode.capability

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.Wearable.CapabilityApi
import com.lorentzos.slicknode.internal.MultipleEmissionLambda
import rx.Observable
import rx.subscriptions.Subscriptions

/**
 * An [Observable] which is notified when a specific capability is added to or removed from
 * the Wear network.
 */
class CapabilityListenerObservable : Observable<CapabilityInfo> {

  constructor(googleApiClient: GoogleApiClient, capability: String) : super(OnSubscribe {
    if (it.isUnsubscribed) {
      return@OnSubscribe
    }

    val listener = MultipleEmissionLambda(it)

    CapabilityApi.addCapabilityListener(googleApiClient, listener, capability)

    it.add(Subscriptions.create { CapabilityApi.removeListener(googleApiClient, listener) })
  })

}
