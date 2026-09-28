package com.example.studentinfo_2415141122122.extensions

fun Double.formatScore(): String {
    return String.format("%.1f", this)
}