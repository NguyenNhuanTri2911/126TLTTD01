package com.example.baitapmoi

data class CongViec(
    var tenCongViec: String,
    var moTa: String,
    var trangThai: Boolean = false
) {
    fun layThongTin(): String {
        val trangThaiHienThi = if (trangThai) {
            "Da hoan thanh"
        } else {
            "Chua hoan thanh"
        }

        return "Ten cong viec: $tenCongViec\n" +
                "Mo ta: $moTa\n" +
                "Trang thai: $trangThaiHienThi"
    }
}