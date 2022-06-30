package com.refri.techbox.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.refri.techbox.R
import com.refri.techbox.databinding.ActivityMainBinding
import com.refri.techbox.ui.about.AboutFragment
import com.refri.techbox.ui.newslist.NewsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()

        val newsFragment = NewsListFragment()
        val aboutFragment = AboutFragment()

        setCurrentFragment(newsFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mAbout -> setCurrentFragment(aboutFragment)
                R.id.mList -> setCurrentFragment(newsFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

    private fun bindViews(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}