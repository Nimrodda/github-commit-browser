/*
 * Copyright 2019 Nimrod Dayan nimroddayan.com
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

package org.codepond.commitbrowser.api

import org.codepond.commitbrowser.model.CommitResponse

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface GithubApi {
    @GET("repos/android/platform_build/commits")
    fun getCommits(@Query("page") page: Int): Observable<List<CommitResponse>>

    @GET("repos/android/platform_build/commits/{sha}")
    fun getCommit(@Path("sha") sha: String): Observable<CommitResponse>
}
