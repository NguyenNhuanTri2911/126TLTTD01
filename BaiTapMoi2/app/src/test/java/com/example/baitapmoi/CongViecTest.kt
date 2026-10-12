package com.example.baitapmoi

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
class CongViecTest {


    @Test
    fun taoCongViec_ThongTinDung() {
        val congViec = CongViec(
            maCongViec = "CV001",
            tenCongViec = "Hoc Kotlin",
            moTa = "On tap Android"
        )

        assertEquals("CV001", congViec.maCongViec)
        assertEquals("Hoc Kotlin", congViec.tenCongViec)
        assertEquals("On tap Android", congViec.moTa)
        assertFalse(congViec.trangThai)
    }

    @Test
    fun laDaHoanThanh_KhiChuaHoanThanh_TraVeFalse() {
        val congViec = CongViec(
            maCongViec = "CV002",
            tenCongViec = "Lam bai tap",
            moTa = "Bai tap Android",
            trangThai = false
        )

        assertFalse(congViec.laDaHoanThanh())
    }

    @Test
    fun laDaHoanThanh_KhiDaHoanThanh_TraVeTrue() {
        val congViec = CongViec(
            maCongViec = "CV003",
            tenCongViec = "Nop bai",
            moTa = "Nop bai tap Android",
            trangThai = true
        )

        assertTrue(congViec.laDaHoanThanh())
    }

    @Test
    fun layThongTin_TraVeDayDuThongTin() {
        val congViec = CongViec(
            maCongViec = "CV004",
            tenCongViec = "Hoc Android",
            moTa = "Hoc Intent",
            trangThai = true
        )

        val thongTin = congViec.layThongTin()

        assertTrue(thongTin.contains("CV004"))
        assertTrue(thongTin.contains("Hoc Android"))
        assertTrue(thongTin.contains("Hoc Intent"))
        assertTrue(thongTin.contains("Da hoan thanh"))
    }
}