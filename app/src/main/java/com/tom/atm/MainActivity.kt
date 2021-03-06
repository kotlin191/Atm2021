package com.tom.atm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_login.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    companion object {
        val RC_LOGIN = 30
        val REQUEST_CAMERA = 50
        val TAG = MainActivity::class.java.simpleName
    }
    var login = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!login) {
            Intent(this, LoginActivity::class.java).apply {
                startActivityForResult(this, RC_LOGIN)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_work -> {
                val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
                    .setInitialDelay(30, TimeUnit.SECONDS)
                    .build()
                WorkManager.getInstance(this)
                    .enqueue(workRequest)
                val sdf = SimpleDateFormat("HH:mm:ss")
                Log.d(TAG,"start: ${sdf.format(Date())}")
            }
            R.id.action_maps -> {
                startActivity(Intent(this, MapsActivity::class.java))
            }
            R.id.action_service -> {
                startService(Intent(this, MyService::class.java))
            }
            R.id.action_expense -> {
                val exp = Intent(this, ExpenseActivity::class.java)
                startActivity(exp)
            }
            R.id.action_contacts -> {
                startActivity(Intent(this, MaterialActivity::class.java))
            }
            R.id.action_camera -> {
                val camera = Intent(this, CameraActivity::class.java)
                startActivityForResult(camera, REQUEST_CAMERA)
            }
            R.id.action_transactions -> {
                startActivity(Intent(this, TransActivity::class.java))
            }
            R.id.action_help -> {

            }
        }
        return super.onOptionsItemSelected(item)
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