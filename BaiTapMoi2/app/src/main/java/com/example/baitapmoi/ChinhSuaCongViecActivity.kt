import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baitapmoi.databinding.ActivityChinhSuaCongViecBinding

class ChinhSuaCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChinhSuaCongViecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChinhSuaCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tenCongViec = intent.getStringExtra("TEN_CONG_VIEC") ?: ""
        val moTa = intent.getStringExtra("MO_TA") ?: ""

        binding.edtTenCongViec.setText(tenCongViec)
        binding.edtMoTa.setText(moTa)

        binding.btnLuu.setOnClickListener {
            val tenMoi = binding.edtTenCongViec.text.toString()
            val moTaMoi = binding.edtMoTa.text.toString()

            val ketQua = Intent()
            ketQua.putExtra("TEN_CONG_VIEC_MOI", tenMoi)
            ketQua.putExtra("MO_TA_MOI", moTaMoi)

            setResult(RESULT_OK, ketQua)
            finish()
        }
    }
}