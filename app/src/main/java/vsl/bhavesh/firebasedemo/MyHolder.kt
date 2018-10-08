package vsl.bhavesh.firebasedemo

import android.media.Image
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyHolder: RecyclerView.ViewHolder {

    //var iim_image: Image? = null
    var iimname:TextView? = null
    var director: TextView? = null
    var desc: TextView? = null


    constructor(v:View) : super(v){

       // iim_image = v.findViewById(R.id.iim_image)
        iimname = v.findViewById(R.id.iimname)
        director = v.findViewById(R.id.director)
        desc = v.findViewById(R.id.desc)

    }

}