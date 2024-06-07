package com.example.beproductive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val eventLiveData = MutableLiveData<List<Event>>()
    private val events = mutableListOf<Event>()

    private val notesLiveData = MutableLiveData<List<String>>()
    private val notes = mutableListOf<String>()

    fun setEvents(events: List<Event>) {
        this.events.clear()
        this.events.addAll(events)
        eventLiveData.value = this.events
    }

    fun getEvents(): LiveData<List<Event>> = eventLiveData

    fun setEvent(taskName: String, startTime: String, endTime: String, description: String, daily: Boolean) {
        val newEvent = Event(taskName, startTime, endTime, description, daily)
        events.add(newEvent)
        eventLiveData.value = events
    }

    fun setNotes(note1: String, note2: String, note3: String) {
        notes.clear()
        if (note1.isNotEmpty()) notes.add(note1)
        if (note2.isNotEmpty()) notes.add(note2)
        if (note3.isNotEmpty()) notes.add(note3)
        notesLiveData.value = notes
    }

    fun getNotes(): LiveData<List<String>> = notesLiveData

    data class Event(
        val taskName: String,
        val startTime: String,
        val endTime: String,
        val description: String,
        val daily: Boolean
    )
}
