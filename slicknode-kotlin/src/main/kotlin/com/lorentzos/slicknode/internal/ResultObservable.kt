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

package com.lorentzos.slicknode.internal

import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.Result
import rx.Observable

/**
 * An [Observable] that returns a [Result] for a given [PendingResult] action.
 */
class ResultObservable<T : Result> : Observable<T> {

  internal constructor(action: PendingResult<T>) : super(OnSubscribe {
    if (it.isUnsubscribed) {
      return@OnSubscribe
    }

    action.setResultCallback(SingleResultLambda(it))
  })

}

/**
 * Converts a given [PendingResult] into an [Observable].
 */
fun <R : Result> PendingResult<R>.toObservable() = ResultObservable(this)

