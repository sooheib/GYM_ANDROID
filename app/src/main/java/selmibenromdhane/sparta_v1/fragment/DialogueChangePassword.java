package selmibenromdhane.sparta_v1.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.LoginActivity;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.utils.MySingleton;

/**
 * Created by mounir on 27/12/2016.
 */

public class DialogueChangePassword extends DialogFragment {
    LayoutInflater inflater;
    View v;
    EditText  editPassword,editConfirmPassword;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater=getActivity().getLayoutInflater();
        v=inflater.inflate(R.layout.dialogchangepassword,null);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(v).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editConfirmPassword=(EditText)v.findViewById(R.id.confirmPassword);
                editPassword=(EditText)v.findViewById(R.id.changePassword);
                if(editPassword.length()==0 && editConfirmPassword.length()==0)
                {

                    new SweetAlertDialog(getContext())
                            .setContentText("Veuiller remplir les champs ")
                            .show();
                }else if((editConfirmPassword.toString().equals(editPassword.toString()))){

                    changePassword(LoginActivity.userId,editPassword.getText().toString());
                    new SweetAlertDialog(getContext())
                            .setContentText("vous avez modifier votre profile")
                            .show();
                }
                else {new SweetAlertDialog(getContext())
                        .setContentText(editConfirmPassword.getText().toString())
                        .show();
                    new SweetAlertDialog(getContext())
                            .setContentText("veuillez retapez le mot de passe")
                            .show();
                }

            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();

    }


    public void changePassword(final String userID,final String password )
    {
        final String resp = "";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, AppConfig.URL_CHANGEPASSWORD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       if (response.equals("succeyys"))
                       {
                           System.out.println("d");
                       //    Toast.makeText(getContext(),"dd",Toast.LENGTH_LONG).show();

                       }
                        else                            System.out.println("failed");

                        //     Toast.makeText(getContext(),"succes",Toast.LENGTH_LONG).show();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("userID",userID);
                params.put("password",password);
                return params;
            }
        };
        MySingleton.getinstance(getActivity().getApplicationContext()).addToRequest(stringRequest);

    }

}
