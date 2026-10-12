package com.example.baitapmoi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.baitapmoi.databinding.ActivityChiTietCongViecBinding

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietCongViecBinding

    private var viTriCongViec: Int = -1

    private val chinhSuaLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { ketQua ->
        if (ketQua.resultCode == Activity.RESULT_OK) {
            val congViec = layCongViec()

            if (congViec != null) {
                congViec.tenCongViec =
                    ketQua.data?.getStringExtra("TEN_CONG_VIEC_MOI")
                        ?: congViec.tenCongViec

                congViec.moTa =
                    ketQua.data?.getStringExtra("MO_TA_MOI")
                        ?: congViec.moTa

                capNhatGiaoDien()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viTriCongViec =
            intent.getIntExtra("VI_TRI_CONG_VIEC", -1)

        // Ho tro du lieu truyen truc tiep bang Intent o Cau 2.
        if (viTriCongViec == -1) {
            val tenCongViec =
                intent.getStringExtra("TEN_CONG_VIEC") ?: ""

            val moTa =
                intent.getStringExtra("MO_TA") ?: ""

            if (tenCongViec.isBlank()) {
                finish()
                return
            }

            DuLieuCongViec.danhSachCongViec.add(
                CongViec(
                    tenCongViec = tenCongViec,
                    moTa = moTa
                )
            )

            viTriCongViec =
                DuLieuCongViec.danhSachCongViec.lastIndex
        }

        if (layCongViec() == null) {
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

            chinhSuaLauncher.launch(manHinhChinhSua)
        }
    }

    private fun layCongViec(): CongViec? {
        return DuLieuCongViec.danhSachCongViec.getOrNull(
            viTriCongViec
        )
    }

    private fun capNhatGiaoDien() {
        val congViec = layCongViec() ?: return

        binding.tvTenCongViec.text =
            "Ten cong viec: ${congViec.tenCongViec}"

        binding.tvMoTa.text =
            "Mo ta: ${congViec.moTa}"
    }
}
