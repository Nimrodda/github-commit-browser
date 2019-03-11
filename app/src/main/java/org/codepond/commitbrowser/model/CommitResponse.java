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

package org.codepond.commitbrowser.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class CommitResponse implements Parcelable {
    public static CommitResponse create(@Nullable String sha,
                                        @Nullable Commit commit,
                                        @Nullable Stats stats,
                                        @Nullable Author author,
                                        @Nullable List<File> files) {
        return new AutoValue_CommitResponse(sha, commit, stats, author, files);
    }

    public static JsonAdapter<CommitResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_CommitResponse.MoshiJsonAdapter(moshi);
    }

    @Nullable public abstract String sha();
    @Nullable public abstract Commit commit();
    @Nullable public abstract Stats stats();
    @Nullable public abstract Author author();
    @Nullable public abstract List<File> files();
}
