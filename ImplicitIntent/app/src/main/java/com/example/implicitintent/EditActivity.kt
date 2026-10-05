package com.example.implicitintent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.implicitintent.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận tên hiện tại từ MainActivity
        val currentName = intent.getStringExtra("CURRENT_NAME") ?: ""

        // Điền tên hiện tại vào EditText
        binding.edtName.setText(currentName)

        // Lưu và quay lại MainActivity
        binding.btnSave.setOnClickListener {

            val newName = binding.edtName.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("NEW_NAME", newName)

            setResult(RESULT_OK, resultIntent)

            finish()
        }
    }
}