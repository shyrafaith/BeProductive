package com.example.beproductive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.beproductive.databinding.ActivityMainBinding
import com.example.beproductive.HomeFragment // Import Home fragment
import com.example.beproductive.Add // Import Add fragment
import com.example.beproductive.Inbox // Import Inbox fragment (if you have this fragment)

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())


        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home ->replaceFragment(HomeFragment())
                R.id.add ->replaceFragment(Add())
                R.id.inbox ->replaceFragment(Inbox())

                else -> {


                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()




    }
}
