package com.notificationshare.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText editText;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));
*/
        editText = findViewById(R.id.editTextPhone);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                String code = "088";

                 number= editText.getText().toString().trim();

                if (number.isEmpty() || number.length() < 11) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }

                String phonenumber = "+88"+number;

                if (FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().equals(number)) {
                    Log.e("input_number",number);
                    Toast.makeText(RegisterActivity.this, "This is already used", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                    intent.putExtra("phonenumber", phonenumber);
                    Toast.makeText(RegisterActivity.this, "You are eligible", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });

    }
}
