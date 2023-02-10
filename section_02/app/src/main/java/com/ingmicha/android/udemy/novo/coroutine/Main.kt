package com.ingmicha.android.udemy.novo.coroutine

import kotlinx.coroutines.*
import kotlin.random.Random

var functionCalls = 0

@OptIn(DelicateCoroutinesApi::class)
fun main() {

    /*
    GlobalScope.launch {
        delay(1000)
        println("World!")
    }

    print("Hello, ")
    Thread.sleep(2000)

     */

    /*
    runBlocking {
        repeat(100_000) {
            launch { println(".") }
        }
    }

     */

    /*
    *Scope
    println("Program execution will now block")

    runBlocking {
        launch {
            delay(1000L)
            println("Task from runBlocking")
        }
    }

    GlobalScope.launch {
        delay(500L)
        println("Task from GlobalScope")
    }

    coroutineScope {
        launch {
            delay(1500L)
        }
    }

    println("Program execution will now continue")

     */

    /*
    *Context
    runBlocking {
        launch(CoroutineName("myCoroutine")) {
            println("This is run from ${this.coroutineContext[CoroutineName.Key]}")
        }
    }

    GlobalScope.launch {

    }

     */

    /*
    *Suspend functions
    GlobalScope.launch {
        completeMessage()
    }
    GlobalScope.launch {
        improveMessage()
    }
    print("Hello,")
    Thread.sleep(2000L)
    println("There hace been $functionCalls calls so far")

     */

    /*
    *Jobs
    runBlocking {
        val job1 = launch {
            delay(3000L)
            println("Job1 launched")
            val job2 = launch {
                println("Job2 launched")
                delay(3000L)
                println("Job2 is Finishing")
            }

            job2.invokeOnCompletion { println("Job2 completed") }

            val job3 = launch {
                println("Job3 launched")
                delay(3000L)
                println("Job3 is finishing")
            }

        }
        job1.invokeOnCompletion {
            println("Job1 is completed")
        }

        println("Job1 will be cancelled")

        job1.cancel()
    }

     */

    /*
    * Dispatchers
    runBlocking {
        //launch(Dispatchers.Main) {
        //    println("Main dispatcher. Thread: ${Thread.currentThread().name}")
        //}

        launch(Dispatchers.Unconfined) {
            println("Unconfined1 dispatcher. Thread: ${Thread.currentThread().name}")
            delay(100L)
            println("Unconfined2 dispatcher. Thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Default) {
            println("Default dispatcher. Thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.IO) {
            println("IO dispatcher. Thread: ${Thread.currentThread().name}")
        }

        launch(newSingleThreadContext("MyThread")) {
            println("newSingleThreadContext dispatcher. Thread: ${Thread.currentThread().name}")
        }
    }
     */

    /*
    * Async
     runBlocking {
        val firstDeferred = async { getFirstValue() }
        val secondDeferred = async { getSecondValue() }

        println("Doing some processing here")
        delay(500L)
        println("Waiting for values")

        val firstValue = firstDeferred.await()
        val secondValue = secondDeferred.await()

        println("The total is ${firstValue + secondValue}")

    }
     */

    /*
    * WithContext
    runBlocking {
        launch(Dispatchers.Default) {
            println("First context: $coroutineContext")
            withContext(Dispatchers.IO) {
                println("Second context: $coroutineContext")
            }
            println("Third context: $coroutineContext")
        }
    }
     */

    /*
    * Exception handling

    val myHandler = CoroutineExceptionHandler{
        coroutineContext, throwable ->
        println("Exception handled: ${throwable.localizedMessage}")
    }

    runBlocking {
        val job = GlobalScope.launch(myHandler) {
            println("Throwing exception from job")
            throw java.lang.IndexOutOfBoundsException("Exception in coroutine")
        }
        job.join()

        val defered = GlobalScope.async {
            println("Throwing exception from async")
            throw java.lang.ArithmeticException("Exception from async")
        }

        try {
            defered.await()
        }catch (e:java.lang.ArithmeticException){
            println("Caught ArithmeticException ${e.localizedMessage}")
        }
    }

     */

}

suspend fun completeMessage() {
    delay(500L)
    println("World")
    functionCalls++
}

suspend fun improveMessage() {
    delay(1000L)
    println("Suspend functions are cool")
    functionCalls++
}

suspend fun getFirstValue(): Int {
    delay(1000L)
    val value = Random.nextInt(100)
    println("Returning first value $value")
    return value
}

suspend fun getSecondValue(): Int {
    delay(1000L)
    val value = Random.nextInt(1000)
    println("Returnin second  value $value")
    return value
}

