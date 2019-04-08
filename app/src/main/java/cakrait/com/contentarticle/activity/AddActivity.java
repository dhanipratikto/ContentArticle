package cakrait.com.contentarticle.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cakrait.com.contentarticle.R;
import cakrait.com.contentarticle.helper.DatabaseClient;
import cakrait.com.contentarticle.model.room.Content;

public class AddActivity extends AppCompatActivity {

    String status;

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

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioSex);
        btn_save = (Button) findViewById(R.id.save);

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                finish();
//            }
//        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(judul.getText().toString().equals("") || content.getText().toString().equals("") ||
                        tanggal.getText().toString().equals("") || phone.getText().toString().equals("") ||
                        status.equals("")) {
                    Toast.makeText(AddActivity.this, "Harap isi form dulu", Toast.LENGTH_SHORT).show();
                } else {
                    Save();
                }
            }
        });

        status = "";
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioMale:
                        status = "Male";
                        break;
                    case R.id.radioFemale:
                        status = "Female";
                        break;
                }
            }
        });
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

    private void Save() {
        final String mJudul = judul.getText().toString();
        final String mContent = content.getText().toString();
        final String mGender = status;
        final String mPhone = phone.getText().toString();
        final String mTanggal = tanggal.getText().toString();

        class SaveContent extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Content content =  new Content();

                content.setJudul(mJudul);
                content.setMycontent(mContent);
                content.setCategory(mGender);
                content.setTanggal(mTanggal);
                content.setTanggal(mPhone);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().contentDao().insert(content);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Toast.makeText(AddActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddActivity.this, HomeActivity.class));
                finish();
            }
        }

        SaveContent saveContent = new SaveContent();
        saveContent.execute();
    }

}
