
package com.example.baitapmoi

data class CongViec(
    var tenCongViec: String,
    var moTa: String,
    var trangThai: Boolean = false,
    var maCongViec: String = ""
) {
    fun layThongTin(): String {
        val trangThaiHienThi = if (laDaHoanThanh()) {
            "Da hoan thanh"
        } else {
            "Chua hoan thanh"
        }

        return "Ma cong viec: $maCongViec\n" +
                "Ten cong viec: $tenCongViec\n" +
                "Mo ta: $moTa\n" +
                "Trang thai: $trangThaiHienThi"
    }

    fun laDaHoanThanh(): Boolean {
        return trangThai
    }
}
