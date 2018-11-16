package com.ehret.devoxxmorocco.model

import java.util.*

data class Speaker(
    val firstName: String,
    val lastName: String,
    val uuid: String = UUID.randomUUID().toString(),
    val country: String = "MOROCCO"
)

class AppDatabase{

    companion object {
        private val speakers = mutableListOf(Speaker("Guillaume", "EHRET", country = "FRANCE"))
        val speakerDao = SpeakerDao(speakers)
    }
}


class SpeakerDao(val speakers:MutableList<Speaker>){

    fun create(speaker: Speaker){
        speakers.add(speaker)
    }

    fun update(speaker: Speaker){
        speakers.removeAll {  it.uuid == speaker.uuid }
        speakers.add(speaker)
    }

    fun readAll(): List<Speaker> = speakers

    fun readById(id: String): Speaker = speakers.first { it.uuid == id }

    fun delete(speaker: Speaker){
        speakers.removeAll {  it.uuid == speaker.uuid }
    }
}