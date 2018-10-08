package vsl.bhavesh.firebasedemo

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_upload_logo.*
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.coroutines.experimental.Continuation

class UploadLogoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_logo)



        // Runtime Permission [ START ]
        var status = ContextCompat.checkSelfPermission(this@UploadLogoActivity, Manifest.permission.CAMERA)

        // Permission not granted
        if (status== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this@UploadLogoActivity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA),0    )
        }
        // Runtime Permission [ END ]




        // For Camera Button [ START ]
        camera.setOnClickListener {

            var i = Intent("android.media.action.IMAGE_CAPTURE") //open predefine activity
            startActivityForResult(i,123) // request code - any positive number

        }
        // For Camera Button [ END ]


        // For Gallery Button [ START ]
        gallery.setOnClickListener {

            var i = Intent() //create object
            i.action = Intent.ACTION_GET_CONTENT //get all the list of file on action
            i.type = "*/*"  //set the specific time..
            startActivityForResult(i,124) // use different request code if you use multile

        }
        // For Gallery Button [ END ]

    }// onCreate Function


    var uri_obj: Uri? = null //define global variable
    // Override the function OnActivityResult Once result completed this method call..
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //If we have multiple request code then we can separate using that code
        if (requestCode == 123 && resultCode == Activity.RESULT_OK){
            //Camera... now if user capture image or cancel image you can get from result code

            var bmp: Bitmap = data!!.extras.get("data") as Bitmap
            uri_obj = getImageUri(this@UploadLogoActivity,bmp)

            iview.setImageURI(uri_obj) // Set Image URL
           // uploadFile(uri_obj)



        }else if (requestCode == 124 && resultCode == Activity.RESULT_OK){
            // Gallery
            uri_obj = data!!.data //by calling intent object you will get url object
            iview.setImageURI(uri_obj) // Set Image URL
           // uploadFile(uri_obj)


        }

    }


    // Get Image URI from Bitmap [ START ]
    fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null)
        return Uri.parse(path)
    }
    //Get Image URI from Bitmap [ END ]

/*
    fun uploadFile(uri:Uri?)
    {

        var sRef = FirebaseStorage.getInstance().
                getReference("iims_photos")
        var f = File(uri!!.path)
        var child_ref = sRef.child(f.name)
        var upload_task = child_ref.putFile(uri)
        val urlTask = upload_task.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>>({ task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }return@Continuation child_ref.downloadUrl
        })).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                var download_url = downloadUri.toString()
                unique_key!!.child("logo_url").setValue(download_url)

                var i = Intent(this@UploadLogoActivity,
                        ListOfIIMsActivity::class.java)
                startActivity(i)

            } else {
                // Handle failures
                // ...
            }
        }
       }
*/

}









