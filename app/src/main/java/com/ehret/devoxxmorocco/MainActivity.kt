package com.ehret.devoxxmorocco

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.global, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuGithub -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"))
                startActivity(intent)
            }
            R.id.menuMail -> {
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto://guillaume@dev-mind.fr"))
                startActivity(intent)
            }
            R.id.menuSpeakerList -> {
                startActivity(Intent(baseContext, SpeakerListActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
