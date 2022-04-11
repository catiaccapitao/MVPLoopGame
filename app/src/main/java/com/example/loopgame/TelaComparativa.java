package com.example.loopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loopgame.database.BancoSQLite;
import com.example.loopgame.modelos.Usuario;

public class TelaComparativa extends AppCompatActivity {

    ImageButton ImgBtnVoltarHome;
    TextView TxtResultado, TxtParabens;
    int pontuacaoPrimeiroTeste, pontuacaoSegundoTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_comparativa);

        iniciarComponentes();
        criarListeners();
        desempenho();

    }

    public void iniciarComponentes(){
        TxtResultado = findViewById(R.id.txtResultadoTelaComparativa);
        TxtParabens = findViewById(R.id.txtParabensTelaComparativa);
        ImgBtnVoltarHome = findViewById(R.id.imgBtnVoltarHomeTelaComparativa);
    }

    public void criarListeners(){
        ImgBtnVoltarHome.setOnClickListener(evt -> proximaTela(TelaHome.class));
    }

    public void proximaTela(Class x){
        Intent it = new Intent(getApplicationContext(), x);
        startActivity(it);
    }

    public void desempenho(){
        try {
            Intent intent = getIntent();
            pontuacaoSegundoTeste = Integer.parseInt(intent.getStringExtra("pontuacao"));

            BancoSQLite db = new BancoSQLite(this);
            Usuario u = db.selecionarUsuarioPorId("1");
            pontuacaoPrimeiroTeste = Integer.parseInt(u.getPontuacaoTeste());


            if (pontuacaoPrimeiroTeste > pontuacaoSegundoTeste) {
                TxtResultado.setText(getString(R.string.errou_mais));
            } else if (pontuacaoPrimeiroTeste < pontuacaoSegundoTeste) {
                TxtResultado.setText(getString(R.string.acertou_mais));
                TxtParabens.setText(getString(R.string.parabens));
            } else
                TxtResultado.setText(getString(R.string.empate));

        } catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}