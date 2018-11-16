package com.ehret.devoxxmorocco.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ehret.devoxxmorocco.R


class SpeakerAdapater(val speakers: List<Speaker>): RecyclerView.Adapter<SpeakerAdapater.ViewHolder>(){


    class ViewHolder(view: ConstraintLayout) : RecyclerView.ViewHolder(view){
        val name = view.findViewById(R.id.speakerName) as TextView
        val country = view.findViewById(R.id.speakerCountry) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_speaker_item, parent, false)
        return ViewHolder(view as ConstraintLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speaker = speakers[position]
        holder.apply {
            name.text = "${speaker.firstName} ${speaker.lastName}"
            country.text = speaker.country
        }
    }

    override fun getItemCount()= speakers.size
}