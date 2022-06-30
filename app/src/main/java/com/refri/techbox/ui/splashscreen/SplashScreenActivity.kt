package com.refri.techbox.ui.splashscreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.refri.techbox.databinding.ActivitySplashScreenBinding
import com.refri.techbox.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashDelay()
        bindViews()
    }

    private lateinit var binding: ActivitySplashScreenBinding


    private fun navigateToList() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun bindViews(){
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    private fun splashDelay() {
        lifecycleScope.launch(Dispatchers.IO){
            delay(2000)
            lifecycleScope.launch(Dispatchers.Main){
                navigateToList()
            }
        }
    }

    companion object {

        @JvmStatic
        fun startActivity(context: Context?) {
            val intent = Intent(context, SplashScreenActivity::class.java)
            context?.startActivity(intent)
        }
    }
}