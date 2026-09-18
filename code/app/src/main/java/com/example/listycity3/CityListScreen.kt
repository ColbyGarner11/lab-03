package com.example.listycity3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CityListScreen(
    cities: List<City>,
    modifier: Modifier = Modifier,
    onUpdateCity: (City, City) -> Unit = { _, _ -> }
) {
    var selectedCity by remember { mutableStateOf<City?>(null) }
    var editedName by remember { mutableStateOf("") }
    var editedProvince by remember { mutableStateOf("") }

    Column(modifier = modifier) {

        if (selectedCity != null) {

            OutlinedTextField(
                value = editedName,
                onValueChange = { editedName = it },
                label = { Text("City") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )

            OutlinedTextField(
                value = editedProvince,
                onValueChange = { editedProvince = it },
                label = { Text("Province") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )

            Button(
                onClick = {
                    val oldCity = selectedCity

                    if (oldCity != null) {
                        val updatedCity = City(
                            name = editedName,
                            province = editedProvince
                        )

                        onUpdateCity(oldCity, updatedCity)
                        selectedCity = null
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Save Changes")
            }
        }

        LazyColumn {
            itemsIndexed(cities) { index, city ->

                CityRow(
                    city = city,
                    onClick = {
                        selectedCity = city
                        editedName = city.name
                        editedProvince = city.province
                    }
                )

                if (index < cities.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun CityRow(
    city: City,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = city.name,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = city.province,
            fontSize = 30.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityListScreenPreview() {
    CityListScreen(
        cities = listOf(
            City("Edmonton", "AB"),
            City("Vancouver", "BC"),
            City("Calgary", "AB")
        )
    )
}