package com.example.proyecto_after_aplicacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.proyecto_after_aplicacion.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Frame4(navController: NavController) {
    // creo la variable para buscar recetas(sin funcion)
    val barraBuscar = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        // barra de busqueda
        OutlinedTextField(
            value = barraBuscar.value,
            onValueChange = { barraBuscar.value = it },
            label = { Text("Hinted search text") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray
            )
        )

        // Título y Subtítulo
        Text(
            text = "CARNES",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
       /* Text(
            text = "Subheading",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
        )*/

        // Primer Elemento con Título, Imagen, y Texto
        Spacer(modifier = Modifier.height(24.dp))

        // Primer Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp) //
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.carnes), //aqui meto la imagen de la carne
                    contentDescription = "Image description",
                    modifier = Modifier.fillMaxWidth().height(180.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Chuleton a la brasa.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        //me falta la funcion al pulsar para que me lleve a la receta completa
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Bistec")
                }
            }
        }

        // Segundo Card
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.carnes),
                    contentDescription = "Image description",
                    modifier = Modifier.fillMaxWidth().height(180.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Texto.",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        // lleva a la receta completa(sin funcion)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Button")
                }
            }
        }
    }
}

@Preview(widthDp = 403, heightDp = 578, showSystemUi = true)
@Composable
private fun Frame4Preview() {
    val navController = rememberNavController()
    Frame4(navController = navController)
}

