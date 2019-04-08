package cakrait.com.contentarticle.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cakrait.com.contentarticle.R;

public class AddActivity extends AppCompatActivity {

    EditText judul, content, tanggal, phone;
    Button btn_save;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        judul = (EditText) findViewById(R.id.judul);
        content = (EditText) findViewById(R.id.content);
        tanggal = (EditText) findViewById(R.id.tanggal);
        phone = (EditText) findViewById(R.id.phone);

//        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioSex);
//        int radioButtonID = radioGroup.getCheckedRadioButtonId();
//        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
//        String selectedtext = (String) radioButton.getText();

        btn_save = (Button) findViewById(R.id.save);

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tanggal.setText(sdf.format(myCalendar.getTime()));
    }

}
