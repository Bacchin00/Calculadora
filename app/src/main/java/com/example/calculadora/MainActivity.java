
package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
public boolean aberto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn0), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toast.makeText(this, "Boa Noiotye", Toast.LENGTH_LONG).show();
    }

    public void numero(View v){

        Button botao = findViewById(v.getId());

        TextView visor = (TextView) findViewById(R.id.txtVisor);

        visor.setText( visor.getText().toString() + botao.getText().toString() );

    }

    public void apagar(View v){

        Button apagar = findViewById(R.id.btnApagarTudo);

        TextView visor = (TextView) findViewById(R.id.txtVisor);

        visor.setText("");
    }

    public void calcular(View v) {
        TextView visor = findViewById(R.id.txtVisor);
        String expressao = visor.getText().toString();

        try {
            Expression e = new ExpressionBuilder(expressao).build();
            double resultado = e.evaluate();
            visor.setText(String.valueOf(resultado));
        } catch (Exception e) {
            visor.setText("Erro");
            Toast.makeText(this, "Erro ao calcular", Toast.LENGTH_SHORT).show();
        }
    }

    public void porcentagem(View v){
        TextView visor = findViewById(R.id.txtVisor);
        Button botao = findViewById(R.id.BtnPorcentagem);
        visor.setText(visor.getText() + "/100*");

    }

    public void parentese(View v) {
        TextView visor = findViewById(R.id.txtVisor);
        if (!aberto) {
            visor.setText(visor.getText().toString() + '(');
            aberto = true;
        } else {
            visor.setText(visor.getText().toString() + ')');
            aberto = false;
        }

    }

    public void apagando(View v) {
        TextView visor = findViewById(R.id.txtVisor);
        String textoAtual = visor.getText().toString();

        if (textoAtual.length() > 0) {
            // Remove o último caractere do texto
            String novoTexto = textoAtual.substring(0, textoAtual.length() - 1);
            visor.setText(novoTexto);
        }
    }
}