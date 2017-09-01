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

package org.codepond.commitbrowser.api;

import android.support.annotation.NonNull;

import org.codepond.commitbrowser.model.CommitResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GithubApi {
    @GET("repos/android/platform_build/commits?page={page}") Observable<List<CommitResponse>> getCommits(@Path("page") int page);
    @GET("repos/android/platform_build/commits/{sha}") Observable<CommitResponse> getCommit(@Path("sha") @NonNull String sha);
}
