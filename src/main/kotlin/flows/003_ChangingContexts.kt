package flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    getMoviesFromPersistence().collect {
        println("Collecting thread =====> ${Thread.currentThread().name}")
        println("Collected Movies ====> $it")
    }
}

suspend fun getMoviesFromPersistence(): Flow<List<String>> {
    return flow {
        println("Operating thread =====> ${Thread.currentThread().name}")
        delay(500L)
        emit(listOf("Weapon", "Predator", "John Wick"))
    }.map {
        it.map { movie -> "Movie : $movie" }
    }.flowOn(Dispatchers.IO)
}