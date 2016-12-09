package selmibenromdhane.sparta_v1.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.utils.Constants;
import selmibenromdhane.sparta_v1.utils.ImageUtils;

public class EditProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private final int REQUEST_CODE_IMAGE = 1;

    private SwitchCompat switchSex;
    private EditText etName, etWeight, etHeight, etBmi, etFat, etBirthDate, etSex;
    private boolean isMale;
    private String photoPath;
    private Date birthDate;
    private Button addHeight, subHeight, addWeight, subWeight, calcBMI, addFat, subFat;
    private ImageView profilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setUpToolbar();

        setUpFab();

        setUpViews();

    }

    private void setUpViews() {
        profilePhoto = ((ImageView) findViewById(R.id.profile_photo));
        profilePhoto.setOnClickListener(this);

        switchSex = (SwitchCompat) findViewById(R.id.switch_sex);

        etSex = (EditText) findViewById(R.id.input_sex);
        etName = (EditText) findViewById(R.id.input_name);
        etBirthDate = (EditText) findViewById(R.id.input_age);
        etWeight = (EditText) findViewById(R.id.input_weight);
        etHeight = (EditText) findViewById(R.id.input_height);
        etBmi = (EditText) findViewById(R.id.input_bmi);
        etFat = (EditText) findViewById(R.id.input_fat);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //load the saved image in the view
        Uri imageUri = Uri.parse(prefs.getString(Constants.TAG_PHOTO_PATH, ""));
        photoPath = imageUri.toString();
        ImageUtils.loadProfileImage(EditProfileActivity.this, imageUri, profilePhoto);

        isMale = prefs.getBoolean(Constants.TAG_ISMALE, true);
        switchSex.setChecked(isMale);
        switchSex.setOnCheckedChangeListener(this);
        onCheckedChanged(switchSex, isMale);

        etName.setText(prefs.getString(Constants.TAG_NAME, "John Who"));
        etBirthDate.setText(prefs.getString(Constants.TAG_BIRTHDATE, "1 Jan 1985"));
        etWeight.setText(prefs.getFloat(Constants.TAG_WEIGHT, 80) + "");
        etHeight.setText(prefs.getInt(Constants.TAG_HEIGHT, 180) + "");
        etBmi.setText(prefs.getFloat(Constants.TAG_BMI, 25) + "");
        etFat.setText(prefs.getFloat(Constants.TAG_FAT, 20) + "");

        etHeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setUpBmi();
                }
            }
        });
        etWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setUpBmi();
                }
            }
        });

        addWeight = (Button) findViewById(R.id.bAddWeight);
        subWeight = (Button) findViewById(R.id.bSubstractWeight);
        addHeight = (Button) findViewById(R.id.bAddHeight);
        subHeight = (Button) findViewById(R.id.bSubstractHeight);
        addFat = (Button) findViewById(R.id.bAddFat);
        subFat = (Button) findViewById(R.id.bSubstractFat);

        addWeight.setOnClickListener(this);
        subWeight.setOnClickListener(this);
        addHeight.setOnClickListener(this);
        subHeight.setOnClickListener(this);
        addFat.setOnClickListener(this);
        subFat.setOnClickListener(this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            //when coming back from "choose image" intent
            case REQUEST_CODE_IMAGE:
                if (resultCode == RESULT_OK) {

                    Uri imageUri = intent.getData();
                    photoPath = imageUri.toString();
                    ImageUtils.loadProfileImage(EditProfileActivity.this, imageUri, profilePhoto);
                }
        }
    }


    public void showDatePickerDialog(View v) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, 1985);
        date.set(Calendar.MONTH, Calendar.JANUARY);
        date.set(Calendar.DAY_OF_MONTH, 1);
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setOnDateSetListener(this);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Toast.makeText(EditProfileActivity.this, "Changes Saved", Toast.LENGTH_LONG).show();
                NavUtils.navigateUpFromSameTask(EditProfileActivity.this);
            }
        });
    }

    private void saveData() {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putBoolean(Constants.TAG_ISMALE, isMale);
        editor.putString(Constants.TAG_NAME, etName.getText().toString());
        editor.putString(Constants.TAG_BIRTHDATE, etBirthDate.getText().toString());
        editor.putFloat(Constants.TAG_WEIGHT, Float.valueOf(etWeight.getText().toString()));
        editor.putInt(Constants.TAG_HEIGHT, Integer.valueOf(etHeight.getText().toString()));
        editor.putFloat(Constants.TAG_BMI, Float.valueOf(etBmi.getText().toString()));
        editor.putFloat(Constants.TAG_FAT, Float.valueOf(etFat.getText().toString()));
        editor.putString(Constants.TAG_PHOTO_PATH, photoPath);

        editor.commit();
    }

    private double getBmi(double height, double weight) {
        double value = weight / (height * height);
        value = Math.round(value * 10) / 10.0;

        return value;
    }

    private void setUpBmi() {
        if (etWeight.getText().toString().trim().length() != 0
                && etHeight.getText().toString().trim().length() != 0) {
            etBmi.setText(getBmi(Double.valueOf(etHeight.getText().toString()) / 100,
                    Double.valueOf(etWeight.getText().toString())) + "");
        } else {
            etBmi.setText("N/A");
            Snackbar.make(etBmi, "Weight or height is missing", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        birthDate = c.getTime();
        etBirthDate.setText(new SimpleDateFormat("dd MMM yyyy").format(birthDate));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        isMale = isChecked;
        etSex.setText(isMale ? "MALE" : "FEMALE");
    }

    @Override
    public void onClick(View v) {
        double current;
        switch (v.getId()) {
            case R.id.profile_photo:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, REQUEST_CODE_IMAGE);

                break;
            case R.id.bAddWeight:
                current = Double.valueOf(etWeight.getText().toString());
                current += 1;
                etWeight.setText(current + "");
                setUpBmi();
                break;
            case R.id.bSubstractWeight:
                current = Double.valueOf(etWeight.getText().toString());
                current -= 1;
                etWeight.setText(current + "");
                setUpBmi();
                break;
            case R.id.bAddHeight:
                current = Double.valueOf(etHeight.getText().toString());
                current += 1;
                etHeight.setText((int) current + "");
                setUpBmi();
                break;
            case R.id.bSubstractHeight:
                current = Double.valueOf(etHeight.getText().toString());
                current -= 1;
                etHeight.setText((int) current + "");
                setUpBmi();
                break;
            case R.id.bAddFat:
                current = Double.valueOf(etFat.getText().toString());
                current += 0.5;
                etFat.setText(current + "");
                break;
            case R.id.bSubstractFat:
                current = Double.valueOf(etFat.getText().toString());
                current -= 0.5;
                etFat.setText(current + "");
                break;
        }
    }

    private void showExitDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Stay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                NavUtils.navigateUpFromSameTask(EditProfileActivity.this);
            }
        });
        builder.setTitle("Caution");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("Unsaved changes will be lost");

        builder.create().show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            showExitDialog();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }
}
