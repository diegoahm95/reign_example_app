package com.aurapps.reigntask.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aurapps.reigntask.R
import com.aurapps.reigntask.view.fragments.StoriesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment()
    }

    private fun setFragment(){
        supportFragmentManager.beginTransaction().replace(
            R.id.container, StoriesFragment.newInstance()
        ).commit()
    }

}