package com.example.beproductive

import ToDoAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val taskSummaryTextView: TextView = view.findViewById(R.id.taskSummaryTextView)
        val tasksTodayTextView: TextView = view.findViewById(R.id.tasksTodayTextView)
        val progressTextView: TextView = view.findViewById(R.id.progressTextView)
        val toDoRecyclerView: RecyclerView = view.findViewById(R.id.toDoRecyclerView)

        val smallTaskNote1: EditText = view.findViewById(R.id.smallTaskNote1)
        val smallTaskNote2: EditText = view.findViewById(R.id.smallTaskNote2)
        val smallTaskNote3: EditText = view.findViewById(R.id.smallTaskNote3)
        val saveNotesButton: Button = view.findViewById(R.id.saveNotesButton)

        toDoRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        sharedViewModel.getEvents().observe(viewLifecycleOwner) { events ->
            val toDoEvents = events.filter { !it.daily }
            val inProgressEvents = events.filter { it.daily }

            taskSummaryTextView.text = "Task Summary"
            tasksTodayTextView.text = "Today"
            progressTextView.text = "${toDoEvents.size} / ${events.size} Task"

            toDoRecyclerView.adapter = ToDoAdapter(toDoEvents + inProgressEvents)
        }

        saveNotesButton.setOnClickListener {
            saveNotes(smallTaskNote1.text.toString(), smallTaskNote2.text.toString(), smallTaskNote3.text.toString())
        }

        loadNotes(smallTaskNote1, smallTaskNote2, smallTaskNote3)

        return view
    }

    private fun saveNotes(note1: String, note2: String, note3: String) {
        val sharedPreferences = requireActivity().getSharedPreferences("smallTasks", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("note1", note1)
            putString("note2", note2)
            putString("note3", note3)
            apply()
        }
        Toast.makeText(requireContext(), "Notes saved successfully", Toast.LENGTH_SHORT).show()
    }

    private fun loadNotes(note1: EditText, note2: EditText, note3: EditText) {
        val sharedPreferences = requireActivity().getSharedPreferences("smallTasks", Context.MODE_PRIVATE)
        note1.setText(sharedPreferences.getString("note1", ""))
        note2.setText(sharedPreferences.getString("note2", ""))
        note3.setText(sharedPreferences.getString("note3", ""))
    }
}
