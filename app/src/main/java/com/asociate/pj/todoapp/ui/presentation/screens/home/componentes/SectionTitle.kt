package com.asociate.pj.todoapp.ui.presentation.screens.home.componentes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.asociate.pj.todoapp.ui.theme.ToDoAppTheme

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    title: String,
) {
    androidx.compose.material3.Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun SectionTitlePreview() {
    ToDoAppTheme {
        SectionTitle(
            title = "Today",
            modifier = Modifier
        )
    }
}