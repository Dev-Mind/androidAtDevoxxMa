package com.ehret.devoxxmorocco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_speaker_list.*

class SpeakerListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaker_list)

        buttonAddSpeaker.setOnClickListener {
            startActivity(Intent(baseContext, SpeakerActivity::class.java))
        }
    }
}
