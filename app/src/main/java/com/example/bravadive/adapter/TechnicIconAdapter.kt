package com.example.bravadive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bravadive.R
import com.example.bravadive.SpotIcon

class TechnicIconAdapter(val spotIconList: List<SpotIcon>) :
    RecyclerView.Adapter<TechnicIconAdapter.TechnicIconViewHolder>() {


    class TechnicIconViewHolder(root: View) : RecyclerView.ViewHolder(root){

        val iVTechnicalIcon = root.findViewById<ImageView>(R.id.iVTechnicalIcon)

     fun updateIcon(icon: Int){
         iVTechnicalIcon.setImageResource(icon)

     }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechnicIconViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_technical_aspects, parent, false)

        return TechnicIconViewHolder(view)
    }

    override fun onBindViewHolder(holder: TechnicIconViewHolder, position: Int) {
        holder.updateIcon(spotIconList[position].iconId)

    }

    override fun getItemCount(): Int {
        return spotIconList.size

    }


}

