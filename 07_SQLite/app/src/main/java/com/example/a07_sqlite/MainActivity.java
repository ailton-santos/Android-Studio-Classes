package com.example.a07_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a07_sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    private EditText editTextCodigo;
    private EditText editTextDisciplina;
    private EditText editTextNota;

    private Button buttonIncluir;
    private Button buttonAlterar;
    private Button buttonExcluir;
    private Button buttonPesquisar;
    private Button buttonListar;

    private ListView listViewDisciplinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCodigo = findViewById(R.id.editTextCodigo);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota = findViewById(R.id.editTextNota);

        buttonIncluir = findViewById(R.id.buttonIncluir);
        buttonAlterar = findViewById(R.id.buttonAlterar);
        buttonExcluir = findViewById(R.id.buttonExcluir);
        buttonPesquisar = findViewById(R.id.buttonPesquisar);
        buttonListar = findViewById(R.id.buttonListar);

        listViewDisciplinas = findViewById(R.id.listViewDisciplinas);

        // Abrir o banco de dados
        db = openOrCreateDatabase("DisciplinasDB", MODE_PRIVATE, null);

        // Criar a tabela
        db.execSQL("CREATE TABLE IF NOT EXISTS disciplinas (codigo INTEGER PRIMARY KEY, nome TEXT, nota REAL)");

        buttonIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incluirDisciplina();
            }
        });

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarDisciplina();
            }
        });

        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirDisciplina();
            }
        });

        buttonPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesquisarDisciplina();
            }
        });

        buttonListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarDisciplinas();
            }
        });
    }

    private void incluirDisciplina() {
        ContentValues values = new ContentValues();
        values.put("codigo", editTextCodigo.getText().toString());
        values.put("nome", editTextDisciplina.getText().toString());
        values.put("nota", editTextNota.getText().toString());
        long result = db.insert("disciplinas", null, values);
        if (result == -1) {
            Toast.makeText(this, "Erro ao incluir disciplina", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Disciplina incluída com sucesso", Toast.LENGTH_SHORT).show();
            limparCampos();
        }
    }

    private void alterarDisciplina() {
        ContentValues values = new ContentValues();
        values.put("nome", editTextDisciplina.getText().toString());
        values.put("nota", editTextNota.getText().toString());
        int result = db.update("disciplinas", values, "codigo = ?", new String[]{editTextCodigo.getText().toString()});
        if (result == 0) {
            Toast.makeText(this, "Disciplina não encontrada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Disciplina alterada com sucesso", Toast.LENGTH_SHORT).show();
            limparCampos();
        }
    }

    private void excluirDisciplina() {
        int result = db.delete("disciplinas", "codigo = ?", new String[]{editTextCodigo.getText().toString()});
        if (result == 0) {
            Toast.makeText(this, "Disciplina não encontrada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Disciplina excluída com sucesso", Toast.LENGTH_SHORT).show();
            limparCampos();
        }
    }

    private void pesquisarDisciplina() {
        String[] projection = {"codigo", "nome", "nota"};
        String selection = "codigo = ?";
        String[] selectionArgs = {editTextCodigo.getText().toString()};
        Cursor cursor = db.query("disciplinas", projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            editTextDisciplina.setText(cursor.getString(cursor.getColumnIndex("nome")));
            editTextNota.setText(cursor.getString(cursor.getColumnIndex("nota")));
        } else {
            Toast.makeText(this, "Disciplina não encontrada", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    private void listarDisciplinas() {
        String[] projection = {"codigo", "nome", "nota"};
        Cursor cursor = db.query("disciplinas", projection, null, null, null, null, null);
        List<String> disciplinas = new ArrayList<>();
        while (cursor.moveToNext()) {
            String codigo = cursor.getString(cursor.getColumnIndex("codigo"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String nota = cursor.getString(cursor.getColumnIndex("nota"));
            disciplinas.add(codigo + " - " + nome + " - " + nota);
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, disciplinas);
        listViewDisciplinas.setAdapter(adapter);
    }

    private void limparCampos() {
        editTextCodigo.setText("");
        editTextDisciplina.setText("");
        editTextNota.setText("");
    }
}
