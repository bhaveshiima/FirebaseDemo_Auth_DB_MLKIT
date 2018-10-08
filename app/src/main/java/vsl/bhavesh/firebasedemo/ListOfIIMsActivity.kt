package vsl.bhavesh.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_list_of_iims.*

class ListOfIIMsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_iims)


        fab.setOnClickListener {
            var i = Intent(this@ListOfIIMsActivity, AddIIMDataActivity::class.java)
            startActivity(i)
        }

        /*
        var dBase = FirebaseDatabase.getInstance()
        var dRef = dBase.getReference("iims")
        dRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(iims: DatabaseError) {

            }

            override fun onDataChange(iims: DataSnapshot) {
                var list = mutableListOf<IIMBean>()
                var it:Iterable<DataSnapshot> =  iims.children
                //var itr:Iterator<DataSnapshot> = it.iterator()
                // will repeat the loop for childs of IIMs
                it.forEach {

                   var iimname: String? = null
                   var director: String? = null
                   var desc: String? = null
                   var uid: String? = null
                   var logo_url: String? = null

                    var it_children = it.children
                    it.children.forEach {

                        when(it.key){
                            "iimname"-> {iimname = it.value.toString()}
                            "director"-> {director = it.value.toString()}
                            "desc"-> {desc = it.value.toString()}
                            "uid"-> {uid = it.value.toString()}
                            "logo_url"-> {logo_url = it.value.toString()}
                        }
                    }

                    var bean = IIMBean(iimname!!,director!!,desc!!,uid!!,logo_url!!)
                    list.add(bean)


                }

                // Now display
                var lManager = LinearLayoutManager(this@ListOfIIMsActivity,LinearLayoutManager.VERTICAL,false)
                rview.layoutManager = lManager
                rview.adapter = MyAdapter(this@ListOfIIMsActivity,list)


            }

        })
        */
    }
}
