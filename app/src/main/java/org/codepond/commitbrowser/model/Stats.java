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

@AutoValue
public abstract class Stats implements Parcelable {
    public static Stats create(long total,
                                long additions,
                                long deletions) {
        return new AutoValue_Stats(total, additions, deletions);
    }

    public static JsonAdapter<Stats> jsonAdapter(Moshi moshi) {
        return new AutoValue_Stats.MoshiJsonAdapter(moshi);
    }

    public abstract long total();
    public abstract long addition();
    public abstract long deletion();
}
