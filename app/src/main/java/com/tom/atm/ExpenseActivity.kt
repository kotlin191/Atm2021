package com.tom.atm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_expense.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class ExpenseActivity : AppCompatActivity() {
    companion object {
        val TAG = ExpenseActivity::class.java.simpleName
    }
    private lateinit var database: ExpenseDatabase
    val expenseData = arrayListOf<Expense>(
        Expense("2021/02/01", "Lunch", 120),
        Expense("2021/02/02", "停車費", 60),
        Expense("2021/02/05", "日用品", 215),
        Expense("2021/02/07", "Parking", 55),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)
        database = Room.databaseBuilder(this,
            ExpenseDatabase::class.java, "expense.db")
            .build()
        CoroutineScope(Dispatchers.IO).launch {
            val expenses = database.expenseDao().getAll()
            Log.d(TAG, expenses.size.toString())
        }
        fab.setOnClickListener {
            Executors.newSingleThreadExecutor().execute {
                database.expenseDao().add(expenseData[0])
            }
        }
    }
}