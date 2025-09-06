import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    // Global scope means the coroutine will run
    // until the application is killed.
    // Not encouraged
    GlobalScope.launch {
        println("This coroutine has the life time scope")
    }

    Repository().getUser()

    launch(Dispatchers.Default) {
        println("Task on Default dispatcher")

        withContext(Dispatchers.IO) {
            println("Task on IO dispatcher")
        }

        println("Back on Default dispatcher")
    }
}

// Custom scope
class Repository {
    val scope = CoroutineScope(Dispatchers.IO)

    fun getUser() {
        scope.launch {
            println("Getting user")
        }
    }
}