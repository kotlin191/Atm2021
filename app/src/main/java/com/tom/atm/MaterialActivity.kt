package com.tom.atm

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_row.view.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        //data
        val contacts = listOf<Contact>(
                Contact("Hank", "6661234"),
                Contact("Jack", "99838882"),
                Contact("Jenny", "98881234"),
                Contact("Eric", "77366363"))
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = object : RecyclerView.Adapter<ContactViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                    : ContactViewHolder {
                val view = layoutInflater.inflate(R.layout.contact_row, parent, false)
                return ContactViewHolder(view)
            }

            override fun getItemCount(): Int {
                return contacts.size
            }

            override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
                holder.name.setText(contacts.get(position).name)
                holder.phone.setText(contacts.get(position).phone)
            }
        }
        recycler.adapter = adapter
    }

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.contact_name
        val phone = view.contact_phone
    }
}