package selmibenromdhane.sparta_v1.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.adapter.FeedAdapter;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.utils.Gallery;
import selmibenromdhane.sparta_v1.utils.MySingleton;
import selmibenromdhane.sparta_v1.utils.Utils;


public class GallerieActivity extends BaseActivity {
    RecyclerView rv;
    FloatingActionButton btn;
    ArrayList<Gallery> list = new ArrayList<>();
    Context ctx;
    private String userChoosenTask;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    //Image request code
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallerie);
        btn = (FloatingActionButton) findViewById(R.id.btnCreate);
        rv = (RecyclerView) findViewById(R.id.s);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getGallerie(ctx);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "photoooooooooo", Snackbar.LENGTH_SHORT).show();
                selectImage();
                getGallerie( ctx);
                //   uploadMultipart();

            }
        });


    }


    public void getGallerie(final Context ctx) {

        JsonArrayRequest req = new JsonArrayRequest(AppConfig.URL_GALLERIE,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            // Toast.makeText(getApplicationContext(), response.length(), Toast.LENGTH_SHORT).show();

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject person = (JSONObject) response.get(i);

                                list.add(new Gallery(person.getString("nbLike"), person.getString("photos"), person.getString("photoprofile"), person.getString("posted")));
                            }
                            rv.setAdapter(new FeedAdapter(getApplicationContext(), list));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "erreur", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        //data.addAll(data2);


        //data.add(places);

        MySingleton.getinstance(getApplicationContext().getApplicationContext()).addToRequest(req);

    }

    public void uploadMultipart() {
        //getting name for the image
        String name = "d";

        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, AppConfig.UPLOAD_URL)
                    .addFileToUpload(path, "fileToUpload") //Adding file
                    .addParameter("name", name) //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result


    //method to get the file path from uri
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utils.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(GallerieActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utils.checkPermission(GallerieActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
                filePath = data.getData();
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        filePath = data.getData();
        Uri tempUri = getImageUri(getApplicationContext(), thumbnail);
        filePath = tempUri;
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
        uploadMultipart();

    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                filePath = data.getData();
                uploadMultipart();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);

    }

}
