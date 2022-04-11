package com.example.loopgame.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.loopgame.modelos.Usuario;

import java.time.temporal.ValueRange;

public class BancoSQLite extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "Dados_Usuario.db";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";
    private static final String PONTUACAO = "pontuacao";
    private static final String PONTUACAO_TESTE = "pontuacao_teste";
    private static final String TESTE = "teste";
    private static final String NIVEL = "nivel";

    private static final String TABELA = "usuarios";
    private static final int VERSAO = 1;

    public BancoSQLite(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USUARIOS_TABLE = "CREATE TABLE " + TABELA + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME + " TEXT," +
                EMAIL + " TEXT," +
                SENHA + " TEXT," +
                PONTUACAO + " TEXT," +
                PONTUACAO_TESTE + " TEXT," +
                TESTE + " TEXT," +
                NIVEL + " TEXT ) ";
        db.execSQL(CREATE_USUARIOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }

    public boolean inserirUsuario(Usuario u) {
        try {
            long result;
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NOME, u.getNome());
            values.put(EMAIL, u.getEmail());
            values.put(SENHA, u.getSenha());
            values.put(PONTUACAO, u.getPontuacao());
            values.put(PONTUACAO_TESTE, u.getPontuacaoTeste());
            values.put(TESTE, u.getTeste());
            values.put(NIVEL, u.getNivel());

            result = db.insert(TABELA, null, values);
            db.close();

            if (result == -1)
                return false;
            else
                return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario selecionarUsuario(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA,
                new String[]{ID, NOME, EMAIL, SENHA, PONTUACAO, PONTUACAO_TESTE, TESTE, NIVEL},
                NOME + " = ?",
                new String[]{String.valueOf(email)}, null, null, null, null);

        try {
            if (cursor != null) {
                cursor.moveToFirst();

                Usuario user = new Usuario(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7));

                return (Usuario) user.clone();
            } else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario selecionarUsuarioPorId(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor cursor = db.query(
                    TABELA,
                    new String[]{ID, NOME, EMAIL, SENHA, PONTUACAO, PONTUACAO_TESTE, TESTE, NIVEL},
                    ID + " = ? ",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                Usuario user = new Usuario(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7));

                return (Usuario) user.clone();
            } else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void atualizarUsuario(Usuario u) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME, u.getNome());
        values.put(EMAIL, u.getEmail());
        values.put(SENHA, u.getSenha());
        values.put(PONTUACAO, u.getPontuacao());
        values.put(PONTUACAO_TESTE, u.getPontuacaoTeste());
        values.put(NIVEL, u.getNivel());
        values.put(TESTE, u.getTeste());
        db.update(TABELA, values, ID + " = ? ", new String[]{u.getId()});
    }
}
