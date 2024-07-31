package com.example.gastoviagem

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.gastoviagem.databinding.ActivityMainBinding

// está implementando o OnClickListener
class MainActivity : AppCompatActivity(), View.OnClickListener {

    // está iniciando a variavel pra depois instanciar, serve para chamar os ids da aplicação
    private lateinit var binding: ActivityMainBinding

    // função responsável por fazer a criação da Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // instanciando para poder chamar os ids da aplicação
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adiciona evento ao elemento de interface
        binding.buttonCalculate.setOnClickListener(this)
    }

    // função responsavel por tratar qualquer evento de click nos elementos
    override fun onClick(view: View) {
        if (view.id == R.id.button_calculate) {
            calculate()
        }
    }

    private fun isValid() = (binding.editDistance.text.toString() != ""
            && binding.editPrice.text.toString() != ""
            && binding.editAutonomy.text.toString() != ""
            && binding.editAutonomy.text.toString().toFloat() != 0f)

    private fun calculate() {
        if (isValid()) {
            // pega o valor do campo transforma em texto e depois em numero flutuante
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            val totalValue = (distance * price) / autonomy

            binding.textTotalValue.text = "R$ ${"%.2f".format(totalValue)}"
        } else {
            // Toast notification, pop-up. contexto é a propria aplicação, mensagem, duração, e aparecer na tela
            Toast.makeText(this, R.string.validation_fill_all_fields, Toast.LENGTH_SHORT).show()
        }
    }
}