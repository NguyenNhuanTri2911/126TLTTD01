
package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.txtKetQua.apply {
            text = "Kết quả"
            textSize = 20f
        }

        binding.btnHienThi.setOnClickListener {

            binding.edtTen.text.toString().trim().let { ten ->

                if (ten.isNotEmpty()) {
                    binding.txtKetQua.text = "Xin chào $ten"
                } else {
                    binding.txtKetQua.text = "Bạn chưa nhập tên!"
                }
            }
        }
    }
}