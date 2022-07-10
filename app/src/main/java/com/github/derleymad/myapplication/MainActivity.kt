package com.github.derleymad.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Teste", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        // checar GPS
        Log.i("Teste", "onStart")
    }

    override fun onResume() {
        super.onResume()
        // voltar algo (voltar de uma tela)(refresh)
        Log.i("Teste", "onResume")
    }

    override fun onPause() {
        super.onPause()
        // registrar algum evento
        // quando alguem liga vc e vc ta jogando
        Log.i("Teste", "onPause")
    }

    override fun onStop() {
        super.onStop()
        // livrar recuros (camera, qrcode)
        Log.i("Teste", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        // analytics
        Log.i("Teste", "onDestroy")
    }
}