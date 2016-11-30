package selmibenromdhane.sparta_v1.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.utils.CircleTransform;
import selmibenromdhane.sparta_v1.utils.Utils;

public class ProfileActivity extends BaseActivity {
private EditText height,weight;
    private TextView imc;
    private ImageView photoCouverture;
    private ImageButton photoProfile;
    private String userChoosenTask;
    private static int choice;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        photoCouverture=(ImageView)findViewById(R.id.header_cover_image);
        photoProfile=(ImageButton)findViewById(R.id.user_profile_photo);
        photoCouverture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                choice=1;
                selectImage();
            }
        });
        photoProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                choice=2;
                selectImage();
            }
        });
        height=(EditText)findViewById(R.id.input_height);
        weight=(EditText)findViewById(R.id.input_weight);
        imc=(TextView)findViewById(R.id.imc);


            height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (height.getText().toString().length()>1&&weight.getText().length()>1)
                    {   Float heightF=Float.parseFloat(height.getText().toString());
                        Float weightF=Float.parseFloat(weight.getText().toString());
                        imc.setText("IMC ="+String.valueOf((10000*(weightF/(heightF*heightF)))));
                    }
                }
            });

        weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (height.getText().toString().length()>1&&weight.getText().length()>1)
                {
                    Float heightF=Float.parseFloat(height.getText().toString());
                    Float weightF=Float.parseFloat(weight.getText().toString());
                    imc.setText("IMC ="+String.valueOf(10000*(weightF/(heightF*heightF))));


                }
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utils.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utils.checkPermission(ProfileActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
if(choice==1)
{
    photoCouverture.setImageBitmap(thumbnail);

}
        else
{
    Bitmap bm = new CircleTransform().transform(thumbnail);
    photoProfile.setImageBitmap(bm);
}
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(choice==1)
        {
            photoCouverture.setImageBitmap(bm);

        }

        else {
            Bitmap bdm = new CircleTransform().transform(bm);
            photoProfile.setImageBitmap(bdm);
        }               }


}
