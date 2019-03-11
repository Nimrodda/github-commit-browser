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

@AutoValue
public abstract class File implements Parcelable {
    public static File create(@Nullable String sha,
                                @Nullable String filename,
                                @Nullable String status,
                                long additions,
                                long deletions,
                                long changes,
                                @Nullable String patch) {
        return new AutoValue_File(sha, filename, status, additions, deletions, changes, patch);
    }

    public static JsonAdapter<File> jsonAdapter(Moshi moshi) {
        return new AutoValue_File.MoshiJsonAdapter(moshi);
    }

    @Nullable public abstract String sha();
    @Nullable public abstract String filename();
    @Nullable public abstract String status();
    public abstract long additions();
    public abstract long deletions();
    public abstract long changes();
    @Nullable public abstract String patch();
}
