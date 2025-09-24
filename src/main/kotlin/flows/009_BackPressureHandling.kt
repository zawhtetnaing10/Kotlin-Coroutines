package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val fastProducer = flow {
        (1..5).forEach {
            println("Emitting ======> $it")
            emit(it)
            delay(50L)
        }
    }.stateIn(
        scope = this,
        //started = SharingStarted.Eagerly, // Starts emitting whether there's a collector or not
        //started = SharingStarted.Lazily, // Starts only when there's a collector
        started = SharingStarted.WhileSubscribed(), // Starts when there's a collector and stops when the last collector disappears
        initialValue = 0
    )

    // Normal collection. Producer will wait for consumption.
//    fastProducer.collect {
//        delay(1000)
//        println("Normal Consumption =====> $it")
//    }

    // Buffer
//    fastProducer
//        .buffer(capacity = 5)
//        .collect{
//            delay(300L)
//            println("Buffered consumption =====> $it")
//        }

    // Conflate
//    fastProducer
//        .conflate()
//        .collect {
//            delay(1000L)
//            println("Conflated consumption =====> $it")
//        }

    // Collect Latest
    fastProducer
        .collectLatest {
            delay(1000L)
            println("Collect Latest =====> $it")
        }
}