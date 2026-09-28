package com.example.studentinfo_2415141122122.extensions

import com.example.studentinfo_2415141122122.model.Student

fun Double.formatScore(): String {
    return "★ %.1f điểm ★".format(this)
}

fun Student.toDisplayInfo(): String {
    return """
        MSSV: $id
        Họ tên: ${name.uppercase()}
        Lớp: $className
        Tuổi: $age
        Điểm: ${score.formatScore()}
    """.trimIndent()
}