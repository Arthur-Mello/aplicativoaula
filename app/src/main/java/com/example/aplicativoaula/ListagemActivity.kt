package com.example.aplicativoaula

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Modelo de dados
data class User(val id: String, val name: String, val email: String)

// Interface Retrofit
interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<User>>
}

class ListagemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)

        // Configurar botão de voltar
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish() // Volta para a atividade anterior
        }

        // Inicializa Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://673549c75995834c8a925cf7.mockapi.io/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        // Faz a chamada para a API
        val call = apiService.getUsers()
        call.enqueue(object : retrofit2.Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: retrofit2.Response<List<User>>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()
                    if (users != null) {
                        atualizarInterface(users)
                    } else {
                        mostrarErro("Nenhum dado encontrado.")
                    }
                } else {
                    mostrarErro("Erro no servidor: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                mostrarErro("Erro: ${t.message}")
            }
        })
    }

    private fun atualizarInterface(users: List<User>) {
        val container = findViewById<LinearLayout>(R.id.containerUsers)
        for (user in users) {
            val userView = TextView(this).apply {
                text = "Nome: ${user.name}\nEmail: ${user.email}\n"
                textSize = 16f
                setPadding(8, 8, 8, 8)
            }
            container.addView(userView)
        }
    }

    private fun mostrarErro(mensagem: String) {
        val container = findViewById<LinearLayout>(R.id.containerUsers)
        val erroView = TextView(this).apply {
            text = mensagem
            textSize = 16f
            setPadding(8, 8, 8, 8)
        }
        container.addView(erroView)
    }
}