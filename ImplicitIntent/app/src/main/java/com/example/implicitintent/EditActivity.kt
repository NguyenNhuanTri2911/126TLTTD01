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

        // Nhận dữ liệu từ MainActivity
        val currentName =
            intent.getStringExtra("CURRENT_NAME") ?: ""

        val currentEmail =
            intent.getStringExtra("CURRENT_EMAIL") ?: ""

        val currentClass =
            intent.getStringExtra("CURRENT_CLASS") ?: ""

        // Hiển thị dữ liệu hiện tại
        binding.edtName.setText(currentName)
        binding.edtEmail.setText(currentEmail)
        binding.edtClass.setText(currentClass)

        binding.btnSave.setOnClickListener {

            val newName =
                binding.edtName.text.toString()

            val newEmail =
                binding.edtEmail.text.toString()

            val newClass =
                binding.edtClass.text.toString()

            // Tạo Intent chứa kết quả
            val resultIntent = Intent()

            resultIntent.putExtra("NEW_NAME", newName)
            resultIntent.putExtra("NEW_EMAIL", newEmail)
            resultIntent.putExtra("NEW_CLASS", newClass)

            // Trả dữ liệu về MainActivity
            setResult(RESULT_OK, resultIntent)

            // Đóng EditActivity
            finish()
        }
    }
}