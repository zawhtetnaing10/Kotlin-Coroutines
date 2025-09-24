package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val coldFlow = flow {
        println("Started emitting values")
        (1..100).forEach {
            delay(100L)
            emit(it)
        }
    }

    // State Flow ====> Replays last value
    val myStateFlow = coldFlow
        .stateIn(
            scope = this,
            //started = SharingStarted.Eagerly, // Starts emitting whether there's a collector or not
            //started = SharingStarted.Lazily, // Starts only when there's a collector
            started = SharingStarted.WhileSubscribed(), // Starts when there's a collector and stops when the last collector disappears
            initialValue = 0
        )

    val job = launch {
        myStateFlow.collect{
            println("Number received =====> $it")
        }
    }
    delay(1000L)
    job.cancel()

    // Add another subscriber to see if only last value is replayed
}