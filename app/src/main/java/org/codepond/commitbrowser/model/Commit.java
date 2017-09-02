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

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import javax.annotation.Nullable;

@AutoValue
public abstract class Commit implements Parcelable {
    public static Commit create(@Nullable Author author,
                                @Nullable String message) {
        return new AutoValue_Commit(author, message);
    }

    public static JsonAdapter<Commit> jsonAdapter(Moshi moshi) {
        return new AutoValue_Commit.MoshiJsonAdapter(moshi);
    }

    @Nullable public abstract Author author();
    @Nullable public abstract String message();
}
