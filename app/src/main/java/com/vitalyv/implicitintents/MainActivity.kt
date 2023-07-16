package com.vitalyv.implicitintents

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.vitalyv.implicitintents.databinding.ActivityMainBinding


private var urlString = ""
private var locationString = ""
private var shareTextString = ""

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openWebsiteButton.setOnClickListener {
            openWebsite(binding.openWebsiteButton)
        }
        binding.openLocationButton.setOnClickListener {
            openLocation(binding.openLocationButton)
        }
        binding.shareTextButton.setOnClickListener {
            shareText(binding.shareTextButton)
        }

    }


    @SuppressLint("QueryPermissionsNeeded")
    private fun openWebsite(view: View) {
        urlString = binding.websiteEdittext.text.toString()
        val webSite = Uri.parse(urlString)
        val intent = Intent(Intent.ACTION_VIEW, webSite)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent);
        } else {
            Log.d("OpenWebsiteLog", "Can't handle this!");
            val enterUrlError = R.string.enterUrlError
            Toast.makeText(
                this,
                enterUrlError,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun openLocation(view: View) {
        locationString = binding.locationEdittext.toString()
        val urlLoc = Uri.parse("geo:0,0?q=$locationString")
        val intent = Intent(Intent.ACTION_VIEW, urlLoc)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("OpenLocalLog", "Ошибка с координатами");
            val enterUrlError = R.string.enterUrlError
            Toast.makeText(
                this,
                enterUrlError,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun shareText(view:View){
        shareTextString = binding.shareEdittext.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder.from(this).setType(mimeType)
            .setChooserTitle("Share this text with: ")
            .setText(shareTextString)
            .startChooser();
    }


}