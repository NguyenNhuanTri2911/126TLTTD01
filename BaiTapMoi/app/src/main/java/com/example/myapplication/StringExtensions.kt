
package com.example.myapplication

fun String.capitalizeFirst(): String {
    return this.trim().replaceFirstChar { it.uppercase() }
}