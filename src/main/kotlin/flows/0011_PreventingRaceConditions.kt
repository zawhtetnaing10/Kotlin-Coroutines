package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() : Unit = runBlocking {
    val state = MutableStateFlow(0)

    launch {
        state.update {
            return@update it + 1
        }
    }

    launch {
        state.update {
            return@update it + 1
        }
    }

    delay(500L)
    println("The final value is ${state.value}")
}