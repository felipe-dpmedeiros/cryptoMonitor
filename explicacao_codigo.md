## Explicação do codigo e suas implementações

### Classes responsaveis pelos modelos de dados (chamados de API)

# TickerResponse: Representa o modelo de dados que contem as informações da API (por exemplo, val last é o ultimo valor da cotação e o val date é a data da cotação). Para este app, é utilizado apenas esses 2 valores. 
class Ticker(
val last: String,
val date: Long,
(outros valores não utilizados)
)
# MercadoBitcoinService: Responsável por fazer a requisição à API.
Essa interface simples apenas interage com a API, onde tem as "continuações" url HTTP. Ele pega os dados que ele vai mostrar para o usuário. 
interface MercadoBitcoinService {
@GET("api/BTC/ticker/") // Pega a informação 
suspend fun getTicker(): Response<TickerResponse> // Faz a requisição
}

# MercadoBitcoinServiceFactory: Responsável por instanciar o Retrofit.
val retrofit = Retrofit.Builder() vai instanciar o retrofit.
Primeiro, ele vai pegar a base da url onde ele vai fazer a requisição HTTP:
.baseUrl("https://www.mercadobitcoin.net/")
Depois disso, ele tambem vai converter o JSON para o GSON:
.addConverterFactory(GsonConverterFactory.create())
.build()
Por fim, ele vai retornar a instancia do retrofit para a classe MercadoBitcoinService, onde ele vai "juntar" a base url com o get.
return retrofit.create(MercadoBitcoinService::class.java)
