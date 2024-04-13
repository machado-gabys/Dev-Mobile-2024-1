package com.example.mobileaula_24_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PagLogin extends AppCompatActivity {

    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_login);

        editEmail = findViewById(R.id.edit_email);

        findViewById(R.id.bt_entrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                if (!email.isEmpty()) {
                    Log.d("PagLogin", "Email passado para PagInicial: " + email);
                    // Iniciar a próxima atividade e passar o email como extra
                    Intent intent = new Intent(PagLogin.this, PagInicial.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(PagLogin.this, "Por favor, insira um email.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
