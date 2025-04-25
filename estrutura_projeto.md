# Estrutura do Projeto

```
android_crypto_monitor/
├── app/
│   ├── build.gradle.kts
│   └── src/
│       └── main/
│           ├── java/
│           │   └── felipedpmedeiros/
│           │       └── com/
│           │           └── example/
│           │               └── android_crypto_monitor/
│           │                   ├── MainActivity.kt
│           │                   ├── model/
│           │                   │   ├── Ticker.kt
│           │                   │   └── TickerResponse.kt
│           │                   └── service/
│           │                       ├── MercadoBitcoinService.kt
│           │                       └── MercadoBitcoinServiceFactory.kt
│           └── res/
│               └── layout/
│                   └── activity_main.xml
├── build.gradle.kts
└── gradle.properties
```

### Divisão de responsabilidade

- **Model:** Define as classes (TickerResponse) que representam os dados recebidos da API.
- **View:** Interface do usuário, representada pela MainActivity.kt e activity_main.xml.
- **Tratamento de dados:** Requisições à API com Retrofit e Gson.
