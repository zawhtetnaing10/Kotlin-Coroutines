package flows

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main()  : Unit = runBlocking {

    // Data Source
    val numbersFlow = flow {
        (1..10).forEach {
            kotlinx.coroutines.delay(500L)
            emit(it)
        }
    }

    val doubledFlow = numbersFlow.map { it * 2 }
    val evenNumbersFlow = numbersFlow.filter { it % 2 == 0 }

    // Collect in another coroutine scope
    launch {
        doubledFlow.collect {
            println("Collected doubled data =====> $it")
        }
    }

    // Collect in another coroutine
    launch {
        evenNumbersFlow.collect {
            println("Collected filtered data =====> $it")
        }
    }
}