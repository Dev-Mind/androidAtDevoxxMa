package com.ehret.devoxxmorocco

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ehret.devoxxmorocco.model.AppDatabase
import com.ehret.devoxxmorocco.model.Speaker
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_speaker.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SpeakerActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaker)

        val id = intent.getStringExtra("ID")
        val speakerDao = AppDatabase.instance(baseContext).speakerDao()

        if (id.isNotEmpty()) {
            launch {
                val speaker = speakerDao.readById(id)
                speakerFirstname.setText(speaker.firstName)
                speakerLastname.setText(speaker.lastName)
                speakerCountry.setText(speaker.country)
            }
        }

        buttonSaveSpeaker.setOnClickListener {
            buttonSaveSpeaker.setOnClickListener {

                if (speakerFirstname.text.isNullOrBlank() || speakerLastname.text.isNullOrBlank()) {
                    Snackbar
                        .make(
                            it,
                            R.string.speaker_error_required,
                            Snackbar.LENGTH_LONG
                        )
                        .setAction("Error", null)
                        .show()

                } else {
                    val id = intent.getStringExtra("ID")
                    launch {
                        if (id.isNullOrBlank()) {
                            speakerDao.create(
                                Speaker(
                                    speakerFirstname.text.toString(),
                                    speakerLastname.text.toString(),
                                    country = if (speakerCountry.text.isBlank()) "MOROCCO" else speakerCountry.text.toString()
                                )
                            )

                        } else {
                            speakerDao.update(
                                Speaker(
                                    speakerFirstname.text.toString(),
                                    speakerLastname.text.toString(),
                                    id,
                                    if (speakerCountry.text.isBlank()) "MOROCCO" else speakerCountry.text.toString()
                                )
                            )

                        }
                    }
                    startActivity(Intent(this, SpeakerListActivity::class.java))
                }
            }
        }
    }
}
