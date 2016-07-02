package com.example.italo.atividaderest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btBuscar = (Button) findViewById(R.id.btnBuscar);
        final EditText tfCep = (EditText) findViewById(R.id.tfCEP);
        if (btBuscar != null) btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity.this, ResultActivity.class);
                nextActivity.putExtra("cep", tfCep.getText().toString());
                startActivity(nextActivity);
            }
        });
    }
}
