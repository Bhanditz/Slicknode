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

package com.lorentzos.slicknode.node

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Wearable.NodeApi
import com.lorentzos.slicknode.internal.toObservable

/**
 * Contains actions to get the local and connected nodes.
 */
object NodeObservable {

	/**
	 * Returns the local node
	 */
	fun getLocalNode(googleApiClient: GoogleApiClient) =
			NodeApi.getLocalNode(googleApiClient).toObservable().map { it.node }

	/**
	 * Returns the connected nodes.
	 */
	fun getConnectedNodes(googleApiClient: GoogleApiClient) =
			NodeApi.getConnectedNodes(googleApiClient).toObservable().map { it.nodes }

}