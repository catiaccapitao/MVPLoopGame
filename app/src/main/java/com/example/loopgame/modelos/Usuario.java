package com.example.loopgame.modelos;

public class Usuario implements Cloneable{
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String pontuacao;
    private String pontuacaoTeste;
    private String teste;
    private String nivel;

    public Usuario() {

    }

    public Usuario(String nome, String email, String senha, String pontuacao, String pontuacaoTeste, String teste, String nivel){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pontuacao = pontuacao;
        this.pontuacaoTeste = pontuacaoTeste;
        this.teste = teste;
        this.nivel = nivel;
    }

    public Usuario(String id, String nome, String email, String senha, String pontuacao, String pontuacaoTeste, String teste, String nivel){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pontuacao = pontuacao;
        this.pontuacaoTeste = pontuacaoTeste;
        this.teste = teste;
        this.nivel = nivel;
    }

    public Usuario(Usuario u){
        this.id = u.id;
        this.nome = u.nome;
        this.email = u.email;
        this.senha = u.senha;
        this.pontuacao = u.pontuacao;
        this.pontuacaoTeste = u.pontuacaoTeste;
        this.teste = u.teste;
        this.nivel = u.nivel;
    }

    public String getId(){

        return this.id;
    }

    public void setId(String id){

        this.id = id;
    }

    public String getNome() {

        return this.nome;
    }

    public void setNome(String n){

        this.nome = n;
    }

    public String getEmail(){

        return this.email;
    }

    public void setEmail(String e){

        this.email = e;
    }

    public String getSenha(){

        return this.senha;
    }

    public void setSenha(String s){

        this.senha = s;
    }

    public String getPontuacao(){

        return this.pontuacao;
    }

    public void setPontuacao(String p){

        this.pontuacao = p;
    }

    public String getPontuacaoTeste(){

        return this.pontuacaoTeste;
    }

    public void setPontuacaoTeste(String p){

        this.pontuacaoTeste = p;
    }

    public String getTeste() {

        return this.teste;
    }

    public void setTeste(String t) {

        this.teste = t;
    }

    public String getNivel() {

        return this.nivel;
    }

    public void setNivel(String n) {

        this.nivel = n;
    }

    @Override
    public Object clone(){
        Usuario clone = new Usuario(this);
        return clone;
    }












}
