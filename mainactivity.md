### MainActivity

A MainActivity herda de AppCompatActivity para usar os recursos de UI. (class MainActivity : AppCompatActivity())
class MainActivity : AppCompatActivity()

setContentView(R.layout.activity_main): Define o layout da atividade (activity_main.xml)

Toolbar:
val toolbarMain: Toolbar = findViewById(R.id.toolbar_main)
configureToolbar(toolbarMain)

private fun configureToolbar(toolbar: Toolbar) {
setSupportActionBar(toolbar)
toolbar.setTitleTextColor(getColor(R.color.white))
supportActionBar?.setTitle(getText(R.string.app_title))
supportActionBar?.setBackgroundDrawable(getDrawable(R.color.primary))
}

Botão Refresh:
val btnRefresh: Button = findViewById(R.id.btn_refresh)
btnRefresh.setOnClickListener { makeRestCall() }

Função makeRestCall():
val service = MercadoBitcoinServiceFactory().create()
val response = service.getTicker()

if (response.isSuccessful) {
val lblValue: TextView = findViewById(R.id.lbl_value) 
val lblDate: TextView = findViewById(R.id.lbl_date)
val lastValue = tickerResponse?.ticker?.last?.toDoubleOrNull()
if (lastValue != null) {
val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
lblValue.text = numberFormat.format(lastValue)
}
val date = tickerResponse?.ticker?.date?.let { Date(it * 1000L) }
val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
lblDate.text = sdf.format(date)
} else {
val errorMessage = when (response.code()) {
400 -> "Bad Request"
401 -> "Unauthorized"
403 -> "Forbidden"
404 -> "Not Found"
else -> "Unknown error"
}
// Toast com erro
}
![erro](imagens/erro.png)
