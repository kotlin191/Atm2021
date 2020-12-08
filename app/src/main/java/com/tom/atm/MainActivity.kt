package com.tom.atm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity() {
    companion object {
        val RC_LOGIN = 30
    }
    var login = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userid = getSharedPreferences("atm", MODE_PRIVATE)
            .getString("PREF_USERID", "")
        ed_userid.setText(userid)
        if (!login) {
            Intent(this, LoginActivity::class.java).apply {
                startActivityForResult(this, RC_LOGIN)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_LOGIN) {
            if (resultCode == RESULT_OK) {
                val userid = data?.getStringExtra("LOGIN_USERID")
                val passwd = data?.getStringExtra("LOGIN_PASSWD")
                Log.d("RESULT", "$userid / $passwd")
            }
        }

    }
}