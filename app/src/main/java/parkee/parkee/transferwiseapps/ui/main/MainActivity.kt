package parkee.parkee.transferwiseapps.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.ui.home.HomeFragment
import parkee.parkee.transferwiseapps.ui.recipients.RecipientsFragment

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_home -> viewPagerMain.setCurrentItem(0, false)
                R.id.menu_recipients -> viewPagerMain.setCurrentItem(1, false)
            }
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        onClickListener()
    }

    private fun initView() {

        val mainFragmentAdapter = MainViewPagerAdapter(this)

        mainFragmentAdapter.addFragment(HomeFragment())
        mainFragmentAdapter.addFragment(RecipientsFragment())

        viewPagerMain.offscreenPageLimit = 2
        viewPagerMain.isUserInputEnabled = false
        viewPagerMain.adapter = mainFragmentAdapter
    }

    private fun onClickListener() {

        bottomNavMain.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}