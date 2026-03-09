package com.mikailov.student_material_design

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mikailov.student_material_design.data.Student
import com.mikailov.student_material_design.data.students
import com.mikailov.student_material_design.ui.theme.Student_Material_DesignTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Student_Material_DesignTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    StudentApp()
                }
            }
        }
    }
}

@Composable
fun StudentIcon(
    @DrawableRes studentIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(60.dp)
            .padding(8.dp),
        painter = painterResource(studentIcon),
        contentDescription = null
    )
}

@Composable
fun StudentInformation(
    @StringRes studentName: Int,
    studentAge: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)

    ) {
        Text(
            text = stringResource(studentName),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = stringResource(R.string.years_old, studentAge),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}



@Composable
fun StudentItem(
    student: Student,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        StudentIcon(
            studentIcon = student.imageResourceId,
            modifier = Modifier.weight(0.2f)
        )


        StudentInformation(
            studentName = student.name,
            studentAge = student.age,
            modifier = Modifier.weight(0.8f)
        )
    }
}

@Composable
fun StudentApp() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(students) { student ->
            StudentItem(student = student)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentPreview() {
    Student_Material_DesignTheme(darkTheme = false) {
        StudentApp()
    }
}