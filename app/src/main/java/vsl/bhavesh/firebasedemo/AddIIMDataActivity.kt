package vsl.bhavesh.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_iimdata.*



//declare static variable
var unique_key:DatabaseReference? = null

class AddIIMDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_iimdata)

        btnAddIIM.setOnClickListener {

        // open connection
            var dBase = FirebaseDatabase.getInstance()
            // create a table in firebase as a Reference
            var  ref = dBase.getReference("iims")


            unique_key = ref.push()

            // define
            unique_key!!.child("iimname").setValue(iimname.text.toString())
            unique_key!!.child("director").setValue(director.text.toString())
            unique_key!!.child("desc").setValue(desc.text.toString())
            unique_key!!.child("uid").setValue(FirebaseAuth.getInstance().uid)
            unique_key!!.child("logo_url").setValue("")

            // call upload logo Activity
           // var i = Intent(this@AddIIMDataActivity,UploadLogoActivity::class.java)
           // startActivity(i)



        }

    }
}
