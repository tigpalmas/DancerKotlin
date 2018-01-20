package com.example.tiago.dancerkotlin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.tiago.dancerkotlin.fragments.DancersFragment
import com.example.tiago.dancerkotlin.fragments.ListEventsFragment
import kotlinx.android.synthetic.main.activity_scrolling.*
import com.example.tiago.dancerkotlin.fragments.UserPerfilFragment
import com.example.tiago.dancerkotlin.utils.BottomNavigationViewHelper


class MainActivity : AppCompatActivity() {

   val manager = supportFragmentManager


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                val fragment = ListEventsFragment();
                ShowFragmnetON(fragment, "fragment1");
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val fragment = DancersFragment();
                ShowFragmnetON(fragment, "fragment1");
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                //  message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_perfil -> {
                //  message.setText(R.string.title_notifications)
                val fragment = UserPerfilFragment();
                ShowFragmnetON(fragment, "fragmentPerfil");
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val fragment = ListEventsFragment();
        ShowFragmnetON(fragment, "fragment1");

        setSupportActionBar(toolbar)

       navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.disableShiftMode(navigation);

    }

    fun ShowFragmnetON(fragment: Fragment, tag: String){
        val transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment,tag);
        transaction.commit();
    }
}
