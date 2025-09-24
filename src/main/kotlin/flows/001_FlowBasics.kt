package flows

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() : Unit = runBlocking {
    // Flow with single value => Cold Flow
//    val singleValFlow = flow {
//        println("Emitting a number")
//        emit(1)
//    }
//
//    singleValFlow.collect {
//        println("First collector ====> $it")
//    }
//
//    singleValFlow.collect {
//        println("Second collector ====> $it")
//    }


    // Multiple Values Flow with Delay
    val multiValFlow = flow {
        (1..5).forEach {
            delay(500L)
            emit(it)
        }
    }

    // Collecting is a blocking operation
    launch {
        multiValFlow.collect {
            println("First Multi value flow ====> $it")
        }
    }

    launch {
        multiValFlow.collect {
            println("Second Multi value flow ====> $it")
        }
    }
}