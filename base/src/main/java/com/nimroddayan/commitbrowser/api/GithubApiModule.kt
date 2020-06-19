/*
 * Copyright 2020 Nimrod Dayan nimroddayan.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nimroddayan.commitbrowser.api

import android.content.Context
import com.nimroddayan.commitbrowser.base.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val CACHE_SIZE = 20 * 1024 * 1024 // 20 MB
    .toLong()
private const val HEADER_LINK = "link"


@InstallIn(ApplicationComponent::class)
@Module
object GithubApiModule {
    @Provides
    fun provideGithubApi(
        @ApplicationContext context: Context,
        okHttpBuilder: OkHttpClient.Builder
    ): GithubApi {
        val okHttp = okHttpBuilder
            .cache(Cache(context.cacheDir, CACHE_SIZE))
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                val linkHeader =
                    request.header(HEADER_LINK)
                val response = chain.proceed(request)
                response
            }).build()
        val moshi = Moshi.Builder().build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttp)
            .build()
        return retrofit.create(GithubApi::class.java)
    }
}
