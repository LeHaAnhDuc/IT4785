package vn.edu.hust.studentman

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity()
{
  lateinit var listStudent: MutableList<StudentModel>
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val students = mutableListOf(
      StudentModel("Nguyễn Văn An", "SV001"),
      StudentModel("Trần Thị Bảo", "SV002"),
      StudentModel("Lê Hoàng Cường", "SV003"),
      StudentModel("Phạm Thị Dung", "SV004"),
      StudentModel("Đỗ Minh Đức", "SV005"),
      StudentModel("Vũ Thị Hoa", "SV006"),
      StudentModel("Hoàng Văn Hải", "SV007"),
      StudentModel("Bùi Thị Hạnh", "SV008"),
      StudentModel("Đinh Văn Hùng", "SV009"),
      StudentModel("Nguyễn Thị Linh", "SV010"),
      StudentModel("Phạm Văn Long", "SV011"),
      StudentModel("Trần Thị Mai", "SV012"),
      StudentModel("Lê Thị Ngọc", "SV013"),
      StudentModel("Vũ Văn Nam", "SV014"),
      StudentModel("Hoàng Thị Phương", "SV015"),
      StudentModel("Đỗ Văn Quân", "SV016"),
      StudentModel("Nguyễn Thị Thu", "SV017"),
      StudentModel("Trần Văn Tài", "SV018"),
      StudentModel("Phạm Thị Tuyết", "SV019"),
      StudentModel("Lê Văn Vũ", "SV020")
    )
    listStudent = students
    val studentAdapter = StudentAdapter(students)

    findViewById<RecyclerView>(R.id.recycler_view_students).run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }
  }

  fun showAddStudentDialog() {
    val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null)
    val builder = AlertDialog.Builder(this)
    builder.setView(dialogView)
      .setTitle("Add New Student")
      .setPositiveButton("Add") { dialog, _ ->
        val name = dialogView.findViewById<EditText>(R.id.etName).text.toString()
        val id = dialogView.findViewById<EditText>(R.id.etId).text.toString()
        listStudent.add(StudentModel(name, id))
        dialog.dismiss()
      }
      .setNegativeButton("Cancel") { dialog, _ ->
        dialog.dismiss()
      }
      .create()
      .show()
  }
  fun showEditStudentDialog(student: StudentModel) {
    val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null)
    if (dialogView == null) {
      Log.e("DialogError", "Failed to inflate dialog layout")
    }
    dialogView.findViewById<EditText>(R.id.etName).setText(student.studentName)
    dialogView.findViewById<EditText>(R.id.etId).setText(student.studentId)

    val builder = AlertDialog.Builder(this)
    builder.setView(dialogView)
      .setTitle("Edit Student")
      .setPositiveButton("Update") { dialog, _ ->
        val updatedName = dialogView.findViewById<EditText>(R.id.etName).text.toString()
        val updatedId = dialogView.findViewById<EditText>(R.id.etId).text.toString()
        updateStudent(StudentModel(updatedName, updatedId))
        dialog.dismiss()
      }
      .setNegativeButton("Cancel") { dialog, _ ->
        dialog.dismiss()
      }
      .create()
      .show()
  }
  fun updateStudent(student: StudentModel){

  }
  fun showDeleteConfirmation(student: StudentModel) {
    AlertDialog.Builder(this)
      .setTitle("Delete Student")
      .setMessage("Are you sure you want to delete ${student.studentName}?")
      .setPositiveButton("Yes") { dialog, _ ->
        listStudent.remove(student) // Hàm xóa khỏi danh sách
        showUndoSnackbar(student) // Hiển thị snackbar
        dialog.dismiss()
      }
      .setNegativeButton("No") { dialog, _ ->
        dialog.dismiss()
      }
      .create()
      .show()
  }
  fun showUndoSnackbar(deletedStudent: StudentModel) {
    Snackbar.make(findViewById(R.id.main), "${deletedStudent.studentName} has been deleted", Snackbar.LENGTH_LONG)
      .setAction("Undo") {
        listStudent.add(deletedStudent) // Hàm khôi phục
      }
      .show()
  }



}