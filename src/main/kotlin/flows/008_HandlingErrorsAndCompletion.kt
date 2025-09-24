package flows

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.IllegalStateException

fun main(): Unit = runBlocking {
    val flow = flow {
        emit(100)
        throw IllegalStateException("Something went wrong.")
        emit(200)
    }
        .map { it * 2 }
        .filter { it % 2 == 0 }
        .catch { // If there's a catch block here, the throwable in onCompletion block will be null
            println("Flow failed with exception $it")
            emit(-1)
        }
        .onCompletion {
            // If catch operator is used and a value is re-emitted.
            // The throwable in this onCompletion block will be null
            it?.let {
                println("Flow completed with error")
            } ?: run {
                println("Flow completed successfully")
            }
        }

    flow.collect {
        println("The collected value is $it")
    }
}