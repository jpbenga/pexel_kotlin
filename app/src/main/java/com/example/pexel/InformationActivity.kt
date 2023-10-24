package com.example.pexel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.pexel.databinding.ActivityInformationBinding

class InformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setViewItems()
    }

    private  fun setViewItems(){
        val photographer = intent.extras?.getString(Pexel.photographerKey)
        val image = intent.extras?.getString(Pexel.imageKey)
        binding.photographNameInformation.text = photographer
        Glide.with(this).load(image).into(binding.photoPexelInformation)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}