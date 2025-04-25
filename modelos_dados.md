# Modelos de Dados

## TickerResponse
Representa o modelo de dados que contém as informações da API.

```kotlin
class Ticker(
    val last: String,
    val date: Long
)
```

## MercadoBitcoinService
Interface que define as chamadas HTTP.

```kotlin
interface MercadoBitcoinService {
    @GET("api/BTC/ticker/")
    suspend fun getTicker(): Response<TickerResponse>
}
```

## MercadoBitcoinServiceFactory
Instancia o Retrofit.

```kotlin
val retrofit = Retrofit.Builder()
    .baseUrl("https://www.mercadobitcoin.net/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

return retrofit.create(MercadoBitcoinService::class.java)
```
