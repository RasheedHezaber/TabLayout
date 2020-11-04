 package com.example.tablayout

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.tablayout.SecondFragment.Companion.newInstance
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Array.newInstance

 class MainActivity : AppCompatActivity() {
     lateinit var tabLayaut: TabLayout
     lateinit var tabViewPager: ViewPager2
     @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         tabLayaut = findViewById(R.id.tab)

         tabViewPager = findViewById(R.id.pager)


         tabViewPager.adapter = object : FragmentStateAdapter(this) {

             override fun createFragment(position: Int): Fragment {
                 return when (position) {
                     0 -> FirstFragment.newInstance(position.toString(),"")
                     1 -> FirstFragment.newInstance(position.toString(),"")
                     2 -> FirstFragment.newInstance(position.toString(),"")

                     else -> {
                         SecondFragment.newInstance("", "")
                     }
                 }

             }

             override fun getItemCount(): Int {
                 return 3
             }


         }

         TabLayoutMediator(tabLayaut, tabViewPager) { tab, position ->
             tab.text = when (position) {
                 0 -> "tab1"
                 1 -> "tab2"
                 2 -> "tab3"
                 else -> null
             }
             tab.icon = when(position){
                 0 -> getDrawable(R.drawable.ic_baseline_mood_24)
                 1 -> getDrawable(R.drawable.ic_baseline_mood_bad_24)
                 2 -> getDrawable(R.drawable.ic_baseline_mood_24)
                 else -> null
             }
         }.attach()
       /*  tabLayaut.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_mood_24)
         tabLayaut.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_mood_bad_24)
         tabLayaut.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_mood_24)*/

             tabViewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
                 override fun onPageSelected(position: Int) {
                     super.onPageSelected(position)
                     Toast.makeText(this@MainActivity,"page ${position}",Toast.LENGTH_LONG).show()
                 }
             })
         }


 }
