package br.edu.ifrn.preferenciasdousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvar);
        editNome = findViewById(R.id.editNome);
        textResultado = findViewById(R.id.textResultado);

        //Configurando o click do botão salvar
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Salva os dados do usuário dentro do próprio aparelho celular através de uma classe xml
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                //Validando o nome
                if(editNome.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha o nome", Toast.LENGTH_LONG).show();
                } else {
                    String nome = editNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                    textResultado.setText("Olá, " + nome + "!");
                }
            }
        });
        //Recuperando os dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        //Valida se temos o nome em preferências
        if(preferences.contains("nome")) {
            String nome = preferences.getString("nome", "Usuário não definido!");
            textResultado.setText("Olá, " + nome + "!");
        } else {
            textResultado.setText("Olá, usuário não definido!");
        }
    }
}