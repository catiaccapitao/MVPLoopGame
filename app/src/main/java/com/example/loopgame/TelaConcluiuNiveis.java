package com.example.loopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TelaConcluiuNiveis extends AppCompatActivity {

    Button BtnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_concluiu_niveis);

        BtnIniciar = findViewById(R.id.btnIniciarTelaConcluiuNiveis);
        BtnIniciar.setOnClickListener(evt -> proximaTela(TelaTesteNivel.class));
    }

    public void proximaTela(Class x){
        Intent it = new Intent(getApplicationContext(), x);
        startActivity(it);
    }
}