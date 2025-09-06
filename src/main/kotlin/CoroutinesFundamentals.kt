import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
//    launch {
//        doSomethingAsync()
//    }
//
//    println("The program continues")

    val getMoviesAsync = async { getMovies() }
    println("Do something else")
    val movies = getMoviesAsync.await()
    print(movies)
}

suspend fun doSomethingAsync(){
    println("Network call starts")
    delay(1000)
    println("Network call completes")
}

suspend fun getMovies() : List<String>{
    println("Get movies start")
    delay(2000)
    return listOf("John Wick", "Hungry", "Weapon")
}