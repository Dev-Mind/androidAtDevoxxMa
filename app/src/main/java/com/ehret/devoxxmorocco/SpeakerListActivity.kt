package com.ehret.devoxxmorocco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehret.devoxxmorocco.model.AppDatabase
import com.ehret.devoxxmorocco.model.SpeakerAdapater
import kotlinx.android.synthetic.main.activity_speaker_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


interface OnSpeakerClickListener {
    fun onSpeakerSelected(id: String)
}

class SpeakerListActivity : AppCompatActivity(), OnSpeakerClickListener, CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Default

    override fun onSpeakerSelected(id: String) {
        startActivity(Intent(baseContext, SpeakerActivity::class.java).putExtra("ID", id))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaker_list)

        buttonAddSpeaker.setOnClickListener {
            onSpeakerSelected("")
        }


        speakerList.apply {
            val speakerDao = AppDatabase.instance(baseContext).speakerDao()

            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            launch {
                adapter = SpeakerAdapater(speakerDao.readAll(), this@SpeakerListActivity)
            }
        }
    }
}
