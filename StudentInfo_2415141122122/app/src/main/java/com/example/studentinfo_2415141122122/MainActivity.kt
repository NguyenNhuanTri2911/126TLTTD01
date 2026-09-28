package com.example.studentinfo_2415141122122

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentinfo_2415141122122.databinding.ActivityMainBinding
import com.example.studentinfo_2415141122122.model.Student
import com.example.studentinfo_2415141122122.extensions.formatScore
import com.example.studentinfo_2415141122122.extensions.toDisplayInfo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val student = Student(
        id = "2415141122122",
        name = "Nguyen Nhuan Tri",
        className = "24SK1",
        age = 20,
        score = 3.2,
        status = "Dang hoc",
        phone = "0123456789"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayStudent()
    }

    private fun displayStudent() {
        with(binding) {
            tvStudentId.text = "MSSV: ${student.id}"
            tvName.text = "Ho ten: ${student.name}"
            tvClass.text = "Lop: ${student.className}"
            tvAge.text = "Tuoi: ${student.age}"
            tvScore.text = "Diem: ${student.score.formatScore()}"
            tvStatus.text = "Trang thai: ${student.status}"
            tvPhone.text = "So dien thoai: ${student.phone}"

            tvStudentInfo.text = student.toDisplayInfo()
        }
    }
}