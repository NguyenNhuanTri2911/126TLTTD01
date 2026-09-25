package com.example.studentprofilecard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentprofilecard.databinding.ActivityMainBinding
import com.example.studentprofilecard.extensions.toAcademicRanking
import com.example.studentprofilecard.model.Student
import com.example.studentprofilecard.extensions.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentStudent = Student(
        id = "2415141122122",
        name = "Nguyen Nhuan Tri",
        className = "SPKT",
        email = "nntri@gmail.com",
        gpa = 3.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayStudent()

        binding.btnUpdateGpa.setOnClickListener {
            updateGpa()
        }
    }

    private fun displayStudent() {
        with(binding) {
            tvName.text = currentStudent.name
            tvStudentId.text = "MSSV: ${currentStudent.id}"
            tvClassName.text = "Lop: ${currentStudent.className}"
            tvEmail.text = "Email: ${currentStudent.email}"
            tvGpa.text =
                "GPA: ${currentStudent.gpa} - ${currentStudent.gpa.toAcademicRanking()}"
        }
    }

    private fun updateGpa() {
        val newGpa = binding.edtNewGpa.text.toString().toDoubleOrNull()

        newGpa?.let { gpa ->
            if (gpa in 0.0..4.0) {
                currentStudent = currentStudent.copy(gpa = gpa)
                displayStudent()
                toast("Cập nhật GPA thành công!")
            } else {
                toast("GPA phải từ 0.0 đến 4.0!")
            }
        } ?: run {
            toast("Vui lòng nhập GPA hợp lệ!")
        }
    }
}