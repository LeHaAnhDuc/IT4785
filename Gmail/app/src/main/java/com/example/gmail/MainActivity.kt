package com.example.gmail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

data class EmailItem(val senderName: String, val messagePreview: String, val time: String)

// EmailAdapter.kt - Adapter for RecyclerView
class EmailAdapter(private val emails: List<EmailItem>) :
    RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    class EmailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val senderName: TextView = view.findViewById(R.id.senderName)
        val messagePreview: TextView = view.findViewById(R.id.messagePreview)
        val time: TextView = view.findViewById(R.id.time)
        val textViewIcon: TextView = view.findViewById(R.id.senderNameIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emails[position]
        holder.senderName.text = email.senderName
        holder.messagePreview.text = email.messagePreview
        holder.time.text = email.time
        holder.textViewIcon.text = email.senderName.substring(0, 1)
    }

    override fun getItemCount() = emails.size
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val emailList = listOf(
                EmailItem("Edurila.com", "$19 Only (First 10 spots) - Bestselling course!", "12:34 PM"),
        EmailItem("Chris Abad", "Help make Campaign Monitor better", "11:22 AM"),
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EmailAdapter(emailList)
    }
}