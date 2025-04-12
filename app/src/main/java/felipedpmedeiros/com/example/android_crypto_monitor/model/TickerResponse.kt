package felipedpmedeiros.com.example.android_crypto_monitor.model

// Change from abstract class to data class
data class TickerResponse(
    val ticker: Ticker
)

data class Ticker(
    val high: String,
    val low: String,
    val vol: String,
    val last: String,
    val buy: String,
    val sell: String,
    val date: Long
)