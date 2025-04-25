// Define o pacote onde essa classe está localizada
package felipedpmedeiros.com.example.android_crypto_monitor

// Importações necessárias para o funcionamento da activity e seus componentes
import android.os.Bundle                      // Lida com o ciclo de vida da activity
import android.widget.Button                  // Para usar o botão
import android.widget.TextView                // Para mostrar textos na tela
import android.widget.Toast                   // Para mostrar mensagens rápidas na tela
import androidx.appcompat.app.AppCompatActivity // Classe base para activities modernas com suporte a ActionBar
import androidx.appcompat.widget.Toolbar       // Para usar a Toolbar personalizada
import felipedpmedeiros.com.example.android_crypto_monitor.service.MercadoBitcoinServiceFactory // Classe para consumir API
import kotlinx.coroutines.CoroutineScope       // Criação de escopo para corrotinas
import kotlinx.coroutines.Dispatchers          // Define em qual thread a corrotina será executada
import kotlinx.coroutines.launch               // Lança a execução de uma corrotina
import java.text.NumberFormat                  // Para formatar números como moeda
import java.text.SimpleDateFormat              // Para formatar a data
import java.util.Date                          // Classe que representa a data
import java.util.Locale                        // Define localização para formato de moeda e data

// Classe principal da tela, herdando funcionalidades de AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Função chamada ao iniciar a activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)                     // Chama o comportamento padrão do onCreate
        setContentView(R.layout.activity_main)                 // Define o layout da activity

        // Pegando a Toolbar do layout e configurando ela
        val toolbarMain: Toolbar = findViewById(R.id.toolbar_main)
        configureToolbar(toolbarMain)

        // Pegando o botão e definindo a ação quando ele for clicado
        val btnRefresh: Button = findViewById(R.id.btn_refresh)
        btnRefresh.setOnClickListener {
            makeRestCall() // Faz a chamada para atualizar os dados da API
        }
    }

    // Função que configura a Toolbar com título, cor e fundo
    private fun configureToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)                                     // Define a toolbar como ActionBar
        toolbar.setTitleTextColor(getColor(R.color.white))              // Define a cor do texto como branco
        supportActionBar?.setTitle(getText(R.string.app_title))         // Define o título da toolbar
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.primary)) // Define a cor de fundo
    }

    // Função que faz a chamada para a API usando corrotinas
    private fun makeRestCall() {
        CoroutineScope(Dispatchers.Main).launch { // Executa na thread principal
            try {
                val service = MercadoBitcoinServiceFactory().create() // Cria uma instância do serviço
                val response = service.getTicker()                    // Faz a requisição à API

                if (response.isSuccessful) {
                    val tickerResponse = response.body() // Recupera o corpo da resposta

                    // Pega os elementos da interface (textos) para atualizar
                    val lblValue: TextView = findViewById(R.id.lbl_value)
                    val lblDate: TextView = findViewById(R.id.lbl_date)

                    // Pega o último valor da cotação (se existir) e formata como moeda brasileira
                    val lastValue = tickerResponse?.ticker?.last?.toDoubleOrNull()
                    if (lastValue != null) {
                        val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                        lblValue.text = numberFormat.format(lastValue) // Exibe o valor formatado
                    }

                    // Pega a data da cotação (em timestamp UNIX), transforma em data legível
                    val date = tickerResponse?.ticker?.date?.let { Date(it * 1000L) }
                    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                    lblDate.text = sdf.format(date) // Exibe a data formatada
                } else {
                    // Se a resposta não for bem-sucedida, mostra um erro específico
                    val errorMessage = when (response.code()) {
                        400 -> "Bad Request"
                        401 -> "Unauthorized"
                        403 -> "Forbidden"
                        404 -> "Not Found"
                        else -> "Unknown error"
                    }
                    // Mostra uma mensagem de erro ao usuário
                    Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
                }

            } catch (e: Exception) {
                // Se houver uma exceção (ex: sem internet), mostra mensagem de falha
                Toast.makeText(this@MainActivity, "Falha na chamada: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
