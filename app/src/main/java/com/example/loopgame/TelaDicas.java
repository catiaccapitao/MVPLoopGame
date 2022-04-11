package com.example.loopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.loopgame.database.BancoSQLite;
import com.example.loopgame.modelos.Usuario;

public class TelaDicas extends AppCompatActivity {

    Button BtnJogar;
    ImageButton ImgBtnVoltarHome;
    TextView TxtDica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_dicas);

        iniciarComponentes();
        criarListeners();
        carregarDicas();

    }

    private void iniciarComponentes() {
        BtnJogar = findViewById(R.id.btnJogarTelaDicas);
        ImgBtnVoltarHome = findViewById(R.id.imgBtnVoltarHomeTelaDicas);
        TxtDica = findViewById(R.id.txtDicaTelaDicas);
    }

    private void criarListeners(){
        BtnJogar.setOnClickListener(evt -> proximaTela(TelaPerguntas.class));
        ImgBtnVoltarHome.setOnClickListener(evt -> proximaTela(TelaHome.class));
    }

    public void proximaTela(Class x){
        Intent it = new Intent(getApplicationContext(), x);
        startActivity(it);
    }

    public void carregarDicas(){
        BancoSQLite db = new BancoSQLite(this);
        Usuario u = db.selecionarUsuarioPorId("1");
        String dica;

        if(u.getNivel().equals("A")) {
            dica = getString(R.string.dica_1);
        } else if(u.getNivel().equals("B")) {
            dica = getString(R.string.dica_2);
        } else {
            dica = getString(R.string.dica_3);
        }
        TxtDica.setText(dica);
    }
}