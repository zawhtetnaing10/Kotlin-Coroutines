package flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val userFlow = flow {
        (1..3).forEach {
            emit(it)
        }
    }
//        .flatMapConcat {// flatMapConcat process inner flows one after another
//            flow { emit(getUserDetails(it)) }
//        }
//        .flatMapMerge { // flatMapMerge processes inner flows concurrently, the result may be out of order.
//            flow{ emit(getUserDetails(it)) }
//        }
        .flatMapLatest {// flatMapLatest only gets the latest value.
            flow { emit(getUserDetails(it)) }
        }
        .flowOn(Dispatchers.IO)

    userFlow.collect {
        println("Collected user =====> $it")
    }
}

suspend fun getUserDetails(userId: Int): String {
    delay(1000L)
    return when (userId) {
        1 -> "{name: John, age : 30}"
        2 -> "{name: Mary, age : 20}"
        else -> "{name: Jack, age: 25}"
    }
}