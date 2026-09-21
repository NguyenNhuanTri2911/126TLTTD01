
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
            text = "Thông tin sinh viên sẽ hiển thị ở đây"
            textSize = 18f
        }

        binding.btnHienThi.setOnClickListener {

            val maSV = binding.edtMaSV.text.toString().trim()

            val ten = binding.edtTen.text.toString()
                .capitalizeFirst()

            val nganh = binding.edtNganh.text.toString()
                .trim()

            if (maSV.isNotEmpty() &&
                ten.isNotEmpty() &&
                nganh.isNotEmpty()
            ) {
                binding.txtKetQua.text = """
                    Thông tin sinh viên
                    
                    Mã sinh viên: $maSV
                    Họ tên: $ten
                    Ngành học: $nganh
                    
                    Xin chào $ten!
                """.trimIndent()
            } else {
                binding.txtKetQua.text =
                    "Vui lòng nhập đầy đủ thông tin sinh viên!"
            }
        }
    }
}