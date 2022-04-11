package com.example.loopgame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TelaEnviarEmail extends AppCompatActivity {

    EditText EdtEmail;
    Button BtnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_enviar_email);

        iniciarComponentes();
        criarListeners();

    }

    private void iniciarComponentes() {
        EdtEmail = findViewById(R.id.edtEmailTelaEnviarEmail);
        BtnEnviar = findViewById(R.id.btnEnviarTelaEnviarEmail);
    }

    private void criarListeners() {
        BtnEnviar.setOnClickListener(evt -> proximaTela(TelaRedefinirSenha.class));
    }

    public void proximaTela(Class x){
        Intent it = new Intent(getApplicationContext(), x);
        startActivity(it);
    }

}
