package com.example.parmeshmahore.movielistdemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.parmeshmahore.movielistdemo.R
import com.example.parmeshmahore.movielistdemo.model.Movie

public class ListAdapter(val itemList : List<Movie>?, val context : Context) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = itemList!!.get(position)
        holder.tvCountryName.text = movie.title
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvCountryName = view.findViewById<TextView>(R.id.tv_country_name);
    }

    override fun getItemCount(): Int {
        return itemList!!.size
    }

}