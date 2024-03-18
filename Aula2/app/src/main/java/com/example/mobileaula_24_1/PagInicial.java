package com.example.mobileaula_24_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PagInicial extends AppCompatActivity {

    private TextView btn_deslogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_inicial);

        getSupportActionBar().hide();

        IniciarComponentes();

        btn_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PagInicial.this, PagLogin.class);
                startActivity(intent);

            }
        });
    }

    private void IniciarComponentes(){
        btn_deslogar = findViewById(R.id.btn_deslogar);
    }
}