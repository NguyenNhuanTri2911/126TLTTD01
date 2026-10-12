package com.example.baitapmoi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baitapmoi.databinding.ActivityMainBinding
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTaoCongViec.setOnClickListener {
            val tenCongViec = binding.edtTenCongViec.text.toString()
            val moTa = binding.edtMoTa.text.toString()

            val intent = Intent(this, ChiTietCongViecActivity::class.java)
            intent.putExtra("TEN_CONG_VIEC", tenCongViec)
            intent.putExtra("MO_TA", moTa)

            startActivity(intent)
        }

        binding.btnXemDanhSach.setOnClickListener {
            Toast.makeText(
                this,
                "Danh sach cong viec se duoc bo sung",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}