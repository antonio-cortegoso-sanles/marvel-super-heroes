# marvel-super-heroes

# Marvel Heroes App

Marvel Heroes Android app is a list and detail example using https://developer.marvel.com/docs API.

This app is coded in Kotlin using Clean Architecture (MVVM).

By the fisrt, Splash View consults local DB, if there is no data saved then fetch heroes data from Marvel API and save it in DB.

Next step, Heroes List View shows DB saved data and when user clicks in a hero the app does the same step that Splash View. Consulting to DB if there is the selected hero data and if not fetch from Marvel API for saving that.

Last step, Detail View shows the selected hero data.

## Libraries and Tools Used

- Kotlin
- AndroidX
    - RecyclerView
- Material Components
- Android Architecture Components
    - Lifecycle and ViewModel
- Retrofit
- Moshi
- Kotlin Coroutines
- Glide
- Koin
- Room DB
- Navigation Component

## Installation

For using this app you need get private and public key from [Developer.Marvel](https://developer.marvel.com/docs) and save this keys in local.properties file:

```android
publicApiKey="xxxxxxxxxxxxxxxxxxx"
privateApiKey="yyyyyyyyyyyyyyyyyyyyyyyyyy"
```

## Architecture and project organization
this app has been coded following [**Clean Architecture**](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) paradigm, a concept with an increasing popularity thanks to Robert C. Martin (Uncle Bob).


### Class hierarchy

* **Presentation**
This layer's duties consist of managing the events caused by the user interactions, and rendering the information coming from the _domain_ layer. In this particular case, the team has opted for using the Model-View-ViewModel ([MVVM](https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1)) architecture pattern. This entity "sees" the _domain_ layer.

* **Domain**
This layer is in charge of the application business logic. It is built upon _use-cases_ and repositories (_repository pattern_). The _domain_ layer obtains data from the _data_ module, use them to perform all the required operations, and format the outcomes to later deliver them to the _presentation_ layer. This layer represents the most inner entity, and thus it does not "see" anyone but itself.

* **Data**
This layer simply contains libraries and frameworks which provide data to the application (data sources). Among them, stand out service-query frameworks (_Retrofit_), local databases (_Room_), events tracking (_Omniture_), etc. This layer "sees" the _domain_ layer.

The usage of this class hierarchy and package organization pursues grasping the **SOLID** principles, getting more flexible when implementing new functionality, and easing code testing.
 
### Inversion of Control
In order to facilitate the interaction between layers this app uses **[Koin](https://www.raywenderlich.com/9457-dependency-injection-with-koin)** is a framework which allows to abstract type injection in a neat and clear manner. 

### Coroutines
Since _multithreading_ has historically been a challenge in Android Development, the team has decided to include [coroutines](https://codelabs.developers.google.com/codelabs/kotlin-coroutines/#0). This is one of the most interesting and appealing features recently introduced in Kotlin.

The main advantage that supports the usage of _coroutines_ is an easy and enhanced multithreading management. _Coroutines_  allow to turn asynchronous operations in synchronous code, without affecting the application overall performance.

From the _execution-flow_ perspective, every task is undertaken in the main thread (UI thread), until a _use-case_ is invoked. From that moment onwards, operations are handled in worker threads, to later retrieve the computed results in the main thread again.

### Database
One of the objectives of this app is that it can be used offline.

For this reason, a Room database is used to store all the data to be displayed as they are consulted. Room provides an abstraction layer on top of SQLite that enables seamless database access while harnessing the full power of SQLite.


