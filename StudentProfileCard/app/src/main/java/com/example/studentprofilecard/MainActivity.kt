package com.example.studentprofilecard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentprofilecard.databinding.ActivityMainBinding
import com.example.studentprofilecard.extensions.toAcademicRanking
import com.example.studentprofilecard.model.Student
import com.example.studentprofilecard.extensions.toast
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentStudent = Student(
        id = "2415141122122",
        name = "Nguyen Nhuan Tri",
        className = "SPKT",
        email = "nntri@gmail.com",
        phone = "0123456789",
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
        binding.btnCall.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:${currentStudent.phone}")
            )
            startActivity(intent)
        }
        binding.btnDeleteProfile.setOnClickListener {
            showDeleteDialog()
        }
    }

    private fun displayStudent() {
        with(binding) {
            tvName.text = currentStudent.name
            tvStudentId.text = "MSSV: ${currentStudent.id}"
            tvClassName.text = "Lop: ${currentStudent.className}"
            tvEmail.text = "Email: ${currentStudent.email}"
            tvPhone.text = "Phone: ${currentStudent.phone}"
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
    private fun showDeleteDialog() {
        AlertDialog.Builder(this)
            .setTitle("Xoa ho so")
            .setMessage("Ban co chac muon xoa ho so sinh vien?")
            .setPositiveButton("Xoa") { _, _ ->
                toast("Da xoa ho so!")
            }
            .setNegativeButton("Huy", null)
            .show()
    }
}