import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.baitapmoi.databinding.ActivityChiTietCongViecBinding

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietCongViecBinding

    private var tenCongViec = ""
    private var moTa = ""

    private val chinhSuaLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { ketQua ->
            if (ketQua.resultCode == RESULT_OK) {
                val duLieu = ketQua.data

                tenCongViec =
                    duLieu?.getStringExtra("TEN_CONG_VIEC_MOI") ?: tenCongViec

                moTa =
                    duLieu?.getStringExtra("MO_TA_MOI") ?: moTa

                capNhatGiaoDien()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tenCongViec = intent.getStringExtra("TEN_CONG_VIEC") ?: ""
        moTa = intent.getStringExtra("MO_TA") ?: ""

        capNhatGiaoDien()

        binding.btnChinhSua.setOnClickListener {
            val intent = Intent(this, ChinhSuaCongViecActivity::class.java)
            intent.putExtra("TEN_CONG_VIEC", tenCongViec)
            intent.putExtra("MO_TA", moTa)

            chinhSuaLauncher.launch(intent)
        }
    }

    private fun capNhatGiaoDien() {
        binding.tvTenCongViec.text = "Ten cong viec: $tenCongViec"
        binding.tvMoTa.text = "Mo ta: $moTa"
    }
}