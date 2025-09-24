package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() : Unit = runBlocking {
    val coldFlow = flow {
        println("Started emitting values.")
        (1..3).forEach {
            kotlinx.coroutines.delay(100L)
            emit(it)
        }
    }

    val sharedFlow = coldFlow.shareIn(
        this,
        //started = SharingStarted.Eagerly,
        //started = SharingStarted.Lazily,
        started = SharingStarted.WhileSubscribed()
    )


    val job = launch {
        sharedFlow.collectLatest {
            println("Collected data =====> $it")
        }
    }
    delay(500L)
    job.cancel()

    // Add another subscriber to see if only last value is replayed
}