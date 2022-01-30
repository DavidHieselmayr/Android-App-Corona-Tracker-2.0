package at.htl.leonding.test02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import at.htl.leonding.test02.Model.CoronaAppReport
import at.htl.leonding.test02.databinding.FragmentWelcomeBinding
import java.time.LocalDateTime

class Welcome : Fragment() {
    val coronaAppReport: CoronaAppReport by activityViewModels()
    val now: LocalDateTime = LocalDateTime.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentWelcomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        binding.btNewReport.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_welcome_to_newReport)
        }

        binding.btReportList.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_welcome_to_reportList)
        }


        var posAnzahl = 0
        var negAnzahl = 0

        var linzAnzahl = 0
        var leondingAnzahl = 0
        var haidAnzahl = 0

        coronaAppReport.ReportList.value?.forEach {
            if (it.isPositiv) {
                posAnzahl++
            } else {
                negAnzahl++
            }

            when (it.office) {
                "Linz-Stadtplatz" -> linzAnzahl++
                "Leonding-Meixnerkreuzung" -> leondingAnzahl++
                "Parkplatz-Haidcenter" -> haidAnzahl++
            }
        }

        binding.tvLinzAnzahl.text = linzAnzahl.toString()
        binding.tvLeondingAnzahl.text = leondingAnzahl.toString()
        binding.tvHaidAnzahl.text = haidAnzahl.toString()

        return binding.root
    }


}