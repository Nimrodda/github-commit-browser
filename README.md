# Saving UI state with ViewModel SavedState and Dagger

This repository contains a sample project that demonstrate the topic discussed in my article about
ViewModel SavedState module, which part of the Android architecture lifecycle components.

## Trying out the sample

When you start the sample app you'll notice that the first screen doesn't do much at this point. This is expected as
I'm actively working on this sample and using it as a pet project for different experiments.

Nevertheless, the part that demonstrates how to use ViewModel SavedState with Dagger is fully working.
To try it out, first make sure to simulate a scenario where the system has killed the app process by
enabling _Don't Keep Activities_ in your test device. Then you need to execute the following command in terminal:

```shell
adb shell am start -n org.codepond.commitbrowser/.commitdetail.CommitDetailActivity --es "extra_commit_sha" "ab28c2d4eff4679ba31dfdc540832211c192bf0c"
```

This command will start `CommitDetailActivity` with an Intent that contains a commit SHA of AOSP, which will be requested from
Github API.

Pay attention to the logs in Logcat. You will see that when the activity is started for the first time, it will make the request
and then store the result in `SavedStateHandle` that is passed to `CommitDetailViewModel`. Then navigate away from the activity by simply
going to the home screen and then navigate back. You'll see in the logs that the previously stored response is loaded from
`SavedStateHandle` - indicating that the state has been restored after process death.

![](screenshots/screenshot1.png)

## Libraries and architecture

The app is utilizing the MVVM design pattern for the UI with the help of the following libraries:

* AndroidX
* Architecture Components
* Retrofit2
* Dagger2
* Timber
* Glide
* Kotlin coroutines

## License

Copyright 2019 Nimrod Dayan nimroddayan.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
