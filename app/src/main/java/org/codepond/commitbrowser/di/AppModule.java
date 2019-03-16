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

package org.codepond.commitbrowser.di;

import android.content.Context;
import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.codepond.commitbrowser.App;
import org.codepond.commitbrowser.BuildConfig;
import org.codepond.commitbrowser.api.GithubApi;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class AppModule {
    private static final long CACHE_SIZE = 20 * 1024 * 1024; // 20 MB
    private static final String HEADER_LINK = "link";

    @Provides Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Provides GithubApi provideGithubApi(Context context, OkHttpClient.Builder okHttpBuilder) {
        OkHttpClient okHttp = okHttpBuilder
                .cache(new Cache(context.getCacheDir(), CACHE_SIZE))
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    String linkHeader = request.header(HEADER_LINK);
                    Response response = chain.proceed(request);
                    return response;
                }).build();
        Moshi moshi = new Moshi.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttp)
                .build();
        return retrofit.create(GithubApi.class);
    }
}
