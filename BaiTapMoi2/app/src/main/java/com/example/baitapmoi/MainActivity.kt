
package com.example.baitapmoi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.baitapmoi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTaoCongViec.setOnClickListener {
            taoCongViec()
        }

        binding.btnXemDanhSach.setOnClickListener {
            xemDanhSachCongViec()
        }
    }

    private fun taoCongViec() {
        val tenCongViec =
            binding.edtTenCongViec.text.toString().trim()

        val moTa =
            binding.edtMoTa.text.toString().trim()

        if (tenCongViec.isBlank()) {
            binding.edtTenCongViec.error =
                "Vui long nhap ten cong viec"
            binding.edtTenCongViec.requestFocus()
            return
        }

        val congViec = CongViec(
            tenCongViec = tenCongViec,
            moTa = moTa
        )

        DuLieuCongViec.danhSachCongViec.add(congViec)

        val viTriCongViec =
            DuLieuCongViec.danhSachCongViec.lastIndex

        val manHinhChiTiet = Intent(
            this,
            ChiTietCongViecActivity::class.java
        )

        manHinhChiTiet.putExtra(
            "VI_TRI_CONG_VIEC",
            viTriCongViec
        )

        binding.edtTenCongViec.text.clear()
        binding.edtMoTa.text.clear()

        Toast.makeText(
            this,
            "Tao cong viec thanh cong",
            Toast.LENGTH_SHORT
        ).show()

        startActivity(manHinhChiTiet)
    }

    private fun xemDanhSachCongViec() {
        val danhSach =
            DuLieuCongViec.danhSachCongViec

        if (danhSach.isEmpty()) {
            Toast.makeText(
                this,
                "Chua co cong viec nao",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val tenDanhSach = danhSach.mapIndexed {
                viTri, congViec ->
            "${viTri + 1}. ${congViec.tenCongViec}"
        }.toTypedArray()

        AlertDialog.Builder(this)
            .setTitle("Danh sach cong viec")
            .setItems(tenDanhSach) { _, viTri ->
                val manHinhChiTiet = Intent(
                    this,
                    ChiTietCongViecActivity::class.java
                )

                manHinhChiTiet.putExtra(
                    "VI_TRI_CONG_VIEC",
                    viTri
                )

                startActivity(manHinhChiTiet)
            }
            .setNegativeButton("Dong", null)
            .show()
    }
}
