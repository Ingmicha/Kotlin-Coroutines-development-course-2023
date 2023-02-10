# Complete-Kotlin-Coroutines-development-course-2023



## Coroutines

* Threads are resource intensive
* Coroutines are lightweight threads
	* Use thread pools
* Simplify async code, callbacks and synchronisation
* Simple syntax
* Can pause and resume at any time, on number of threads

## Coroutines - Basic concepts

* Scope - create and run coroutines, provides lifecycle events
* Context - the **Scope** provides a context in which the coroutine runs
* Suspending functions - functions that can be run in a coroutine (can be suspended)
* Jobs - a handle on a coroutine
* Deferred - a future result of a coroutine
* Dispatcher - manager which thread(s) the coroutine runs on
* Error handling


##Scope


* Provides lifecycle methods for coroutines
	* Allow us to start and stop coroutines
* GlobalScope.launch{} - the scope of the coroutine is the lifecyce of the entire application
* runBlocking - creates a Scope and tuns a coroutine in a blocking way 
* coroutineScope {} - creates a new scope does not complete until all children coroutines complete

		runBlocking{
			launch{
				deleay(1000L)
				println("Blocking task")
			}
		}
==
	
		GlobalScope.launch{
			delay(1000L)
			println("Global scope")
		}
==	
	
		coroutineScope{
			delay(1000L)
			println("custom coroutine scope")
		}
		
		
##Context

* Acontext is a set of data that relates to the coroutine
* All coroutines hace an associated context
* Important elements of a context
	* Dispatcher - which thread the coroutine is run on
	* Job - handle on the coroutine lifecycle

##Suspending functions

* Functions thaat can be run a coroutine
* Make callbacks seamless


		suspend fun sayHello(){
			printlv("Hello!")
		}
		
		GlobalScope.launch{
			sayHello()
		}


##Jobs

* A .launch call returns a Job
* Allows us to manipulate the coroutine lifecycle
* Live in the hierarche of others Jobs both as parents or children

			Job{
				Job{}
				Job{
					Job{}
				}
			}
			
			val job1 = GlobalScope.launch{
				coroutineScope{
					val job2 = launch{
						//processing
					}
				}
			}

* Can access lifecycle variables and methods
	* cancel()
	* join()
* If a job is cancelled, all its parents and childrens will be cancelled too
*  


##Dispatchers

* A dispatchers determines which thread od thread pool the coroutine runs on
* Different dispatchers are available depending on the task specifity


		launch(Dispatchers.Default){
			//do some CPU intensive processing task here
		}

* Common dispatchers:
	* Main
		* Main thread update in UI driven applications( Android)
		* Main dispatchers needs to be defined in Gradle
	* Default
		* Useful for CPU intensive Work
	* IO
		* Useful for network communication or reading/writing files    
	* Unconfiden
		* Starts the coroutine in the inherited dispatcher that called it
	* newSingleThreadContext("My Thread")
		* Forces creation of a new thread


##Async

* Just like launch, except it returns a result
* In the form of a Deferred
* When we need the value, we call **await()** (blocking call)
	*	If the vaue is available, it will return inmediately
	* If the vaule is not available, it will pause the thread until it is

===
	
		suspend fun getRandom()= Random.nextInt(1000)
			
===

		val valueDeferred = GlobalScope.async{ getRandom()}
		//Do some processing here
		val finalValue = valueDeferred.await()
			
			 

##withContext

* Allows us to easily change context
* Easlily switch between dispatchers
* Vary lightweight


		launch(Dispatchers.Default){
			//default context
			withCotext(Dispatchers.IO){
				//IO Context
			}
			//back to default context
		}

##Exception handling

* Exception behaviour depends on the coroutine builder
* Launch
	* Propagates through the parent-child heirarchy
	* The Excepion will be thrown immediately and jobs will fail
	* Use try-catch or an exception handler

* async
	* Exceptions are deferred until the result is consumed
	* if the result is not consumed, the exception is never thrown
	* Try-catch in the coroutine or in the await() call


===
	
		val myHandler =.CoroutineExceptionHandler{coroutineContext, throwable->//handle exception}
		
===
		
		launch(myHandler){
			//do some task here
			throw IndexOutOfBoundException()
			}
===
	
		launch(Dispatchers.default +myHndler){
			//do some task here
			throw IndexOutOfBoundException()
			}

























