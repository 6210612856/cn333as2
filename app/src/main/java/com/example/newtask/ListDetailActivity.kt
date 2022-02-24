package com.example.newtask

import android.R.attr.defaultValue
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.newtask.databinding.ListDetailActivityBinding
import com.example.newtask.models.TaskList
import com.example.newtask.ui.detail.ListDetailFragment


class ListDetailActivity : AppCompatActivity() {
    lateinit var list: TaskList
    private lateinit var binding: ListDetailActivityBinding
    private lateinit var saveText: EditText
    private lateinit var showText: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        title = list.name

        saveText = findViewById<EditText>(R.id.saveText)
        showText = findViewById<TextView>(R.id.saveText)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }

        saveText.doAfterTextChanged {
            onSave()
        }

        val sharedPref = getPreferences(MODE_PRIVATE)
        val message = sharedPref.getString(list.name, "edit your text")
        showText.text = message
    }
    private fun onSave(){
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        val pref = getPreferences(Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putString(list.name, saveText.text.toString())
        editor.commit()
    }

}