import arrow.core.Either

object NotApplicable

fun getVatValue(country: String): Either<NotApplicable, Double> {
    if (country == "SRB") {
        return Either.right(20.00)
    } else {
        return Either.Left(NotApplicable)
    }
}

fun main() {
    println(getVatValue("SRB").isLeft())
}
