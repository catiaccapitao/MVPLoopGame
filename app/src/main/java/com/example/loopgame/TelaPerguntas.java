package com.example.loopgame;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loopgame.database.BancoSQLite;
import com.example.loopgame.modelos.Usuario;

public class TelaPerguntas extends AppCompatActivity {

    ImageButton ImgBtnVoltarHome;
    ImageView ImgNivel;
    Button BtnAlternativaA, BtnAlternativaB, BtnAlternativaC, BtnAlternativaD, BtnAlternativaE;
    TextView TxtPergunta, TxtPontuacao;
    SeekBar SkbTempo;
    String nivel;
    int numeroPergunta, pontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_perguntas);

        iniciarComponentes();
        carregarPontuacaoENivel();
        criarListeners();
        carregarImagemNivel();
        carregarProximaPergunta();

    }

    private void iniciarComponentes() {
        ImgBtnVoltarHome = findViewById(R.id.imgBtnVoltarHomeTelaPerguntas);
        ImgNivel = findViewById(R.id.imgNivelTelaPerguntas);
        BtnAlternativaA = findViewById(R.id.btnAlternativaATelaPerguntas);
        BtnAlternativaB = findViewById(R.id.btnAlternativaBTelaPerguntas);
        BtnAlternativaC = findViewById(R.id.btnAlternativaCTelaPerguntas);
        BtnAlternativaD = findViewById(R.id.btnAlternativaDTelaPerguntas);
        BtnAlternativaE = findViewById(R.id.btnAlternativaETelaPerguntas);
        TxtPergunta = findViewById(R.id.txtPerguntaTelaPerguntas);
        TxtPontuacao = findViewById(R.id.txtPontuacaoTelaPerguntas);
        SkbTempo = findViewById(R.id.skbTempoTelaPerguntas);
    }

    private void criarListeners(){
        ImgBtnVoltarHome.setOnClickListener(evt -> proximaTela(TelaHome.class));
        BtnAlternativaA.setOnClickListener(evt -> escolherAlternativa(0));
        BtnAlternativaB.setOnClickListener(evt -> escolherAlternativa(1));
        BtnAlternativaC.setOnClickListener(evt -> escolherAlternativa(2));
        BtnAlternativaD.setOnClickListener(evt -> escolherAlternativa(3));
        BtnAlternativaE.setOnClickListener(evt -> escolherAlternativa(4));
    }

    public void proximaTela(Class x){
        Intent it = new Intent(getApplicationContext(), x);
        startActivity(it);
    }

    public void carregarProximaPergunta() {
        numeroPergunta++;
        String pergunta;
        Resources res = getResources();
        String[] Alternativas;

        if (nivel.equals("A") && numeroPergunta == 0) {
            pergunta = getString(R.string.primeira_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasPrimeiraPergunta);
        } else if (nivel.equals("A") && numeroPergunta == 1) {
            pergunta = getString(R.string.segunda_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasSegundaPergunta);
        } else if (nivel.equals("A") && numeroPergunta == 2){
            pergunta = getString(R.string.terceira_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasTerceiraPergunta);
        } else if (nivel.equals("B") && numeroPergunta == 3) {
            pergunta = getString(R.string.quarta_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasQuartaPergunta);
        } else if (nivel.equals("B")  && numeroPergunta == 4) {
            pergunta = getString(R.string.quinta_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasQuintaPergunta);
        } else if (nivel.equals("B") && numeroPergunta == 5) {
            pergunta = getString(R.string.sexta_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasSextaPergunta);
        } else if (nivel.equals("C") && numeroPergunta == 6) {
            pergunta = getString(R.string.setima_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasSetimaPergunta);
        } else if (nivel.equals("C") && numeroPergunta == 7) {
            pergunta = getString(R.string.oitava_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasOitavaPergunta);
        } else if (nivel.equals("C") && numeroPergunta == 8) {
            pergunta = getString(R.string.nona_pergunta);
            Alternativas = res.getStringArray(R.array.alternativasNonaPergunta);
        } else {
            return;
        }

        TxtPergunta.setText(pergunta);
        BtnAlternativaA.setText(Alternativas[0]);
        BtnAlternativaB.setText(Alternativas[1]);
        BtnAlternativaC.setText(Alternativas[2]);
        BtnAlternativaD.setText(Alternativas[3]);
        BtnAlternativaE.setText(Alternativas[4]);

    }

    public void escolherAlternativa(int alternativa) {
        //Valida as alternativas das perguntas
        if (numeroPergunta == 0){
            if(alternativa == 2) {
                carregarProximaPergunta();
                pontuacao += 20;
            } else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                carregarProximaPergunta();
            }
        } else if (numeroPergunta == 1){
            if(alternativa == 0) {
                carregarProximaPergunta();
                pontuacao += 20;
            }else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                carregarProximaPergunta();
            }
        } else if (numeroPergunta == 2) {
            if(alternativa == 0) {
                pontuacao += 20;
                proximaTela(TelaPassouDeNivel.class);
            }else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                if(pontuacao < 30) {
                    proximaTela(TelaHome.class);
                } else {
                    proximaTela(TelaPassouDeNivel.class);
                }
            }

            if(pontuacao < 30){
                nivel = "A";
            } else
                nivel = "B";

        } else if (numeroPergunta == 3){
            if(alternativa == 1) {
                carregarProximaPergunta();
                pontuacao += 20;
            } else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                carregarProximaPergunta();
            }
        } else if (numeroPergunta == 4){
            if(alternativa == 1) {
                carregarProximaPergunta();
                pontuacao += 20;
            }else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                carregarProximaPergunta();
            }
        } else if (numeroPergunta == 5) {
            if (alternativa == 1) {
                pontuacao += 20;
                if (pontuacao >= 100) {
                    proximaTela(TelaPassouDeNivel.class);
                }
            } else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                if (pontuacao < 120) {
                    proximaTela(TelaHome.class);
                } else {
                    proximaTela(TelaPassouDeNivel.class);
                }
            }

            if (pontuacao < 100) {
                nivel = "B";
            } else
                nivel = "C";

        }  else if (numeroPergunta == 6){
            if(alternativa == 2) {
                carregarProximaPergunta();
                pontuacao += 20;
            } else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                carregarProximaPergunta();
            }
        } else if (numeroPergunta == 7){
            if(alternativa == 2) {
                carregarProximaPergunta();
                pontuacao += 20;
            }else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                carregarProximaPergunta();
            }
        } else if (numeroPergunta == 8) {
            if (alternativa == 4) {
                pontuacao += 20;
                if (pontuacao >= 140) {
                    proximaTela(TelaConcluiuNiveis.class);
                }
            } else {
                Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
                if (pontuacao < 140) {
                    proximaTela(TelaHome.class);
                } else {
                    proximaTela(TelaConcluiuNiveis.class);
                }
            }

        } else {
            Toast.makeText(this, "Alternativa incorreta!", Toast.LENGTH_LONG).show();
            carregarProximaPergunta();
        }

        //Salva a pontuação e o nivel do usuario
        try {
            atualizarPontuacaoENivel();
        }
        catch (Exception e) {
            Toast.makeText(this, "Não foi possível salvar o nível!", Toast.LENGTH_LONG).show();
        }

        TxtPontuacao.setText(String.valueOf(pontuacao));
    }

    public void carregarPontuacaoENivel() {
        BancoSQLite db = new BancoSQLite(this);
        Usuario u = db.selecionarUsuarioPorId("1");
        TxtPontuacao.setText(u.getPontuacao());
        pontuacao = Integer.parseInt(u.getPontuacao());
        nivel = u.getNivel();

        carregarImagemNivel();
    }

    public void carregarImagemNivel(){
        BancoSQLite db = new BancoSQLite(this);
        Usuario u = db.selecionarUsuarioPorId("1");

        if(u.getNivel().equals("A")){
            ImgNivel.setImageResource(R.drawable.icone_a);
            numeroPergunta = -1;
        } else if(u.getNivel().equals("B")){
            ImgNivel.setImageResource(R.drawable.icone_b);
            numeroPergunta = 2;
        } else if(u.getNivel().equals("C")){
            ImgNivel.setImageResource(R.drawable.icone_c);
            numeroPergunta = 5;
        } else {
            Toast.makeText(this, "Nivel não encontrado.", Toast.LENGTH_LONG).show();
        }

    }

    public void atualizarPontuacaoENivel() {
        BancoSQLite db = new BancoSQLite(this);
        Usuario u = db.selecionarUsuarioPorId("1");
        u.setPontuacao(String.valueOf(pontuacao));
        u.setNivel(nivel);

        db.atualizarUsuario(u);
    }
}
