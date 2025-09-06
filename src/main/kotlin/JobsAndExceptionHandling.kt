import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.lang.Exception

fun main(): Unit = runBlocking {

    // Job
    val firstJob = launch {
        println("First job starts")
        delay(5000)
        println("First job completes")
    }

    firstJob.join()
    //firstJob.cancel()
    println("Program ends")

    // Child coroutines
//    launch {
//        launch {
//            println("First network call running")
//            delay(1000)
//            //throw Exception("Network call fails")
//            println("First network call ends")
//        }
//
//        launch {
//            println("Second network call running")
//            delay(2000)
//            println("Second network call ends")
//        }
//    }

    // Exception handling
    launch {
        try {
            println("Network call running")
            throw Exception("Network call failed")
        } catch (e: Exception){
            println(e.message)
        }
    }
}