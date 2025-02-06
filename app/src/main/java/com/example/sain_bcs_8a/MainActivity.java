package com.example.sain_bcs_8a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // hooks
    EditText etCnic;

    Button btnClear, btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etCnic.getText().toString().trim();
                parseId(id);

            }
        });



    }


    private void parseId(String id)
    {
        if(id.isEmpty())
        {
            etCnic.setError("Id can't be empty");
            return;
        }

        if(id.length()!=13)
        {
            Toast.makeText(this, R.string.id_is_invalid, Toast.LENGTH_LONG).show();
            return;
        }


        String []months = new String[]{"", "January", "February", "March",
                "April", "May", "June", "July",
                "Aug", "Sep", "Oct", "Nov", "Dec"};

        StringBuilder resultText = new StringBuilder("");
        resultText.append("Date of Birth : ");
        resultText.append(id.substring(4, 6));
        resultText.append(" ");
        resultText.append(months[Integer.parseInt(id.substring(2, 4))]);
        resultText.append(" ");
        resultText.append("19");
        resultText.append(id.substring(0, 2));
        resultText.append("\n");
        resultText.append("Gender : ");
        int flag = Integer.parseInt(id.substring(6, 10));
        if(flag >=0 && flag<5000)
            resultText.append("Female");
        else
            resultText.append("Male");

        resultText.append("\n");
        resultText.append("Citizenship : ");

        if(id.charAt(10) == '0')
            resultText.append("SA citizen");
        else
            resultText.append("Permanent Resident");

        resultText.append("\n");
        resultText.append("Validity : ");
        if(id.charAt(id.length()-1) == '0')
            resultText.append("Invalid");
        else
            resultText.append("Valid");

        Intent i = new Intent(MainActivity.this, Home.class);
        i.putExtra("result", resultText.toString());
        i.putExtra("msg", "Hello I am here");
        startActivity(i);

        finish();
    }

    private void init()
    {
        etCnic = findViewById(R.id.etCnic);

        btnClear = findViewById(R.id.btnClear);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    public void btnClearClicked(View view)
    {
            etCnic.setText("");
    }


}