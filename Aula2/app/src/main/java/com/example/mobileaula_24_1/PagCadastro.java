package com.example.mobileaula_24_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PagCadastro extends AppCompatActivity {

    private TextView btn_tela_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();

        btn_tela_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PagCadastro.this, PagLogin.class);
                startActivity(intent);

            }
        });
    }

    private void IniciarComponentes(){
        btn_tela_login = findViewById(R.id.btn_tela_login);
    }
}