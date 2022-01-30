package at.htl.leonding.test02.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoronaAppReport : ViewModel() {
    private val _ReportList: MutableLiveData<MutableList<Report>> =
        MutableLiveData<MutableList<Report>>()

    val ReportList: LiveData<MutableList<Report>>
        get() = _ReportList


    fun addReportToList(report: Report) {
        val value = this._ReportList.value ?: arrayListOf()
        value.add(report)
        this._ReportList.value = value
    }
}