
package com.example.baitapmoi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.baitapmoi.databinding.ActivityChiTietCongViecBinding

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietCongViecBinding
    private var viTriCongViec = -1

    private val boChinhSua = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { ketQua ->
        if (ketQua.resultCode == Activity.RESULT_OK) {
            val congViec = layCongViec() ?: return@registerForActivityResult

            congViec.tenCongViec =
                ketQua.data?.getStringExtra("TEN_CONG_VIEC_MOI")
                    ?: congViec.tenCongViec

            congViec.moTa =
                ketQua.data?.getStringExtra("MO_TA_MOI")
                    ?: congViec.moTa

            capNhatGiaoDien()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viTriCongViec =
            intent.getIntExtra("VI_TRI_CONG_VIEC", -1)

        // Ho tro du lieu truyen truc tiep bang Intent tu Cau 2.
        if (viTriCongViec == -1) {
            val tenCongViec =
                intent.getStringExtra("TEN_CONG_VIEC") ?: ""

            val moTa =
                intent.getStringExtra("MO_TA") ?: ""

            if (tenCongViec.isBlank()) {
                Toast.makeText(
                    this,
                    "Khong tim thay cong viec",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
                return
            }

            DuLieuCongViec.danhSachCongViec.add(
                CongViec(tenCongViec, moTa)
            )

            viTriCongViec =
                DuLieuCongViec.danhSachCongViec.lastIndex
        }

        if (layCongViec() == null) {
            Toast.makeText(
                this,
                "Cong viec khong ton tai",
                Toast.LENGTH_SHORT
            ).show()
            finish()
            return
        }

        capNhatGiaoDien()

        binding.btnChinhSua.setOnClickListener {
            val congViec = layCongViec() ?: return@setOnClickListener

            val manHinhChinhSua = Intent(
                this,
                ChinhSuaCongViecActivity::class.java
            )

            manHinhChinhSua.putExtra(
                "TEN_CONG_VIEC",
                congViec.tenCongViec
            )

            manHinhChinhSua.putExtra(
                "MO_TA",
                congViec.moTa
            )

            boChinhSua.launch(manHinhChinhSua)
        }

        binding.btnHoanThanh.setOnClickListener {
            val congViec = layCongViec() ?: return@setOnClickListener

            congViec.trangThai = true
            capNhatGiaoDien()

            Toast.makeText(
                this,
                "Da hoan thanh cong viec",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun layCongViec(): CongViec? {
        return DuLieuCongViec.danhSachCongViec.getOrNull(
            viTriCongViec
        )
    }

    private fun capNhatGiaoDien() {
        val congViec = layCongViec() ?: return

        binding.tvMaCongViec.text =
            "Ma cong viec: ${congViec.maCongViec}"

        binding.tvTenCongViec.text =
            congViec.tenCongViec

        binding.tvMoTa.text =
            congViec.moTa

        if (congViec.laDaHoanThanh()) {
            binding.tvTrangThai.text =
                "Trang thai: Da hoan thanh"

            binding.btnHoanThanh.isEnabled = false
            binding.btnHoanThanh.text = "Da hoan thanh"
        } else {
            binding.tvTrangThai.text =
                "Trang thai: Chua hoan thanh"

            binding.btnHoanThanh.isEnabled = true
            binding.btnHoanThanh.text = "Hoan thanh"
        }
    }
}
