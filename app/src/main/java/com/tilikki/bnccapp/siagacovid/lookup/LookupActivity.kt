package com.tilikki.bnccapp.siagacovid.lookup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.tilikki.bnccapp.R
import com.tilikki.bnccapp.siagacovid.lookup.mvvm.LookupViewModel
import kotlinx.android.synthetic.main.activity_lookup.*

class LookupActivity : AppCompatActivity() {

    private val viewModel: LookupViewModel = LookupViewModel(this@LookupActivity)

    private var mockLookupList: MutableList<LookupData> = mutableListOf(
        LookupData("Loading...", 0, 0, 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lookup)

        val lookupAdapter = LookupAdapter(mockLookupList)
        rvLookUp.layoutManager = LinearLayoutManager(this)
        rvLookUp.adapter = lookupAdapter

        fetchData(lookupAdapter)

        ivReturnIcon.setOnClickListener {
            finish()
        }

        ibClearSearch.setOnClickListener {
            etRegionLookupSearch.text.clear()
        }

        etRegionLookupSearch.addTextChangedListener {
            lookupAdapter.filter.filter(etRegionLookupSearch.text)
        }

        srlLookupData.setOnRefreshListener {
            fetchData(lookupAdapter)
        }
    }

    private fun fetchData(lookupAdapter: LookupAdapter) {
        viewModel.fetchData(lookupAdapter)
    }

}