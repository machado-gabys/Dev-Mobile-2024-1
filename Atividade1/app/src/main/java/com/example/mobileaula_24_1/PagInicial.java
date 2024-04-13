package com.example.mobileaula_24_1;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PagInicial extends AppCompatActivity {

    private TextView textNomeUsuario;
    private TextView textPostUsuario;
    private TextView textComentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_inicial);

        textNomeUsuario = findViewById(R.id.text_Nome_Usuario);
        textPostUsuario = findViewById(R.id.text_Post_Usuario);
        textComentarios = findViewById(R.id.text_Comentarios);

        // Receber o email da tela de login
        String email = getIntent().getStringExtra("email");

        // Verificar se o email não é nulo
        if (email != null) {
            getUserDetails(email);
        } else {
            Toast.makeText(this, "Email não recebido.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getUserDetails(String email) {
        String url = "https://jsonplaceholder.typicode.com/users?email=" + email;

        // Fazer a solicitação JSON usando Volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Verificar se a resposta não está vazia
                            if (response.length() > 0) {
                                // Obter o primeiro objeto JSON da resposta
                                JSONObject userObject = response.getJSONObject(0);

                                // Extrair o nome do usuário do objeto JSON
                                String userName = userObject.getString("name");

                                // Definir o nome do usuário na TextView
                                textNomeUsuario.setText(userName);

                                // Obter o ID do usuário
                                int userId = userObject.getInt("id");

                                // Buscar e exibir o post do usuário
                                getPost(userId);

                                // Buscar e exibir os comentários do post do usuário
                                getComments(userId);
                            } else {
                                Toast.makeText(PagInicial.this, "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PagInicial.this, "Erro ao processar a resposta JSON.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", "Volley Error: " + error.getMessage());
                        Toast.makeText(PagInicial.this, "Erro de rede. Verifique sua conexão com a internet.", Toast.LENGTH_SHORT).show();
                    }
                });

        // Adicionar a solicitação à fila de requisições
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private void getPost(int userId) {
        String url = "https://jsonplaceholder.typicode.com/posts?userId=" + userId;

        // Fazer a solicitação JSON usando Volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Verificar se a resposta não está vazia
                            if (response.length() > 0) {
                                // Obter o primeiro objeto JSON da resposta
                                JSONObject postObject = response.getJSONObject(0);

                                // Extrair o título do post do objeto JSON
                                String title = postObject.getString("title");

                                // Definir o título do post na TextView
                                textPostUsuario.setText(title);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", "Volley Error: " + error.getMessage());
                    }
                });

        // Adicionar a solicitação à fila de requisições
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private void getComments(int userId) {
        String url = "https://jsonplaceholder.typicode.com/comments?userId=" + userId;

        // Fazer a solicitação JSON usando Volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Verificar se a resposta não está vazia
                            if (response.length() > 0) {
                                // Obter o primeiro objeto JSON da resposta
                                JSONObject commentObject = response.getJSONObject(0);

                                // Extrair o corpo do comentário do objeto JSON
                                String body = commentObject.getString("body");

                                // Definir o corpo do comentário na TextView
                                textComentarios.setText(body);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", "Volley Error: " + error.getMessage());
                    }
                });

        // Adicionar a solicitação à fila de requisições
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}
