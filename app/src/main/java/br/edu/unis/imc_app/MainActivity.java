package br.edu.unis.imc_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText edtMassa;
    EditText edtAltura;
    TextView txtResultado;
    TextView txtClassificacao;
    Button btnCalcula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregaComponentes();
        configurarBotaoVerificar();
    }

    protected void carregaComponentes() {
        edtMassa = findViewById(R.id.edtMassa);
        edtAltura = findViewById(R.id.edtAltura);
        txtResultado = findViewById(R.id.txtResultado);
        txtClassificacao = findViewById(R.id.txtClassificacao);
        btnCalcula = findViewById(R.id.btnCalcular);
    }

    protected void configurarBotaoVerificar() {
        btnCalcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float imc = realizaCalculo();
                exibirResultado(imc);
                limpaCampos();
            }
        });
    }

    protected Float realizaCalculo() {
        float massa = Float.parseFloat(edtMassa.getText().toString());
        float altura = Float.parseFloat(edtAltura.getText().toString());
        if(validaDadosEntrada(massa, altura))
            return massa / (altura * altura);
        else {
            return (float)0;
        }
    }

    protected Boolean validaDadosEntrada(float massa, float altura) {
        if(altura <= 0 || massa <= 0) {
            return false;
        }
        return true;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    protected void exibirResultado(Float imc) {
        String resposta;
        if(imc < 16) {
            resposta = "Magreza Grave";
        }
        else if (imc < 17) {
            resposta = "Magreza moderada";
        }
        else if (imc < 18.5) {
            resposta = "Magreza leve";
        }
        else if (imc < 25) {
            resposta = "Saudavel";
        }
        else if (imc < 30) {
            resposta = "Sobrepeso";
        }
        else if (imc < 35) {
            resposta = "Obesidade Grau I";
        }
        else if (imc < 40) {
            resposta = "Obesidade Grau II";
        }
        else{
            resposta = "Obesidade Grau III";
        }
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        txtResultado.setText(df.format(imc));
        txtClassificacao.setText(resposta);
    }

    protected void limpaCampos() {
        edtMassa.setText("");
        edtAltura.setText("");
        edtAltura.clearFocus();
    }
}
