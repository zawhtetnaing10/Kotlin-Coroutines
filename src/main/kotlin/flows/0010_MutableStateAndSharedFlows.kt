package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() : Unit = runBlocking {
//    val state = MutableStateFlow(0)
//    launch {
//        delay(50L)
//        state.update { 60 }
//        delay(50L)
//        state.update { 100 }
//    }
//
//    launch {
//        state.asStateFlow().collect{
//            println("Received values =====> $it")
//        }
//    }
//
//    launch {
//        delay(1000L)
//        state.asStateFlow().collect{
//            println("Late Collector received =====> $it")
//        }
//    }


    val sharedFlow = MutableSharedFlow<Int>()
    launch {
        delay(50L)
        sharedFlow.emit(60)
        delay(50L)
        sharedFlow.emit(100)
    }

    launch {
        sharedFlow.asSharedFlow().collect{
            println("Received values =====> $it")
        }
    }

    launch {
        delay(1000L)
        sharedFlow.asSharedFlow().collect{
            println("Late Collector Received =====> $it")
        }
    }
}