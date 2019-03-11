/*
 * Copyright 2017 Nimrod Dayan CodePond.org
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codepond.commitbrowser.common.ui

import android.support.design.widget.Snackbar
import android.view.View

import org.codepond.commitbrowser.R

import rx.Observable
import rx.subjects.PublishSubject
import timber.log.Timber

class NetworkErrorSnackBar(private val root: View) {
    private val retrySubject = PublishSubject.create<Void>()

    fun show(throwables: Observable<out Throwable>): Observable<*> {
        return throwables.flatMap { throwable ->
            Timber.d("Showing NetworkErrorSnackBar. Reason: %s", throwable.message)
            Snackbar
                    .make(root, R.string.network_error_message, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry) { v ->
                        Timber.v("Retry button clicked")
                        retrySubject.onNext(null)
                    }.show()
            retrySubject
        }
    }
}
