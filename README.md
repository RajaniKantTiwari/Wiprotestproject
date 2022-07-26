# Wiprotestproject


<h1>Koin</h1>

https://blog.mindorks.com/kotlin-koin-tutorial

https://medium.com/android-beginners/koin-dependency-injection-framework-for-android-aa34c0737956

Koin is a DI framework for Kotlin developers, completely written in Kotin. It is very light weighted. It supports the Kotlin DSL feature.

module - It creates a module in Koin which would be used by Koin to provide all the dependencies.

single - It creates a singleton that can be used across the app as a singular instance.

factory - It provides a bean definition, which will create a new instance each time it is injected.

get() - It is used in the constructor of a class to provide the required dependency.

<h1>What are Coroutines?</h1>
https://blog.mindorks.com/mastering-kotlin-coroutines-in-android-step-by-step-guide

Coroutines = Co + Routines
Coroutines are lightweight threads. coroutines can run in parallel, wait for each other and communicate. we can create thousands of them, and pay very little in terms of performance
Here, Co means cooperation and Routines means functions.It means that when functions cooperate with each other, we call them Coroutines.
Coroutines and the threads both are multitasking. But the difference is that <b>threads are managed by the OS and coroutines by the users</b>. 
It will enable writing asynchronous code in a synchronous way. (async await)

 
<h1>Paging</h1> 
Paging is used to load and display small amounts of data from the remote server.
You will get the content on the page faster
<ul>
<li>Reducing network usage</li>
<li>It uses very little memory.</li>
<li>We can load more data in first call using  function setInitialLoadSizeHint() </li>
<li>We can set prefetch distance to fetch data using  setPrefetchDistance()</li>
<li>use Paging along with LiveData and ViewModel to observe and update data in an easier way.</li>
</ul>
<b>DataSource -</b> DataSource is the class where you tell your application about how much data you want to load in your application.
<b>ItemKeyedDataSource -</b> If you want the data of an item with the help of the previous item then you can use ItemKeyedDataSource . 
<b>PageKeyedDataSource -</b> If your feeds page has some feature of the next or previous post then you can use PageKeyedDataSource.
<b>PositionalDataSource -</b> If you want to fetch data only from a specific location then you can use PositionalDataSource.

<b>PagedList -</b> By using the PagedList class, you can load the data of your application. All data which is fetched from the server will be added to PagedList.  if there is a change in the data it can be observed with the help of the LiveData.

<b>DiffUtil -</b> DiffUtil is a utility class that calculates the difference between two lists and outputs a list of update operations that converts the first list into the second one.

<b>DataSource -</b> DataSource is queried to load pages of content into a PagedList.

<b>PagedListAdapter -</b> Base class for presenting paged data from PagedLists into RecyclerView.

<b>LivePagedListBuilder -</b> for getting LiveData object of type PageList.
 

<h1>Use view binding</h1>
https://medium.com/androiddevelopers/use-view-binding-to-replace-findviewbyid-c83942471fc

