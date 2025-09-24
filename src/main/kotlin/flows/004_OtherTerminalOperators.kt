package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

fun main() : Unit = runBlocking {
    // First
    val numbersFlow = flow<Int> {
        (1..10).forEach {
            delay(500L)
            emit(it)
        }
    }
    // first()
    println("First Value =====> ${numbersFlow.first()}")

    // toList()
    println("Numbers List ======> ${numbersFlow.toList()}")

    // single()
    numbersFlow.single() // Will fail because numbersFlow is emitting more than one value
}