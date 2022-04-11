package com.example.loopgame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TelaBemVindo extends AppCompatActivity {

    Button BtnIniciarTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_bem_vindo);

        BtnIniciarTeste = findViewById(R.id.btnIniciarTesteTelaBemVindo);
        BtnIniciarTeste.setOnClickListener(evt -> proximaTela(TelaTesteNivel.class));
    }

    public void proximaTela(Class x){
        Intent it = new Intent(getApplicationContext(), x);
        startActivity(it);
    }
}
