package vsl.bhavesh.firebasedemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter:RecyclerView.Adapter<MyHolder>() {

    var activity:ListOfIIMsActivity? = null
    var list: MutableCollection<IIMBean>? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var  inflater = LayoutInflater.from(activity)
        var view = inflater.inflate(R.layout.indview, parent,false)

        return MyHolder(view)

    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
/*
        holder.iimname!!.text = list!!.get(position).iimname;
        holder.director!!.text = list!!.get(position).director;
        holder.desc!!.text = list!!.get(position).desc;

*/
        //Glide.with(activity!!).load(list!!.get(position).iim_logo).into(holder.iim_image);


    }
}