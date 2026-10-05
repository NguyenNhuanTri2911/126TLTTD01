package com.example.implicitintent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.implicitintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var studentName = "Nguyễn Nhuận Trị"
    private var studentEmail = "nntri@gmail.com"
    private var studentClass = "classname"

    // Chọn ảnh đại diện
    private val pickImageLauncher =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->

            uri?.let {
                binding.imgAvatar.setImageURI(it)
            }
        }

    // Nhận dữ liệu từ EditActivity
    private val editActivityLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK) {

                val data = result.data

                val newName = data?.getStringExtra("NEW_NAME")
                val newEmail = data?.getStringExtra("NEW_EMAIL")
                val newClass = data?.getStringExtra("NEW_CLASS")

                if (!newName.isNullOrEmpty()) {
                    studentName = newName
                }

                if (!newEmail.isNullOrEmpty()) {
                    studentEmail = newEmail
                }

                if (!newClass.isNullOrEmpty()) {
                    studentClass = newClass
                }

                updateStudentInfo()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateStudentInfo()

        // Nút chọn ảnh
        binding.btnAvatar.setOnClickListener {

            pickImageLauncher.launch("image/*")
        }

        // Nút chỉnh sửa
        binding.btnEdit.setOnClickListener {

            val intent = Intent(this, EditActivity::class.java)

            intent.putExtra("CURRENT_NAME", studentName)
            intent.putExtra("CURRENT_EMAIL", studentEmail)
            intent.putExtra("CURRENT_CLASS", studentClass)

            editActivityLauncher.launch(intent)
        }
    }

    private fun updateStudentInfo() {

        binding.tvName.text = studentName
        binding.tvEmail.text = "Email: $studentEmail"
        binding.tvClass.text = "Lớp: $studentClass"
        binding.tvStudentId.text = "MSSV: 2415141122122"
    }
}