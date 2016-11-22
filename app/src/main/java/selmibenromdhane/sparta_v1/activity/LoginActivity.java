
package selmibenromdhane.sparta_v1.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.app.AppController;
import selmibenromdhane.sparta_v1.helper.SQLiteUserHandler;
import selmibenromdhane.sparta_v1.helper.SessionManager;
import selmibenromdhane.sparta_v1.manager.Client;


public class LoginActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    public static final String CLIENT_ID="id";
    public static String userId;
    String x="";
    String a="1";
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteUserHandler db;
    private String userID;
    Bundle bundle=new Bundle();

    private SharedPreferences pref; // 0 - for private mode
    private SharedPreferences.Editor editor;
    private static final int PREFERENCE_MODE_PRIVATE=0;
    Client client;



    public LoginActivity() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref=getPreferences(PREFERENCE_MODE_PRIVATE);
      //  editor=pref.edit();


        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
       // String email = inputEmail.getText().toString().trim();
       // System.out.println("jhjjjjjjjjjjjjjjjjj"+email);
        //String password = inputPassword.getText().toString().trim();


        btnLogin = (Button) findViewById(R.id.btnLogin);
        //  btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteUserHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

//        bundle.putString("key1","123"); // Key1
//
//        String cc = bundle.getString("key1");




        //     String cc=bundle.getString("key1");

        //System.out.println("okokokokokokokokok"+cc);
        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
           // String souheib= pref.getString("key_name",""); // getting Integer

          //  System.out.println("asasasasasasasqqqqqqqqq"+souheib);

            userId="2";

            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
            startActivity(intent);
            finish();
        }





        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();


                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                  checkLogin(email, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

        // Link to Register Screen
//        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(),
//                        RegisterActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });

    }

    /**
     * function to verify login details in mysql db
     * */

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public String getUserID() {
        return userId;
    }

    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

          pDialog.setMessage("Logging in ...");
          showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");
                        userID=uid;

                        JSONObject user = jObj.getJSONObject("user");
                        userId= user.getString("id");

                        //pref=getPreferences(PREFERENCE_MODE_PRIVATE);
                       // editor=pref.edit();
                        //editor.putString("key_name", userId);
                        //editor.commit(); // commit changes

                        // Bundle extras = getIntent().getExtras();
//                        String cc = bundle.getString("key1");
//
//                        //     String cc=bundle.getString("key1");
//                        System.out.println("okokokokokokokokok"+cc);

                        x=userId;
                        System.out.println("**********user**********"+userId);
                        Client client=new Client();
                        client.setClient_id(userId);
                        String client_id = user.getString("id");
                        System.out.println("*****client_id******"+client_id);


                       // Intent intentclient = new Intent("GO").putExtra(CLIENT_ID, client_id);
                        //LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intentclient);
                        // Client client=new Client();
                        //client.setClient_id(client_id);

                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at);
                        Toast.makeText(getApplicationContext(), userId, Toast.LENGTH_SHORT).show();

                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this,
                                HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

      //  bundle.putString("key1",userId); // Key1

    }


}