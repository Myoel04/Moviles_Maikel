package com.example.proyecto_after_aplicacion

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_after_aplicacion.ui.theme.Proyecto_after_AplicacionTheme
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.em

class ventana2 {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Frame2(modifier: Modifier = Modifier, contNavegador: NavController) {
        // declaracion del firebase
        val auth = FirebaseAuth.getInstance()

        // variables para las entradas por parte del usuario
        val email = remember { mutableStateOf("") } // correo del nuevo usuario
        val contrasena = remember { mutableStateOf("") } // Contraseña del nuevo usuario
        val confContrasena = remember { mutableStateOf("") } // Confirmación

        // variable mensaje error
        val errorMensaje = remember { mutableStateOf<String?>(null) }

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(24.dp)
        ) {
            // campo del correo
            TextField(
                value = email.value,
                onValueChange = { email.value = it }, // Al cambiar el valor, se actualiza el valor de la variable por el introducido
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xffd9d9d9)),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 170.dp)
                    .fillMaxWidth(0.9f)
                    .height(56.dp)
            )

            // campo de introducir la constraseña
            TextField(
                value = contrasena.value,
                onValueChange = { contrasena.value = it }, // Actualiza la variable de contrasñea
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xffd9d9d9)),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 263.dp)
                    .fillMaxWidth(0.9f)
                    .height(56.dp),
                //metodo para ocultar la contraseña al introducirla
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            // campo para Confirmar Contraseña
            TextField(
                value = confContrasena.value,
                onValueChange = { confContrasena.value = it }, // cambia la variable de confirmación de la contraseña
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xffd9d9d9)),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 363.dp)
                    .fillMaxWidth(0.9f)
                    .height(56.dp),
                //
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            // Botón Aceptar para guardar en firebase el usuario nuevo
            Button(
                onClick = {
                    // Validación para verificar de que las contraseñas coincidan
                    if (contrasena.value != confContrasena.value) {
                        errorMensaje.value = "Las contraseñas no coinciden" // mensaj ede error si las contraseñas no coinciden
                        return@Button
                    }

                    // registrar del usuario en Firebase
                    auth.createUserWithEmailAndPassword(email.value, contrasena.value)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // si se registra bien, navegar a la pantalla de login
                                contNavegador.navigate("Login")
                            } else {
                                // error si el registro falla
                                errorMensaje.value = "Error de registro: ${task.exception?.message}"
                            }
                        }
                },
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 437.dp)
                    .fillMaxWidth(0.5f)
                    .height(56.dp)
            ) {
                Text(
                    text = "Aceptar",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            // error de autenticación con un Toast: error emergente
            val contexto = LocalContext.current
            LaunchedEffect(errorMensaje.value) {
                errorMensaje.value?.let { message ->
                    //mensaje emergente con el error
                    Toast.makeText(
                        contexto,
                        message,
                        Toast.LENGTH_SHORT
                    ).show()
                    errorMensaje.value = null // vacia el valor de la variable despues de saltar
                }
            }

            // label correo
            Text(
                text = "CORREO ELECTRÓNICO",
                color = Color.Black,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 140.dp)
            )
            //label contraseña
            Text(
                text = "CONTRASEÑA",
                color = Color.Black,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 233.dp)
            )
            //label de confirmar
            Text(
                text = "CONFIRMAR CONTRASEÑA",
                color = Color.Black,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 333.dp)
            )

            // label registrarse
            Text(
                text = "REGISTRARSE",
                color = Color.Black,
                style = TextStyle(fontSize = 25.sp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 58.dp)
            )
        }
    }
}

@Preview(widthDp = 404, heightDp = 578)
@Composable
private fun Frame2Preview() {
    val navController = rememberNavController()
    ventana2().Frame2(modifier = Modifier, contNavegador = navController)
}
