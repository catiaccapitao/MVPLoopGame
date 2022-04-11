package com.example.loopgame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loopgame.database.BancoSQLite;
import com.example.loopgame.modelos.Usuario;

public class TelaCadastro extends AppCompatActivity {

    ImageButton ImgBtnConfirmar, ImgBtnCancelar;
    EditText EdtNome, EdtEmail, EdtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        iniciarComponentes();
        criarListeners();

    }

    private void iniciarComponentes() {
        EdtNome = findViewById(R.id.edtNomeTelaCadastro);
        EdtEmail = findViewById(R.id.edtEmailTelaCadastro);
        EdtSenha = findViewById(R.id.edtSenhaTelaCadastro);
        ImgBtnConfirmar = findViewById(R.id.imgBtnConfirmarTelaCadastro);
        ImgBtnCancelar = findViewById(R.id.imgBtnCancelarTelaCadastro);
    }

    private void criarListeners(){
        ImgBtnConfirmar.setOnClickListener(view -> validarCampos());
        ImgBtnCancelar.setOnClickListener(evt -> proximaTela(TelaLogin.class));
    }

    public void proximaTela(Class x){
        Intent it = new Intent(getApplicationContext(), x);
        startActivity(it);
    }

    public void validarCampos(){
        if (EdtNome.getText().toString().equals("") || EdtSenha.getText().toString().equals("")){
            Toast.makeText(this, "O nome e a senha são obrigatórios!", Toast.LENGTH_LONG).show();
            return;
        } else
            salvarUsuario();
    }

    public void salvarUsuario(){
        BancoSQLite db = new BancoSQLite(this);

        try {
            if (db.inserirUsuario(new Usuario(
                    EdtNome.getText().toString(),
                    EdtEmail.getText().toString(),
                    EdtSenha.getText().toString(),
                    "0",
                    "0",
                    "0",
                    ""))
            ) {
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                proximaTela(TelaLogin.class);
            } else {
                Toast.makeText(this, "Não foi possível cadastrar o usuário!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

}