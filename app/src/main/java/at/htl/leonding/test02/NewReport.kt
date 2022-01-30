package at.htl.leonding.test02

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import at.htl.leonding.test02.Model.CoronaAppReport
import at.htl.leonding.test02.Model.Report
import at.htl.leonding.test02.databinding.FragmentNewReportBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class NewReport : Fragment() {
    val coronaAppReport: CoronaAppReport by activityViewModels()
    private lateinit var binding: FragmentNewReportBinding
    var datePicker: DatePickerDialog? = null
    var date: LocalDate = LocalDate.now()
    var timePicker: TimePickerDialog? = null
    var time: LocalTime = LocalTime.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_report, container, false)

        binding.btSave.setOnClickListener { view -> validate(view) }
        binding.etDate.inputType = 0
        binding.etDate.setOnClickListener { view ->
            showDatePickerDialog(view)
        }

        binding.etTime.inputType = 0
        binding.etTime.setOnClickListener { view -> showTimePickerDialog(view) }
        return binding.root
    }

    private fun showDatePickerDialog(v: View) {
        val cldr: Calendar = Calendar.getInstance()
        val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
        val month: Int = cldr.get(Calendar.MONTH)
        val year: Int = cldr.get(Calendar.YEAR)

        datePicker =
            context?.let {
                DatePickerDialog(
                    it,
                    { _, year, monthOfYear, dayOfMonth ->
                        run {
                            binding.etDate.setText(
                                dayOfMonth.toString() + "/" + (monthOfYear + 1) +
                                        "/" + year
                            )
                            date = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
                        }
                    },
                    year,
                    month,
                    day
                )
            }
        datePicker!!.show()
    }

    private fun showTimePickerDialog(v: View) {
        val cldr = Calendar.getInstance()
        val hour = cldr[Calendar.HOUR_OF_DAY]
        val minutes = cldr[Calendar.MINUTE]
        timePicker = TimePickerDialog(
            context, { _, sHour, sMinute ->
                run {
                    binding.etTime.setText("$sHour:$sMinute")
                    time = LocalTime.of(sHour, sMinute)
                }
            },
            hour,
            minutes,
            true
        )
        timePicker!!.show()
    }

    fun validate(v: View) {
        var report = Report(
            binding.etID.text.toString(),
            LocalDateTime.of(date, time),
            binding.switchPositiv.isChecked,
            binding.spinner.selectedItem.toString()
        )

        coronaAppReport.addReportToList(report)
        println(coronaAppReport)
        view?.findNavController()?.navigate(R.id.action_newReport_to_reportList)
    }
}


