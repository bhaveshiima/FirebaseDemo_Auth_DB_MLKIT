package vsl.bhavesh.firebasedemo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_upload_logo.*
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.google.android.gms.tasks.OnSuccessListener



class MainActivity : AppCompatActivity() {

    // declare the variable
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Runtime Permission [ START ]
        var status = ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA)

        // Permission not granted
        if (status== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.CAMERA),0    )
        }
        // Runtime Permission [ END ]


        // inintlized variable
        mAuth = FirebaseAuth.getInstance();


        // Login Button Action [ START ]
        btnLogin.setOnClickListener {

            mAuth!!.signInWithEmailAndPassword(et1.text.toString(),
                    et2.text.toString()).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this@MainActivity,"Login success...!",Toast.LENGTH_LONG).show()

                    var i = Intent(this@MainActivity,ListOfIIMsActivity::class.java)
                    startActivity(i)

                }else{
                    Toast.makeText(this@MainActivity,"Login failed...!",Toast.LENGTH_LONG).show()
                }

            }
        }
        // Login Button Action [ END ]


        // Signup Button Action [ START ]
        btnRegister.setOnClickListener {

            mAuth!!.createUserWithEmailAndPassword(et1.text.toString(),
                    et2.text.toString()).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this@MainActivity,"Registration is success...!",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this@MainActivity,"Registration is failed...!",Toast.LENGTH_LONG).show()
                }
            }

        }
        // Signup Button Action [ END ]


        // MLKit [ START ]
        btnMLKit.setOnClickListener {

            var i = Intent("android.media.action.IMAGE_CAPTURE") //open predefine activity
            startActivityForResult(i,123) // request code - any positive number


        }
        // MLKit [ END ]

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //If we have multiple request code then we can separate using that code
        if (requestCode == 123 && resultCode == Activity.RESULT_OK){
            //Camera... now if user capture image or cancel image you can get from result code
            var bmp: Bitmap = data!!.extras.get("data") as Bitmap
            var fvision = FirebaseVisionImage.fromBitmap(bmp)

            val textRecognizer = FirebaseVision.getInstance()
                    .onDeviceTextRecognizer



            textRecognizer.processImage(fvision)
                    .addOnSuccessListener {

                        var msg = it.text
                        Toast.makeText(this@MainActivity,msg,Toast.LENGTH_LONG).show()

                    }
                    .addOnFailureListener {
                        // Task failed with an exception
                        // ...
                    }


        }
    }





    // Override Request Permission [ START ]
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Toast Message display
        if (grantResults[0] == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this,"You Cann't send SMS and CALL. First you need to Allow Permission",Toast.LENGTH_LONG).show()
        }
    }
    // Override Request Permission [ END ]


}
