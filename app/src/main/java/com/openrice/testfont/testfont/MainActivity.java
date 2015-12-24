package com.openrice.testfont.testfont;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner mSpinner = (Spinner) findViewById(R.id.spinner);
        final RadioGroup styles = (RadioGroup) findViewById(R.id.group_style);
        final TextView result = (TextView) findViewById(R.id.result_text);
        final EditText fontSize = (EditText) findViewById(R.id.font_size);
        final EditText lineSpace = (EditText) findViewById(R.id.line_space);
        final EditText input = (EditText) findViewById(R.id.input_text);
        final String[] stringArr = {"DEFAULT", "DEFAULT_BOLD", "MONOSPACE", "SANS_SERIF", "SERIF"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringArr);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        input.setTypeface(Typeface.DEFAULT);
                        result.setTypeface(Typeface.DEFAULT);
                        break;
                    case 1:
                        input.setTypeface(Typeface.DEFAULT_BOLD);
                        result.setTypeface(Typeface.DEFAULT_BOLD);
                        break;
                    case 2:
                        input.setTypeface(Typeface.MONOSPACE);
                        result.setTypeface(Typeface.MONOSPACE);
                        break;
                    case 3:
                        input.setTypeface(Typeface.SANS_SERIF);
                        result.setTypeface(Typeface.SANS_SERIF);
                        break;
                    case 4:
                        input.setTypeface(Typeface.SERIF);
                        result.setTypeface(Typeface.SERIF);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        styles.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.bold:
                        input.setTypeface(null, Typeface.BOLD);
                        result.setTypeface(null, Typeface.BOLD);
                        break;
                    case R.id.bold_italic:
                        input.setTypeface(null, Typeface.BOLD_ITALIC);
                        result.setTypeface(null, Typeface.BOLD_ITALIC);
                        break;
                    case R.id.italic:
                        input.setTypeface(null, Typeface.ITALIC);
                        result.setTypeface(null, Typeface.ITALIC);
                        break;
                    case R.id.normal:
                        input.setTypeface(null, Typeface.NORMAL);
                        result.setTypeface(null, Typeface.NORMAL);
                        break;
                }
            }
        });
        fontSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                float size = 10;
                if (fontSize.getText().toString().trim().length() == 0) {
                    return;
                }
                try {
                    size = Float.parseFloat(fontSize.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid number format!", Toast.LENGTH_SHORT).show();
                }
                result.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        lineSpace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                float size = 0;
                if (lineSpace.getText().toString().trim().length() == 0) {
                    return;
                }
                try {
                    size = Float.parseFloat(lineSpace.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid number format!", Toast.LENGTH_SHORT).show();
                }
                input.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getResources().getDisplayMetrics()), 1.0f);
                result.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, getResources().getDisplayMetrics()), 1.0f);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                result.setText(input.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}