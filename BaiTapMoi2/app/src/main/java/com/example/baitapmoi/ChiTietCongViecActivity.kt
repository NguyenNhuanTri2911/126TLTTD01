import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baitapmoi.databinding.ActivityChiTietCongViecBinding

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietCongViecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChiTietCongViecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tenCongViec = intent.getStringExtra("TEN_CONG_VIEC") ?: ""
        val moTa = intent.getStringExtra("MO_TA") ?: ""

        binding.tvTenCongViec.text = "Ten cong viec: $tenCongViec"
        binding.tvMoTa.text = "Mo ta: $moTa"
    }
}