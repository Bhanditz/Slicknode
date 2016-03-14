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

import com.google.android.gms.common.api.Result
import com.lorentzos.slicknode.WearOperationException
import rx.Subscriber

/**
 * A lambda which emits a single item and then completes.
 */
internal class SingleResultLambda<T : Result>(private val subscriber: Subscriber<in T>) : (T) -> Unit {
  override fun invoke(child: T) {
    if (!child.status.isSuccess) {

      subscriber.onError(WearOperationException(child))
    }

    subscriber.onNext(child)
    subscriber.onCompleted()
  }
}