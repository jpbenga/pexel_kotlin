package com.example.pexel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Photo
import android.text.Editable
import android.view.View
import android.webkit.WebSettings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.pexel.databinding.ActivityPexelBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class Pexel : AppCompatActivity() {

    private lateinit var binding: ActivityPexelBinding
    private val idKey = "ID_KEY"
    private val apiKey = "cYHmkDDXEPRHAjpeM4h8oTjXtUSCs5HCGUu0TdFE7zDc6PIhBQhJMrGU"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPexelBinding.inflate(layoutInflater)
        setContentView(binding.root as View)
        setViewItems()
    }

    private fun setViewItems(){
        binding.searchButton.setOnClickListener{ search() }

        if(getIdFromSharedPreferences() != "") {
            binding.photoId.setText(getIdFromSharedPreferences().toString())
        }
    }

    private fun search(){
        if(binding.photoId.text.toString().isEmpty()){
            val alertDialog: AlertDialog = AlertDialog.Builder(this@Pexel).create()
            alertDialog.setMessage("Veuillez saisir un entier")
            alertDialog.setButton(
                AlertDialog.BUTTON_NEUTRAL, "OK"
            ) { dialog, _ -> dialog.dismiss() }
            alertDialog.show()
            return
        }
        callService()
    }

    private fun callService(){
        val service: PexelApi.PexelService =
            PexelApi().getClient().create(PexelApi.PexelService::class.java)

        val call: Call<PhotoApiResponse> =
            service.getPhotos(apiKey, binding.photoId.text.toString())

        call.enqueue(object: Callback<PhotoApiResponse>{
            override fun onResponse(call: Call<PhotoApiResponse>, response: Response<PhotoApiResponse>) {
                updateView(response)
                writeIdToSharePreferences()
            }

            override fun onFailure(call: Call<PhotoApiResponse>, t: Throwable) {
                Toast.makeText(this@Pexel,"f", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateView(response: Response<PhotoApiResponse>){

            if(response.body() != null){
                val body = response.body()
                // à modifier de TOUTE URGENCE!!!!!!!!!!!
                if (body != null) {
                    binding.photoTitle.text = Editable.Factory.getInstance().newEditable(body.photos[0].alt)
                    val glideUrl = body.photos[0].src?.original
                    Glide.with(applicationContext).load(glideUrl)
                        .into(binding.imageView)
                }
            }
        }

    private fun writeIdToSharePreferences(){
        getPreferences(Context.MODE_PRIVATE)
            .edit()
            .putString(idKey, binding.photoId.text.toString())
            .apply()
    }

    private fun getIdFromSharedPreferences(): String? {
        return getPreferences(Context.MODE_PRIVATE).getString(idKey, "")
    }
}


