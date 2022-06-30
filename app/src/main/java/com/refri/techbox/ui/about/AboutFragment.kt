package com.refri.techbox.ui.about

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.refri.techbox.R
import com.refri.techbox.base.BaseFragment
import com.refri.techbox.databinding.FragmentAboutBinding
import dagger.hilt.android.AndroidEntryPoint


class AboutFragment : BaseFragment<FragmentAboutBinding, AboutViewModel>(FragmentAboutBinding::inflate) {


    override fun observeData() {
        //do Nothing
    }

    override fun initView() {
        setClickListeners()
    }

    private fun setClickListeners(){
        getViewBinding().llPortfolio.setOnClickListener{
            navigateToPortfolio("https://refriansyari.wixsite.com/refriansyari")
        }

        getViewBinding().llLinkedin.setOnClickListener{
            navigateToPortfolio("https://www.linkedin.com/in/refriansyari/")
        }

    }

    private fun navigateToPortfolio(urls:String){

        val openUrl = Uri.parse(urls)
        val intent = Intent(Intent.ACTION_VIEW, openUrl)
        startActivity(intent)
    }

}