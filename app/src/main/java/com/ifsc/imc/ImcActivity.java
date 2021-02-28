package com.ifsc.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ImcActivity extends AppCompatActivity {

    String nome;
    Double peso, altura;

    TextView tvNome, tvPeso, tvAltura, tvImc, tvClassificacao;
    DecimalFormat decimalFormat = new DecimalFormat("##,###,###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        Bundle bundle = getIntent().getExtras();

        nome = bundle.getString("nome");
        peso = bundle.getDouble("peso");
        altura = bundle.getDouble("altura");

        tvNome = findViewById(R.id.tvNome);
        tvPeso = findViewById(R.id.tvPeso);
        tvAltura = findViewById(R.id.tvAltura);
        tvImc = findViewById(R.id.tvImc);
        tvClassificacao = findViewById(R.id.tvClassificacao);
    }

    @Override
    protected void onStart() {
        super.onStart();

        tvNome.setText(nome);
        tvPeso.setText(peso.toString());
        tvAltura.setText(altura.toString());

        Double imc = calculaIMC(peso, altura);

        tvImc.setText(decimalFormat.format(imc));
        tvClassificacao.setText(classificaIMC(imc).toString());
    }

    public Double calculaIMC(Double peso, Double altura) {
        Double imc = peso/(altura*altura);
        return imc;
    }

    public String classificaIMC(Double imc) {
        String classificacao = "";
        if (imc <= 18.5) {
            classificacao = "Abaixo do peso.";
        }
        if (imc >= 18.6 && imc <= 24.9 ) {
            classificacao = "Peso ideal.";
        }
        if (imc >= 25.0 && imc <= 29.9 ) {
            classificacao = "Levemente acima do peso.";
        }
        if (imc >= 30.0 && imc <= 34.9 ) {
            classificacao = "Obesidade I.";
        }
        if (imc >= 35.0 && imc <= 39.9 ) {
            classificacao = "Obesidade II.";
        }
        if (imc > 40.0) {
            classificacao = "Obesidade III.";
        }

        return classificacao;
    }
}