package com.lorentzos.slicknode.channel

import android.net.Uri
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Wearable.ChannelApi
import com.lorentzos.slicknode.internal.toObservable

/**
 *
 */
object ChannelObservable {


  /**
   * Sends a file to the specified node
   */
  fun sendFile(googleApiClient: GoogleApiClient, nodeId: String, messagePath: String, uri: Uri) =
      getChannel(googleApiClient, nodeId, messagePath)
          .flatMap {
            it.sendFile(googleApiClient, uri).toObservable()
                .doOnUnsubscribe { it.close(googleApiClient) }
          }

  /**
   * Receives a file from the specified node
   */
  fun receiveFile(googleApiClient: GoogleApiClient, nodeId: String, messagePath: String, uri: Uri, append: Boolean = false) =
      getChannel(googleApiClient, nodeId, messagePath)
          .flatMap {
            it.receiveFile(googleApiClient, uri, append).toObservable()
                .doOnUnsubscribe { it.close(googleApiClient) }
          }


  /**
   * Returns the channel for the given node and path
   */
  private fun getChannel(googleApiClient: GoogleApiClient, nodeId: String, messagePath: String) =
      ChannelApi.openChannel(googleApiClient, nodeId, messagePath).toObservable().map { it.channel }


}