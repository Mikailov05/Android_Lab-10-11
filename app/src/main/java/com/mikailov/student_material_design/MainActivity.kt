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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
            .padding(8.dp)
            .clip(MaterialTheme.shapes.small),
        painter = painterResource(studentIcon),
        contentDescription = null,
        contentScale = ContentScale.Crop
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
            modifier = Modifier.padding(bottom = 4.dp),
            style = MaterialTheme.typography.displayMedium // Изменено на displayMedium
        )
        Text(
            text = stringResource(R.string.years_old, studentAge),
            style = MaterialTheme.typography.bodyLarge // Изменено на bodyLarge
        )
    }
}

@Composable
fun StudentItem(
    student: Student,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
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
}

@Composable
fun StudentApp() {
    Scaffold { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(students) { student ->
                StudentItem(
                    student = student,
                    modifier = Modifier.padding(
                        dimensionResource(id = R.dimen.padding_small)
                    )
                )
            }
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

@Preview(showBackground = true)
@Composable
fun StudentDarkThemePreview() {
    Student_Material_DesignTheme(darkTheme = true) {
        StudentApp()
    }
}