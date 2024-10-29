package com.example.bai3
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Student(
    val fullName: String,
    val mssv: String
)
class StudentAdapter(private var students: List<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullNameTextView: TextView = itemView.findViewById(R.id.studentName)
        val mssvTextView: TextView = itemView.findViewById(R.id.MSSV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.fullNameTextView.text = student.fullName
        holder.mssvTextView.text = student.mssv
    }

    override fun getItemCount(): Int = students.size

    fun updateList(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }
}
class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var searchEditText: EditText
    private val studentList = listOf(
        Student("Nguyễn Văn Chính", "20215333"),
        Student("Trần Thị Quỳnh Mai", "20215837"),
        Student("Trần Đình Dũng", "20238933"),
        Student("Nguyễn Văn Hoàng", "20192892"),
        Student("Mai Thị Quỳnh Hương", "20202822"),
        Student("Trần Đình Dũng", "20208288"),
        Student("Nguyễn Tấn Đạt", "20215334"),
        Student("Lê Hồng Phong", "20215857"),
        Student("Lê Trọng", "20238633")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        searchEditText = findViewById(R.id.searchBox)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter


        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                if (s.toString().isEmpty()){
                    studentAdapter.updateList(studentList)
                }
                else {
                    if (query.length > 2) {
                        val filteredList = studentList.filter {
                            it.fullName.contains(
                                query,
                                ignoreCase = true
                            ) || it.mssv.contains(query)
                        }
                        studentAdapter.updateList(filteredList)
                    } else {
                        studentAdapter.updateList(studentList)
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}