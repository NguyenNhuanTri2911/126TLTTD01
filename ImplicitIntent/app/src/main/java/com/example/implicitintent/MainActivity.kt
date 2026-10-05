package com.example.implicitintent

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.implicitintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Tên hiện tại
    private var currentName = "Chưa có thông tin"

    // Nhận kết quả trả về từ EditActivity
    private val editActivityLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK) {

                val newName = result.data?.getStringExtra("NEW_NAME")

                if (!newName.isNullOrEmpty()) {
                    currentName = newName
                    binding.tvName.text = currentName
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hiển thị tên hiện tại
        binding.tvName.text = currentName

        // Mở EditActivity
        binding.btnEdit.setOnClickListener {

            val intent = Intent(this, EditActivity::class.java)

            // Gửi tên hiện tại sang EditActivity
            intent.putExtra("CURRENT_NAME", currentName)

            editActivityLauncher.launch(intent)
        }
    }
}