package com.mikailov.student_material_design

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = stringResource(studentName),
            modifier = Modifier.padding(bottom = 4.dp),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = stringResource(R.string.years_old, studentAge),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun StudentDescription(
    @StringRes studentDescription: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(studentDescription),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun StudentItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun StudentItem(
    student: Student,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
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
                    modifier = Modifier.weight(0.6f)
                )

                StudentItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                    modifier = Modifier.weight(0.2f)
                )
            }

            if (expanded) {
                StudentDescription(
                    studentDescription = student.description,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_student_logo),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    )
}

@Composable
fun StudentApp() {
    Scaffold(
        topBar = {
            StudentTopAppBar()
        }
    ) { paddingValues ->
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