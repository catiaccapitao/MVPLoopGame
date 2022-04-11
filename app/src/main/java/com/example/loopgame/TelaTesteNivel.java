package com.example.loopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loopgame.database.BancoSQLite;
import com.example.loopgame.modelos.Usuario;

public class TelaTesteNivel extends AppCompatActivity {

    Button BtnAlternativaA, BtnAlternativaB, BtnAlternativaC, BtnAlternativaD, BtnAlternativaE;
    TextView TxtPergunta;
    SeekBar SkbTempo;
    String nivel;
    int numeroPergunta, pontuacao, realizouTeste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_teste_nivel);

        iniciarComponentes();
        criarListeners();

        pontuacao = 0;

        carregarSeRealizouTesteENivel();
        carregarProximaPergunta();

    }

    private void iniciarComponentes() {
        BtnAlternativaA = findViewById(R.id.btnAlternativaATelaTeste);
        BtnAlternativaB = findViewById(R.id.btnAlternativaBTelaTeste);
        BtnAlternativaC = findViewById(R.id.btnAlternativaCTelaTeste);
        BtnAlternativaD = findViewById(R.id.btnAlternativaDTelaTeste);
        BtnAlternativaE = findViewById(R.id.btnAlternativaETelaTeste);
        TxtPergunta = findViewById(R.id.txtPerguntaTelaTeste);
        SkbTempo = findViewById(R.id.skbTempoTelaTeste);
    }

    private void criarListeners() {
        BtnAlternativaA.setOnClickListener(evt -> escolherAlternativa(0));
        BtnAlternativaB.setOnClickListener(evt -> escolherAlternativa(1));
        BtnAlternativaC.setOnClickListener(evt -> escolherAlternativa(2));
        BtnAlternativaD.setOnClickListener(evt -> escolherAlternativa(3));
        BtnAlternativaE.setOnClickListener(evt -> escolherAlternativa(4));
    }

    public void proximaTela(Class x) {
        Intent it = new Intent(getApplicationContext(), x);

        if (realizouTeste == 2) {
            it.putExtra("pontuacao", String.valueOf(pontuacao));
        }

        startActivity(it);
    }

    private void carregarProximaPergunta() {
        numeroPergunta++;
        String pergunta;
        Resources res = getResources();
        String[] Alternativas;

        if (numeroPergunta == 0) {
            pergunta = getString(R.string.primeira_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasPrimeiraPergunta);
        } else if (numeroPergunta == 1) {
            pergunta = getString(R.string.segunda_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasSegundaPergunta);
        } else if(numeroPergunta == 2) {
            pergunta = getString(R.string.terceira_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasTerceiraPergunta);
        } else if (numeroPergunta == 3) {
            pergunta = getString(R.string.quarta_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasQuartaPergunta);
        } else if (numeroPergunta == 4) {
            pergunta = getString(R.string.quinta_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasQuintaPergunta);
        } else if (numeroPergunta == 5) {
            pergunta = getString(R.string.sexta_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasSextaPergunta);
        } else
            return;

        TxtPergunta.setText(pergunta);
        BtnAlternativaA.setText(Alternativas[0]);
        BtnAlternativaB.setText(Alternativas[1]);
        BtnAlternativaC.setText(Alternativas[2]);
        BtnAlternativaD.setText(Alternativas[3]);
        BtnAlternativaE.setText(Alternativas[4]);
    }

    public void escolherAlternativa(int alternativa) {
        //Valida as alternativas do primeiro teste
        if (numeroPergunta == 0) {
            if (alternativa == 2) {
                carregarProximaPergunta();
                pontuacao += 20;
            } else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                carregarProximaPergunta();
            }
        } else if (numeroPergunta == 1) {
            if (alternativa == 0) {
                carregarProximaPergunta();
                pontuacao += 20;
            } else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                carregarProximaPergunta();
            }
        } else if (numeroPergunta == 2) {
            realizouTeste = 1;

            if (alternativa == 0) {
                pontuacao += 20;
                proximaTela(TelaHome.class);
            } else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                proximaTela(TelaHome.class);
            }
        }

        //Atribui um nivel ao usuario de acordo com a sua pontuacao
        if (pontuacao < 30) {
            nivel = "A";
        } else if (pontuacao < 50) {
            nivel = "B";
        } else {
            nivel = "C";
        }

        //Valida as alternativas do segundo teste
        if (numeroPergunta == 3) {
            if (alternativa == 1) {
                carregarProximaPergunta();
                pontuacao += 20;
            } else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                carregarProximaPergunta();
            }
        } else if (numeroPergunta == 4) {
            if (alternativa == 1) {
                carregarProximaPergunta();
                pontuacao += 20;
            } else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                carregarProximaPergunta();
            }
        } else if (numeroPergunta == 5) {
            realizouTeste = 2;

            if (alternativa == 3) {
                pontuacao += 20;
                proximaTela(TelaComparativa.class);
            } else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                proximaTela(TelaComparativa.class);
            }
        }

        //Salva o nivel do usuario e se ele ja fez o teste inicial
        try {
            atualizarNiveleTeste();
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível salvar o nível!", Toast.LENGTH_LONG).show();
        }

    }

    public void carregarSeRealizouTesteENivel(){
        BancoSQLite db = new BancoSQLite(this);
        Usuario u = db.selecionarUsuarioPorId("1");
        realizouTeste = Integer.parseInt(u.getTeste());
        nivel = u.getNivel();

        if(u.getNivel().equals("C")){
            numeroPergunta = 2;
        } else {
            numeroPergunta = -1;
        }
    }

    public void atualizarNiveleTeste(){
        BancoSQLite db = new BancoSQLite(this);
        Usuario u = db.selecionarUsuarioPorId("1");
        u.setNivel(nivel);
        u.setTeste(String.valueOf(realizouTeste));

        if (realizouTeste == 1 && numeroPergunta == 2) {
            u.setPontuacaoTeste(String.valueOf(pontuacao));
        }

        db.atualizarUsuario(u);

    }
}