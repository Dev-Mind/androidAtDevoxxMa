package com.ehret.devoxxmorocco.model

import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class SpeakerDaoTest{

    private lateinit var database:AppDatabase
    private lateinit var dao:SpeakerDao
    private val speaker = Speaker("Guillaume", "EHRET", "12")

    @Before
    fun init(){
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, AppDatabase::class.java).build()

        dao = database.speakerDao()
        dao.create(speaker)
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun readById(){
        val speakerRead = dao.readById("12")
        Truth.assertThat(speakerRead).isEqualTo(speaker)
    }

    @Test
    fun readByUnknownId(){
        val speakerRead = dao.readById("99")
        Truth.assertThat(speakerRead).isNull()
    }

    @Test
    fun update(){
        dao.update(Speaker("Guillaume", "DevMind", "12"))
        val speakerRead = dao.readById("12")
        Truth.assertThat(speakerRead.lastName).isEqualTo("DevMind")
    }

    @Test
    fun delete(){
        Truth.assertThat(dao.readAll()).hasSize(1)
        dao.delete(speaker)
        Truth.assertThat(dao.readAll()).hasSize(0)
    }
}