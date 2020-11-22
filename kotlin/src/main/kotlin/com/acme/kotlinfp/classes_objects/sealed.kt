package classes_objects


fun divide(x: Int, y: Int) = x / y

sealed class Result {
    class Success(val value: String) : Result()
    class Error(val error: String) : Result()
}

fun processSomething(something: String): Result {
    if (something.isEmpty()) {
        return Result.Success("Good")
    } else {
        return Result.Error("Not empty")
    }
}

fun handle() {
    val r = when (processSomething("Something")) {
        is Result.Success -> "Success"
        is Result.Error -> "Error"
    }
}