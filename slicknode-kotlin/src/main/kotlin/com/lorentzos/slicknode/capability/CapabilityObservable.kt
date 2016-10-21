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


package com.lorentzos.slicknode.capability

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.CapabilityApi.FILTER_ALL
import com.google.android.gms.wearable.CapabilityApi.FILTER_REACHABLE
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.Wearable.CapabilityApi
import com.lorentzos.slicknode.internal.toSingle
import rx.Single

/**
 * Contains [CapabilityApi] actions for getting info and updating the Android Wear network with a
 * capability.
 */
object CapabilityObservable {


  /**
   * Announces that a capability has become available on the local node.
   */
  @Suppress("unused")
  fun addLocal(googleApiClient: GoogleApiClient, capability: String) =
      CapabilityApi.addLocalCapability(googleApiClient, capability).toSingle()

  /**
   * Announces that a capability is no longer available on the local node.
   *
   * Note: this will not remove any capabilities announced in the Manifest for an app.
   */
  @Suppress("unused")
  fun removeLocal(googleApiClient: GoogleApiClient, capability: String) =
      CapabilityApi.removeLocalCapability(googleApiClient, capability).toSingle()

  /**
   * Returns information about all capabilities, including the nodes that declare those capabilities.
   *
   * All nodes will be returned.
   *
   * The local node will never be returned in the set of nodes.
   */
  @Suppress("unused")
  fun getInfoAll(googleApiClient: GoogleApiClient) =
      getAllCapabilitiesObservable(googleApiClient, FILTER_ALL)

  /**
   * Returns information about all capabilities, including the nodes that declare those capabilities.
   *
   * Only those that are currently reachable by this node will be returned.
   *
   * The local node will never be returned in the set of nodes.
   */
  @Suppress("unused")
  fun getInfoReachable(googleApiClient: GoogleApiClient) =
      getAllCapabilitiesObservable(googleApiClient, FILTER_REACHABLE)

  /**
   * Returns information about a capability, including the nodes that declare that capability.
   *
   * All nodes will be returned.
   *
   * The local node will never be returned in the set of nodes.
   */
  @Suppress("unused")
  fun getInfoAll(googleApiClient: GoogleApiClient, capability: String) =
      getCapability(googleApiClient, capability, FILTER_ALL)

  /**
   * Returns information about a capability, including the nodes that declare that capability.
   *
   * Only those that are currently reachable by this node will be returned.
   *
   * The local node will never be returned in the set of nodes.
   */
  @Suppress("unused")
  fun getInfoReachable(googleApiClient: GoogleApiClient, capability: String) =
      getCapability(googleApiClient, capability, FILTER_REACHABLE)

  private fun getAllCapabilitiesObservable(googleApiClient: GoogleApiClient, filter: Int): Single<MutableMap<String, CapabilityInfo>> {
    return CapabilityApi.getAllCapabilities(googleApiClient, filter).toSingle().map { it.allCapabilities }
  }

  private fun getCapability(googleApiClient: GoogleApiClient, capability: String, filter: Int): Single<CapabilityInfo> {
    return CapabilityApi.getCapability(googleApiClient, capability, filter).toSingle().map { it.capability }
  }

}

