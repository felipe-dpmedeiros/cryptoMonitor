## Estrutura do projeto
```
android_crypto_monitor/
├── app/
│   ├── build.gradle.kts (App-specific build configuration)
│   └── src/
│       └── main/
│           ├── java/
│           │   └── felipedpmedeiros/
│           │       └── com/
│           │           └── example/
│           │               └── android_crypto_monitor/
│           │                   ├── MainActivity.kt (Main activity)
│           │                   ├── model/ (Data model classes)
│           │                   │   ├── Ticker.kt
│           │                   │   └── TickerResponse.kt
│           │                   └── service/ (Networking service)
│           │                       ├── MercadoBitcoinService.kt
│           │                       └── MercadoBitcoinServiceFactory.kt
│           └── res/ (Layout files, resources)
│               └── layout/
│                   └── activity_main.xml
├── build.gradle.kts (Root-level build configuration)
└── gradle.properties (Project properties)
```
*   **Model:** Responsável por definir as classes (TickerResponse) que representam os dados recebidos da API.
*   **View:** Compreende a interface do usuário, representada pela MainActivity.kt e seu layout correspondente (activity_main.xml).
*   **Tratamento de dados:** Contém as classes que lidam com as requisições à API, implementando o uso do Retrofit e Gson para obter e tratar os dados.

- [Estrutura do Projeto](estrutura_projeto.md)
- [Tecnologias Utilizadas](tecnologias_utilizadas.md)
- [Explicação do Código](explicacao_codigo.md)
- [Layouts XML](layouts.md)
- [MainActivity](mainactivity.md)
