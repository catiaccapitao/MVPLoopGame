package com.example.loopgame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TelaRedefinirSenha extends AppCompatActivity {

    Button BtnRedefinirSenha;
    ImageButton ImgBtnVoltarLogin;
    EditText EdtCodigoValidacao, EdtSenha, EdtConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_redefinir_senha);

        iniciarComponentes();
        criarListeners();

    }

    private void iniciarComponentes() {
        BtnRedefinirSenha = findViewById(R.id.btnRedefinirTelaRedefinirSenha);
        ImgBtnVoltarLogin = findViewById(R.id.imgBtnVoltarLoginTelaRedefinirSenha);
        EdtCodigoValidacao = findViewById(R.id.edtCodigoValidacaoTelaRedefinirSenha);
        EdtSenha = findViewById(R.id.edtSenhaTelaRedefinirSenha);
        EdtConfirmarSenha = findViewById(R.id.edtConfirmarSenhaTelaRedefinirSenha);
    }

    private void criarListeners(){
        BtnRedefinirSenha.setOnClickListener(evt -> proximaTela(TelaLogin.class));
        ImgBtnVoltarLogin.setOnClickListener(evt -> proximaTela(TelaLogin.class));
    }

    public void proximaTela(Class x){
        Intent it = new Intent(getApplicationContext(), x);
        startActivity(it);
    }

}
