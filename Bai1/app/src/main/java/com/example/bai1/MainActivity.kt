package com.example.bai1
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var etMSSV: EditText
    private lateinit var etHoTen: EditText
    private lateinit var radioGroupGioiTinh: RadioGroup
    private lateinit var etEmail: EditText
    private lateinit var etSoDienThoai: EditText
    private lateinit var btnChonNgaySinh: Button
    private lateinit var calendarView: CalendarView
    private lateinit var spinnerNoiO: Spinner
    private lateinit var checkBoxTheThao: CheckBox
    private lateinit var checkBoxDienAnh: CheckBox
    private lateinit var checkBoxAmNhac: CheckBox
    private lateinit var checkBoxDieuKhoan: CheckBox
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etMSSV = findViewById(R.id.etMSSV)
        etHoTen = findViewById(R.id.etHoTen)
        radioGroupGioiTinh = findViewById(R.id.radioGroupGioiTinh)
        etEmail = findViewById(R.id.etEmail)
        etSoDienThoai = findViewById(R.id.etSoDienThoai)
        btnChonNgaySinh = findViewById(R.id.btnChonNgaySinh)
        calendarView = findViewById(R.id.calendarView)
        spinnerNoiO = findViewById(R.id.spinnerNoiO)
        checkBoxTheThao = findViewById(R.id.checkBoxTheThao)
        checkBoxDienAnh = findViewById(R.id.checkBoxDienAnh)
        checkBoxAmNhac = findViewById(R.id.checkBoxAmNhac)
        checkBoxDieuKhoan = findViewById(R.id.checkBoxDieuKhoan)
        btnSubmit = findViewById(R.id.btnSubmit)

        btnChonNgaySinh.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Tạo DatePickerDialog
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // Hiển thị ngày đã chọn
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                Toast.makeText(this, "Ngày sinh: $date", Toast.LENGTH_SHORT).show()
                // Bạn có thể lưu ngày này vào một biến để sử dụng sau này
            }, year, month, day)

            // Hiển thị dialog
            datePickerDialog.show()
        }

        btnSubmit.setOnClickListener {
            validateForm()
        }
    }

    private fun validateForm() {
        val mssv = etMSSV.text.toString().trim()
        val hoTen = etHoTen.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val soDienThoai = etSoDienThoai.text.toString().trim()
        val gioiTinh = findViewById<RadioButton>(radioGroupGioiTinh.checkedRadioButtonId)
        val soThich = checkBoxTheThao.isChecked || checkBoxDienAnh.isChecked || checkBoxAmNhac.isChecked

        if (mssv.isEmpty() || hoTen.isEmpty() || email.isEmpty() || soDienThoai.isEmpty() || gioiTinh == null || !soThich || !checkBoxDieuKhoan.isChecked) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            return
        }

        // Nếu mọi thông tin đều hợp lệ
        Toast.makeText(this, "Thông tin đã được gửi thành công!", Toast.LENGTH_SHORT).show()
    }
}
