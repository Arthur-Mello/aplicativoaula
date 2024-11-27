package com.example.aplicativoaula  // Certifique-se de que o pacote está correto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.aplicativoaula.R  // Garantir que o R seja do mesmo pacote
import com.example.aplicativoaula.CadastroActivity
import com.example.aplicativoaula.ListagemActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Usando R do pacote correto

        val btnCadastro = findViewById<Button>(R.id.btnCadastro)
        val btnListagem = findViewById<Button>(R.id.btnListagem)

        btnCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        btnListagem.setOnClickListener {
            val intent = Intent(this, ListagemActivity::class.java)
            startActivity(intent)
        }
    }
}
