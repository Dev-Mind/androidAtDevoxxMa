package com.ehret.devoxxmorocco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehret.devoxxmorocco.model.AppDatabase
import com.ehret.devoxxmorocco.model.SpeakerAdapater
import kotlinx.android.synthetic.main.activity_speaker_list.*

class SpeakerListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaker_list)

        buttonAddSpeaker.setOnClickListener {
            startActivity(Intent(baseContext, SpeakerActivity::class.java))
        }


        speakerList.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            adapter = SpeakerAdapater(AppDatabase.speakerDao.readAll())
        }
    }
}
