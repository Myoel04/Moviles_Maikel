package com.example.proyecto_after_aplicacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Frame3(modifier: Modifier = Modifier, navController: NavController) {
    // Box de la pantalla
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        //mostrar los card de forma cuadriculada
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),  // dos columnas
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp), // espacio horizontal entre los ítems
            verticalArrangement = Arrangement.spacedBy(16.dp) // espacio vertical entre los ítems
        ) {
            // fila1
            item {
                FoodItem(
                    imageResource = R.drawable.pizza1, // Imagen de comida rápida
                    text = "COMIDA RÁPIDA",
                    onClick = { navController.navigate("pizza") }
                )
            }
            item {
                FoodItem(
                    imageResource = R.drawable.marisco, // Imagen de mariscos
                    text = "MARISCOS",
                    onClick = { navController.navigate("mariscos") }
                )
            }

            // fila2
            item {
                FoodItem(
                    imageResource = R.drawable.pasta, // Imagen de pasta
                    text = "PASTA",
                    onClick = { navController.navigate("pasta") }
                )
            }
            item {
                FoodItem(
                    imageResource = R.drawable.carnes, // Imagen de carnes
                    text = "CARNES",
                    onClick = { navController.navigate("carnes") }
                )
            }

            // fila3
            item {
                FoodItem(
                    imageResource = R.drawable.postres, // Imagen de postres
                    text = "POSTRES",
                    onClick = { navController.navigate("postres") } // pantalla postres
                )
            }
            item {
                FoodItem(
                    imageResource = R.drawable.sopas, // Imagen de sopas
                    text = "SOPAS",
                    onClick = { navController.navigate("sopas") }
                )
            }
        }
    }
}

@Composable
fun FoodItem(imageResource: Int, text: String, onClick: () -> Unit) {
    // Column para contener la imagen y el texto
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick) // hacer clic en cada ítem
            .fillMaxWidth()
    ) {
        // Card para mostrar la imagen
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xff1d192b)), //color fondo card
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            // imagen para cada card
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = text,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // textos debajo imagenes
        Text(
            text = text,
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(widthDp = 404, heightDp = 788, showSystemUi = true)
@Composable
private fun Frame3Preview() {
    val navController = rememberNavController()
    Frame3(modifier = Modifier, navController = navController)
}


