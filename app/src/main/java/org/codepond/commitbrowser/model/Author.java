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
public abstract class Author implements Parcelable {
    public static Author create(@Nullable String name,
                                @Nullable String email,
                                @Nullable String date) {
        return new AutoValue_Author(name, email, date);
    }

    public static JsonAdapter<Author> jsonAdapter(Moshi moshi) {
        return AutoValue_Author.jsonAdapter(moshi);
    }

    @Nullable public abstract String name();
    @Nullable public abstract String email();
    @Nullable public abstract String date();
}
