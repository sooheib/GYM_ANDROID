package selmibenromdhane.sparta_v1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import selmibenromdhane.sparta_v1.Enumeration.EnumMuscleGroups;
import selmibenromdhane.sparta_v1.Model.Exercise;
import selmibenromdhane.sparta_v1.R;

public class ExerciseDetailsActivity extends AppCompatActivity {

    private TextView tvMuscle, tvOtherMuscles, tvMechanics, tvEquipment, tvName;
    private ImageView image1, image2;
    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        setToolbar();

        exercise = (Exercise) getIntent().getExtras().getSerializable("item");

        if (exercise == null) {
            finish();
            Toast.makeText(this, "Bad Argument", Toast.LENGTH_SHORT);
        }

        setUpContent();

    }

    /**
     * Init and Fill the UI Views with the data from the exercise object
     */
    private void setUpContent() {
        tvMuscle = (TextView) findViewById(R.id.tvExerciseMuscle);
        tvOtherMuscles = (TextView) findViewById(R.id.tvExerciseOtherMuscles);
        tvMechanics = (TextView) findViewById(R.id.tvExerciseMechanics);
        tvEquipment = (TextView) findViewById(R.id.tvExerciseEquipment);
        tvName = (TextView) findViewById(R.id.tvExerciseTitle);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);

        //get exercise's values and display them in the UI views
        String other_muscles = "-";
        if (exercise.getOther_muscles() != null) {
            String[] tokens = exercise.getOther_muscles().split("-");
            StringBuilder s = new StringBuilder();
            for (String t : tokens) {
                s.append(EnumMuscleGroups.values()[Integer.valueOf(t) - 1].toString());
                s.append(" , ");
            }
            s.deleteCharAt(s.length() - 1);
            s.deleteCharAt(s.length() - 1);
            other_muscles = s.toString();
        }

        String mechanics = exercise.getMechanics() == 1 ? "Compound" : "Isolation";
        String muscle = exercise.getMuscle().toString();
        String equipment = exercise.getEquipment().toString();

        Picasso.with(this).load("file:///android_asset/Exercises/" + exercise.getImg1() + ".jpg").into(image1);
        Picasso.with(this).load("file:///android_asset/Exercises/" + exercise.getImg2() + ".jpg").into(image2);
        tvName.setText(exercise.getTitle());
        tvMuscle.setText(Html.fromHtml("<b>Muscle</b> : " + muscle));
        tvOtherMuscles.setText(Html.fromHtml("<b>Other Muscles</b> : " + other_muscles));
        tvMechanics.setText(Html.fromHtml("<b>Mechanics Type</b> : " + mechanics));
        tvEquipment.setText(Html.fromHtml("<b>Equipment</b> : " + equipment));
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
