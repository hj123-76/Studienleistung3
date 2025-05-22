package com.example.shoppinglist

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {


    // Zugriff  auf die Elemente unseres Layouts
    private lateinit var binding: ActivityMainBinding
    private lateinit var lvTodoList: ListView
    private lateinit var AddButton: FloatingActionButton
    private lateinit var shoppingItems: ArrayList<String>
    // Bindeglied zwischen Liste und visueller Liste
    private lateinit var itemAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Elemente werden durch id verknüpft
        lvTodoList = findViewById(R.id.lvTodoList)
        AddButton = findViewById(R.id.AddButton)
        shoppingItems = ArrayList()



        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, shoppingItems)
        lvTodoList.adapter  = itemAdapter
        // Löschfunktion mit langem Draufklicken wird implementiert
        lvTodoList.setOnItemLongClickListener(OnItemLongClickListener { arg0, arg1, pos, id ->
         shoppingItems.removeAt(pos)
            itemAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext, "Element gelöscht", Toast.LENGTH_SHORT).show()
            true
        })

        // Buttonelement-Funktion und Editor-Textfeld werden hinzufügt/implementiert
        AddButton.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Hinzufügen")

            var input = EditText(this)
            input.hint = "Text eingeben"
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder. setPositiveButton("OK") { dialog, which ->
                shoppingItems.add(input.text.toString())
            }

            builder.setNegativeButton("Abbrechen") { dialog, which ->
                Toast.makeText(applicationContext, "Abgebrochen", Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }

    }

}
