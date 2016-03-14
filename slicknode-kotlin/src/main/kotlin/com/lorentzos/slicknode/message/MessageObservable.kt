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
import com.google.android.gms.wearable.Wearable.MessageApi
import com.lorentzos.slicknode.internal.toObservable

/**
 * Contains actions to send Messages to wear graph nodes.
 */
class MessageObservable {

  companion object {

    /**
     * Sends a message to the specified node without any data
     */
    fun sendMessage(googleApiClient: GoogleApiClient, nodeId: String, messagePath: String) =
        sendMessage(googleApiClient, nodeId, messagePath, null)

    /**
     * Sends byte[] data message to the specified node.
     */
    fun sendMessage(googleApiClient: GoogleApiClient, nodeId: String, messagePath: String, bytes: ByteArray?) =
        MessageApi.sendMessage(googleApiClient, nodeId, messagePath, bytes).toObservable().map { it.requestId }

  }
}