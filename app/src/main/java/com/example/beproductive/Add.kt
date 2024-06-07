package com.example.beproductive

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.FragmentManager

class Add : Fragment() {
    private lateinit var taskNameEditText: EditText
    private lateinit var startTimeEditText: EditText
    private lateinit var endTimeEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var addButton: Button

    private lateinit var date1: TextView
    private lateinit var date2: TextView
    private lateinit var date3: TextView
    private lateinit var date4: TextView

    private var selectedDate: String = "June 05 WED"

    private val sharedViewModel: SharedViewModel by activityViewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        taskNameEditText = view.findViewById(R.id.taskName)
        startTimeEditText = view.findViewById(R.id.startTime)
        endTimeEditText = view.findViewById(R.id.endTime)
        descriptionEditText = view.findViewById(R.id.descText)
        radioGroup = view.findViewById(R.id.radioGroup)
        addButton = view.findViewById(R.id.addButton)

        date1 = view.findViewById(R.id.date1)
        date2 = view.findViewById(R.id.date2)
        date3 = view.findViewById(R.id.date3)
        date4 = view.findViewById(R.id.date4)

        date1.setOnClickListener { selectDate(date1) }
        date2.setOnClickListener { selectDate(date2) }
        date3.setOnClickListener { selectDate(date3) }
        date4.setOnClickListener { selectDate(date4) }

        addButton.setOnClickListener {
            val taskName = taskNameEditText.text.toString()
            val startTime = startTimeEditText.text.toString()
            val endTime = endTimeEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val isPersonal = radioGroup.checkedRadioButtonId == R.id.personal

            if (taskName.isNotEmpty() && startTime.isNotEmpty() && endTime.isNotEmpty() && description.isNotEmpty()) {
                sharedViewModel.setEvent(taskName, startTime, endTime, description, isPersonal)
                Toast.makeText(context, "Task saved successfully!", Toast.LENGTH_SHORT).show()
                navigateToHome()
            } else {
                Toast.makeText(context, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun selectDate(selectedTextView: TextView) {
        val dateTextViews = listOf(date1, date2, date3, date4)
        dateTextViews.forEach { textView ->
            if (textView == selectedTextView) {
                textView.setBackgroundResource(R.drawable.calendar_date_background)
                textView.setTextColor(resources.getColor(R.color.white))
                selectedDate = textView.text.toString()
            } else {
                textView.setBackgroundResource(R.drawable.calendar_date_background_default)
                textView.setTextColor(resources.getColor(R.color.black))
            }
        }
    }

    private fun navigateToHome() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStackImmediate()
    }
}