package at.htl.leonding.test02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import at.htl.leonding.test02.Model.CoronaAppReport
import at.htl.leonding.test02.Model.Report
import at.htl.leonding.test02.databinding.FragmentReportListBinding


class ReportList : Fragment() {
    private val coronaAppReport: CoronaAppReport by activityViewModels()
    private lateinit var binding: FragmentReportListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_report_list, container, false)

        binding.btReport.setOnClickListener { view ->
            view?.findNavController()?.navigate(R.id.action_reportList_to_welcome)
        }
        // Inflate the layout for this fragment

        coronaAppReport.ReportList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { report -> updateReportList(report) })
       return binding.root
    }


    fun updateReportList(entries:MutableList<Report>){
        val adapter: ArrayAdapter<Report>? = context?.let {
            ArrayAdapter<Report>(
                it,
                android.R.layout.simple_list_item_1, android.R.id.text1, entries
            )
        }
        binding.listView.adapter = adapter
    }


}