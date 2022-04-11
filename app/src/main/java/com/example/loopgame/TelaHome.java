package com.example.loopgame;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loopgame.database.BancoSQLite;
import com.example.loopgame.modelos.Usuario;

public class TelaHome extends AppCompatActivity {

    TextView TxtNomeUsuario, TxtDicas, TxtPrimeiroJogador, TxtSegundoJogador, TxtTerceiroJogador,
            TxtQuartoJogador, TxtQuintoJogador, TxtPontuacao;
    Button BtnJogar, BtnSair;
    String id;
    int indice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_home);

        iniciarComponentes();
        criarListeners();
        carregarUsuario();
        tempo();

    }

    private void iniciarComponentes(){
        TxtNomeUsuario = findViewById(R.id.txtUsuarioTelaHome);
        TxtPontuacao = findViewById(R.id.txtPontuacaoTelaHome);
        TxtDicas = findViewById(R.id.txtDicasTelaHome);
        TxtPrimeiroJogador = findViewById(R.id.txtPrimeiroJogadorTelaHome);
        TxtSegundoJogador = findViewById(R.id.txtSegundoJogadorTelaHome);
        TxtTerceiroJogador = findViewById(R.id.txtTerceiroJogadorTelaHome);
        TxtQuartoJogador = findViewById(R.id.txtQuartoJogadorTelaHome);
        TxtQuintoJogador = findViewById(R.id.txtQuintoJogadorTelaHome);
        BtnJogar = findViewById(R.id.btnJogarTelaHome);
        BtnSair = findViewById(R.id.btnSairTelaHome);
    }

    private void criarListeners(){
        BtnJogar.setOnClickListener(evt -> proximaTela(TelaDicas.class));
        BtnSair.setOnClickListener(evt -> proximaTela(TelaLogin.class));
    }

    public void proximaTela(Class x){
        Intent it = new Intent(getApplicationContext(), x);
        startActivity(it);
    }

    public void carregarUsuario(){
        BancoSQLite db = new BancoSQLite(this);
        Usuario usuario = db.selecionarUsuarioPorId("1");
        TxtNomeUsuario.setText(usuario.getNome());
        TxtPontuacao.setText(usuario.getPontuacao());
    }

    public void tempo(){
        int delay = 10000;
        new Handler().postDelayed(() -> {
            dica();
        }, delay);
    }

    public void dica(){
        if(indice < 3){
            tempo();
            Resources res = getResources();
            String[] dicas;
            dicas = res.getStringArray(R.array.dicas);
            TxtDicas.setText(dicas[indice]);
            indice++;
        } else {
            indice = 0;
            tempo();
        }
    }

}
