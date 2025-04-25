# MainActivity.kt

A `MainActivity` é responsável pela interação com o usuário e atualizações da UI.

- Configura a Toolbar com `setSupportActionBar`
- Usa `findViewById` para pegar os componentes de UI
- Usa `Retrofit` para buscar a cotação na API
- Usa `SimpleDateFormat` e `NumberFormat` para formatar data e valores
- Mostra erros com `Toast` caso a requisição falhe

```kotlin
val btnRefresh: Button = findViewById(R.id.btn_refresh)
btnRefresh.setOnClickListener {
    makeRestCall()
}
```

Resultado final do app:
![activity main](imagens/activitymain.png)
