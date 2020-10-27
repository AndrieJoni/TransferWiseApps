package parkee.parkee.transferwiseapps.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import parkee.parkee.transferwiseapps.R
import parkee.parkee.transferwiseapps.session.SessionManager
import parkee.parkee.transferwiseapps.ui.home.HomeFragment
import parkee.parkee.transferwiseapps.ui.login.LoginActivity
import parkee.parkee.transferwiseapps.ui.recipients.RecipientsFragment
import parkee.parkee.transferwiseapps.ui.transferMoney.TransferMoneyActivity

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_home -> {
                    viewPagerMain.setCurrentItem(0, false)
                    true
                }
                R.id.menu_recipients -> {
                    viewPagerMain.setCurrentItem(1, false)
                    true
                }
                R.id.menu_send -> {
                    startActivity(Intent(this, TransferMoneyActivity::class.java))
                    false
                }

                else -> true
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        onClickListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TransferMoneyActivity.REQUEST_TRANSFER_MONEY && resultCode == RESULT_OK) {
            bottomNavMain.selectedItemId = R.id.menu_home
        }
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

        toolbarMain.menu.findItem(R.id.menu_logout).setOnMenuItemClickListener {
            SessionManager(this).isLogin = false
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            true
        }
    }
}