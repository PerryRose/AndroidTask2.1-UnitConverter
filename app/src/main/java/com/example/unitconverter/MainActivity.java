package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Variables
    Spinner unitSpinner;
    EditText valueToConvert;
    TextView resultTextView1, resultTextView2, resultTextView3;
    TextView unitTextView1, unitTextView2, unitTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set Variables
        setVariables();

        // Create Array Adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unitsToConvert, R.layout.support_simple_spinner_dropdown_item);

        // Specify layout when list appears
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // Apply adapter to spinner
        unitSpinner.setAdapter(adapter);

    }

    private void setVariables() {
        // Set Variables
        unitSpinner = findViewById(R.id.unitsToConvertSpinner);
        valueToConvert = findViewById(R.id.valueToConvertEditText);
        resultTextView1 = findViewById(R.id.resultTextView1);
        resultTextView2 = findViewById(R.id.resultTextView2);
        resultTextView3 = findViewById(R.id.resultTextView3);
        unitTextView1 = findViewById(R.id.unitTextView1);
        unitTextView2 = findViewById(R.id.unitTextView2);
        unitTextView3 = findViewById(R.id.unitTextView3);
    }

    public void convertValue(View view) {
        // If there is a value to convert
        if (!valueToConvert.getText().toString().isEmpty()) {
            // If correct image is selected
            if (imageSelectedIsUnitSelected(view.getId())) {
                // Get value
                double value = Double.parseDouble(valueToConvert.getText().toString());
                // Covert value
                convertValue(value, view.getId());
            }
            else {
                Toast.makeText(this, "Please select correct conversion icon",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Please enter a value to convert",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private boolean imageSelectedIsUnitSelected(int id) {
        // Get Selected Unit
        String selectedUnit = unitSpinner.getSelectedItem().toString();
        // Check if image selected is the unit selected
        switch (id) {
            case R.id.lengthImageView: if (selectedUnit.equals("Metre")) return true; break;
            case R.id.temperatureImageView: if (selectedUnit.equals("Celsius")) return true; break;
            case R.id.weightImageView: if (selectedUnit.equals("Kilogram")) return true; break;
        }
        return false;
    }

    private void convertValue(double value, int id) {
        // Clear Text Views
        clearTextViews();
        // Convert
        switch (id) {
            // Measure
            case R.id.lengthImageView:
                double centimetre = value * 100;
                resultTextView1.setText(String.format("%.2f", centimetre));
                unitTextView1.setText("Centimetre");

                double foot = value * 3.281;
                resultTextView2.setText(String.format("%.2f", foot));
                unitTextView2.setText("Foot");

                double inch = value * 39.37;
                resultTextView3.setText(String.format("%.2f", inch));
                unitTextView3.setText("Inch");
                break;

            // Temperature
            case R.id.temperatureImageView:
                double fahrenheit = (value * 9/5) + 32;
                resultTextView1.setText(String.format("%.2f", fahrenheit));
                unitTextView1.setText("Fahrenheit");

                double kelvin = value + 273.15;
                resultTextView2.setText(String.format("%.2f", kelvin));
                unitTextView2.setText("Kelvin");
                break;

            // Weight
            case R.id.weightImageView:
                double gram = value * 1000;
                resultTextView1.setText(String.format("%.2f", gram));
                unitTextView1.setText("Gram");

                double ounce = value * 35.274;
                resultTextView2.setText(String.format("%.2f", ounce));
                unitTextView2.setText("Ounce (Oz)");

                double pound = value * 2.205;
                resultTextView3.setText(String.format("%.2f", pound));
                unitTextView3.setText("Pound (Lb)");
                break;
        }
    }

    private void clearTextViews() {
        resultTextView1.setText("");
        resultTextView2.setText("");
        resultTextView3.setText("");
        unitTextView1.setText("");
        unitTextView2.setText("");
        unitTextView3.setText("");
    }

}