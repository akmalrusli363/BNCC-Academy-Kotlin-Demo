package com.tilikki.bnccapp.siagacovid.repository

import com.tilikki.bnccapp.siagacovid.lookup.netmodel.RegionSummaryData
import com.tilikki.bnccapp.siagacovid.overview.netmodel.OverviewRootData
import io.reactivex.Observable
import retrofit2.Response

interface CovidGovernmentRepository : CaseDataRepository {
    fun getCaseOverview(): Observable<Response<OverviewRootData>>
    fun getRegionCaseOverview(): Observable<Response<RegionSummaryData>>
}
