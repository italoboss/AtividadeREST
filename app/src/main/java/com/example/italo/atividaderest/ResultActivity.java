package com.example.italo.atividaderest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button btVoltar = (Button) findViewById(R.id.btnVoltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(nextActivity);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String cep = intent.getStringExtra("cep");
        DownloaderTask downloaderTask = new DownloaderTask(this);
        downloaderTask.execute(cep);
    }

    public void setValuesOfCEP(CEPObject cepObject) {
        EditText editText = (EditText) findViewById(R.id.tfCep);
        if (cepObject == null) { editText.setText("CEP não encontrado!"); return; }
        editText.setText(cepObject.getCep());
        editText.setKeyListener(null);
        editText = (EditText) findViewById(R.id.tfLogradouro);
        editText.setText(cepObject.getLogradouro());
        editText.setKeyListener(null);
        editText = (EditText) findViewById(R.id.tfBairro);
        editText.setText(cepObject.getBairro());
        editText.setKeyListener(null);
        editText = (EditText) findViewById(R.id.tfComplemento);
        editText.setText(cepObject.getComplemento());
        editText.setKeyListener(null);
        editText = (EditText) findViewById(R.id.tfLocalidade);
        editText.setText(cepObject.getLocalidade());
        editText.setKeyListener(null);
        editText = (EditText) findViewById(R.id.tfUf);
        editText.setText(cepObject.getUf());
        editText.setKeyListener(null);
        editText = (EditText) findViewById(R.id.tfUnidade);
        editText.setText(cepObject.getUnidade());
        editText.setKeyListener(null);
        editText = (EditText) findViewById(R.id.tfIbge);
        editText.setText(cepObject.getIbge());
        editText.setKeyListener(null);
        editText = (EditText) findViewById(R.id.tfGia);
        editText.setText(cepObject.getGia());
        editText.setKeyListener(null);
    }

}
