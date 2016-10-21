package com.lorentzos.slicknode.channel

import android.net.Uri
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Wearable.ChannelApi
import com.lorentzos.slicknode.internal.toSingle

/**
 * Contains getting and putting actions for the [ChannelApi]
 */
object ChannelObservable {

  /**
   * Sends a file to the specified node
   */
  @Suppress("unused")
  fun sendFile(googleApiClient: GoogleApiClient, nodeId: String, messagePath: String, uri: Uri) =
      getChannel(googleApiClient, nodeId, messagePath)
          .flatMap {
            it.sendFile(googleApiClient, uri)
                .toSingle()
                .doOnUnsubscribe { it.close(googleApiClient) }
          }

  /**
   * Receives a file from the specified node
   */
  @Suppress("unused")
  fun receiveFile(googleApiClient: GoogleApiClient, nodeId: String, messagePath: String, uri: Uri, append: Boolean = false) =
      getChannel(googleApiClient, nodeId, messagePath)
          .flatMap {
            it.receiveFile(googleApiClient, uri, append)
                .toSingle()
                .doOnUnsubscribe { it.close(googleApiClient) }
          }


  /**
   * Returns the channel for the given node and path
   */
  private fun getChannel(googleApiClient: GoogleApiClient, nodeId: String, messagePath: String) =
      ChannelApi.openChannel(googleApiClient, nodeId, messagePath).toSingle().map { it.channel }


}