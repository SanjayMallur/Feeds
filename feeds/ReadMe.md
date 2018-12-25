# Food feeds

This project is about showing list of user's post on Recyclerview with author name, details with receipe image. Clicking on each post takes to details page where user can see receipe with details .

### Design link
* [Design](https://www.uplabs.com/posts/food-app-animation)


### Prerequisites

Things you need to install and build application:

```
Android Studio 3.1.2

Gradle 4.4

compileSdkVersion 27

minSdkVersion 16

targetSdkVersion 27
```



### Running the units tests

1. Go to /src/test/java
2. Right click on java and "Run tests in java"
3. You can check the results

###  Running the mock tests


   1. Change Build Variant to mockDebug and Sync project
   2. Run application and verify mock data on UI
   3. Go to /src/androidMockTest/java
   4. Right click on java and “Run tests in java”

## Built With

* [Retrofit2](https://github.com/square/retrofit) - Type-safe HTTP client for Android
* [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android
* [Dagger2](https://github.com/google/dagger) - A fast dependency injector for Android and Java
* [RxJava](https://github.com/ReactiveX/RxJava) - A library for composing asynchronous and event-based programs using observable sequences for the Java VM.


## Architecture used
##### MVP

This application is using MVP pattern. I am using the Repository pattern in order to provide the data we are going to use in our different builds. This pattern allows us to have different datasources per build flavour we have on our build.gradle file. In our case, in our test build we can load mock data with our fakeApiService instead of loading the real data from server. This pattern allows you to have an independent datasource that can be used from different origins (DB, API, mock).

##### Details of Repository Pattern used

Model

1.  We have a PostDataSource Interface. This interface will contain the method we currently need to obtain the data from our datasource.
2.  Then we have a PostRepository class. This class will be in charge of getting our correct datasource.
3.  The only datasource is PostRemoteDataSource classe. This classes contains the implementation of  all PostDataSource interface. This datasource will call the webservice and get data.
4.  Our Injection class DataModule(One in production package and another one in mock package). This class is made so we can choose which API layer we should use.
5.  In summary, We have separated all our datasources from each other, making it easier to change between them. Also they are separated from the API layer we use. Both of them can use different layers without having to change any code.

View

The view layer is in charge of displaying the data and receiving the user interactions. If an user makes an interaction it will redirect it to the instance of the Presenter it has. After the presenter handles the logic, it will send the data to show to the view.

Presenter

The Presenter layer is in charge of the communication between the View and the Model. It will handle all your business logic and is in charge of delivering it where it was required.


## Author

* **Sanjay Mallur** -  [Github](https://github.com/SanjayMallur)

