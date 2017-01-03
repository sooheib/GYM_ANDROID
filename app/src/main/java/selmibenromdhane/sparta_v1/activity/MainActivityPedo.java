package selmibenromdhane.sparta_v1.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.fragment.DailyReportFragment;
import selmibenromdhane.sparta_v1.fragment.MainFragment;
import selmibenromdhane.sparta_v1.fragment.MonthlyReportFragment;
import selmibenromdhane.sparta_v1.fragment.WeeklyReportFragment;
import selmibenromdhane.sparta_v1.utils.StepDetectionServiceHelper;

public class MainActivityPedo extends BaseActivityPedo implements DailyReportFragment.OnFragmentInteractionListener, WeeklyReportFragment.OnFragmentInteractionListener, MonthlyReportFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainn);

        // init preferences
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_notification, false);

        // show welcome dialog on first run
        String isNotFirstRun = PreferenceManager.getDefaultSharedPreferences(this).
                getString(getString(R.string.pref_is_not_first_run), "");
//        if (isNotFirstRun.equals("")) {
//            PreferenceManager.getDefaultSharedPreferences(this).edit().
//                    putString(getString(R.string.pref_is_not_first_run), "true").apply();
//            WelcomeDialog welcomeDialog = new WelcomeDialog();
//            welcomeDialog.show(getFragmentManager(), "WelcomeDialog");
//        }

        // Load first view
        final android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, new MainFragment(), "MainFragment");
        fragmentTransaction.commit();

        // Start step detection if enabled and not yet started
        StepDetectionServiceHelper.startAllIfEnabled(this);
        //Log.i(LOG_TAG, "MainActivityPedo initialized");
    }

//    @Override
//    protected int getNavigationDrawerID() {
//        return R.id.menu_home;
//    }


    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
