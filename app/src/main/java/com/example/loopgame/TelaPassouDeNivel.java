package com.example.loopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.loopgame.database.BancoSQLite;
import com.example.loopgame.modelos.Usuario;

public class TelaPassouDeNivel extends AppCompatActivity {

    Button BtnContinuar;
    ImageButton ImgBtnVoltarHome;
    ImageView ImgNivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_passou_de_nivel);

        iniciarComponentes();
        criarListeners();
        carregarImagemNivel();

    }

    private void iniciarComponentes(){
        BtnContinuar = findViewById(R.id.btnContinuarTelaPassouDeNivel);
        ImgBtnVoltarHome = findViewById(R.id.imgBtnVoltarHomeTelaPassouDeNivel);
        ImgNivel = findViewById(R.id.imgNivelTelaPassouDeNivel);
    }

    private void criarListeners(){
        BtnContinuar.setOnClickListener(evt -> proximaTela(TelaDicas.class));
        ImgBtnVoltarHome.setOnClickListener(evt -> proximaTela(TelaHome.class));
    }

    public void proximaTela(Class x){
        Intent it = new Intent(getApplicationContext(), x);
        startActivity(it);
    }

    public void carregarImagemNivel(){
        BancoSQLite db = new BancoSQLite(this);
        Usuario u = db.selecionarUsuarioPorId("1");

        if(u.getNivel().equals("A")){
            ImgNivel.setImageResource(R.drawable.icone_a);
        } else if(u.getNivel().equals("B")){
            ImgNivel.setImageResource(R.drawable.icone_b);
        } else if(u.getNivel().equals("C")){
            ImgNivel.setImageResource(R.drawable.icone_c);
        } else
            ImgNivel.setImageResource(R.drawable.icone_a);
    }

}