package com.example.loopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loopgame.database.BancoSQLite;
import com.example.loopgame.modelos.Usuario;

public class TelaLogin extends AppCompatActivity {

    Button BtnLogin, BtnEsqueceuSenha, BtnCadastrar;
    EditText EdtNome, EdtSenha;
    String nome, senha;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        iniciarComponentes();
        criarListeners();

    }

    private void iniciarComponentes() {
        BtnLogin = findViewById(R.id.btnLoginTelaLogin);
        BtnEsqueceuSenha = findViewById(R.id.btnEsqueceuSenhaTelaLogin);
        BtnCadastrar = findViewById(R.id.btnCadastrarSeTelaLogin);
        EdtNome = findViewById(R.id.edtNomeUsuarioTelaLogin);
        EdtSenha = findViewById(R.id.edtSenhaTelaLogin);
    }

    private void criarListeners(){
        BtnLogin.setOnClickListener(view -> validarUsuario());
        BtnEsqueceuSenha.setOnClickListener(evt -> proximaTela(TelaEnviarEmail.class));
        BtnCadastrar.setOnClickListener(evt -> proximaTela(TelaCadastro.class));
    }

    public void proximaTela(Class x){
        Intent it = new Intent(getApplicationContext(), x);
        startActivity(it);
    }

    public void validarUsuario(){
        BancoSQLite db = new BancoSQLite(this);

        try{
            nome = EdtNome.getText().toString();
            senha = EdtSenha.getText().toString();

            Usuario usuario = db.selecionarUsuario(nome);

            if (usuario == null) {
                Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_LONG).show();
                return;
            }

            if(usuario.getSenha().equals(senha)) {
                if(usuario.getTeste().equals("0")){
                    Intent login = new Intent((getApplicationContext()), TelaBemVindo.class);
                    startActivity(login);
                } else {
                    Intent login = new Intent((getApplicationContext()), TelaHome.class);
                    startActivity(login);
                }
            }
            else {
                Toast.makeText(this, "Senha incorreta!", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

}