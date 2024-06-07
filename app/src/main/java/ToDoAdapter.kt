import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beproductive.SharedViewModel.Event
import com.example.beproductive.R

class ToDoAdapter(private val events: List<Event>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskNameTextView: TextView = itemView.findViewById(R.id.taskNameTextView)
        val taskDateTextView: TextView = itemView.findViewById(R.id.taskDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ToDoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentItem = events[position]
        holder.taskNameTextView.text = currentItem.taskName
        holder.taskDateTextView.text = currentItem.startTime // Change this to your event date field
    }

    override fun getItemCount() = events.size
}
