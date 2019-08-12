package com.example.gottagetget.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import com.example.gottagetget.R
import kotlinx.android.synthetic.main.activity_image_search.*

class ImageSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_search)
        setSupportActionBar(toolbar_search_image as Toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        search_view_image_search.setMenuItem(menu?.findItem(R.id.action_search))

        return true
    }
}
