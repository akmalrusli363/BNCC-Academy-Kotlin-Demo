package com.tilikki.bnccapp.siagacovid.hotline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tilikki.bnccapp.R
import com.tilikki.bnccapp.siagacovid.hotline.mvvm.HotlineModel
import com.tilikki.bnccapp.siagacovid.hotline.mvvm.HotlineViewModel
import com.tilikki.bnccapp.siagacovid.utils.AppEventLogging
import kotlinx.android.synthetic.main.activity_hotline.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class HotlineActivity : AppCompatActivity() {

    private val viewModel: HotlineViewModel = HotlineViewModel(this@HotlineActivity)

    private val mockHotlineList = mutableListOf(
        HotlineData("@drawable/ic_wait", "Loading...", "???")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotline)

        val hotlineAdapter = HotlineAdapter(mockHotlineList)
        rvHotline.layoutManager = LinearLayoutManager(this)
        rvHotline.adapter = hotlineAdapter

        viewModel.fetchData(hotlineAdapter)

        ivReturnIcon.setOnClickListener {
            finish()
        }
    }

}
