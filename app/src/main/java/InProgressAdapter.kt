import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beproductive.SharedViewModel.Event
import com.example.beproductive.R

class InProgressAdapter(private val events: List<Event>) :
    RecyclerView.Adapter<InProgressAdapter.InProgressViewHolder>() {

    inner class InProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskNameTextView: TextView = itemView.findViewById(R.id.taskNameTextView)
        val taskDateTextView: TextView = itemView.findViewById(R.id.taskDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InProgressViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_in_progress, parent, false)
        return InProgressViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InProgressViewHolder, position: Int) {
        val currentItem = events[position]
        holder.taskNameTextView.text = currentItem.taskName
        holder.taskDateTextView.text = currentItem.startTime // Change this to your event date field
    }

    override fun getItemCount() = events.size
}
