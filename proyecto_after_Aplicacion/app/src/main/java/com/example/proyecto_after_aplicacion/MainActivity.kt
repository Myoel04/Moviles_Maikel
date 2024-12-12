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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_after_aplicacion.ui.theme.Proyecto_after_AplicacionTheme
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.em

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_after_AplicacionTheme {
                // variable para navegar en ventanas
                val contNavegador = rememberNavController()

                // configuracion del navegador entre ventanas
                NavHost(navController = contNavegador, startDestination = "login") {
                    composable("login") {
                        Frame1(contNavegador = contNavegador) // ventana de login
                    }
                    composable("ventana2") {
                        ventana2().Frame2(contNavegador = contNavegador) // ventana de registro
                    }
                    composable("ventCategorias") {
                        Frame3(navController = contNavegador) // ventana de categorías de la comida
                    }
                }
            }
        }
    }
}

// Componente para la pantalla de login
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Frame1(modifier: Modifier = Modifier, contNavegador: NavController) {

    // declaracion del firebase para controlar el login
    val autentificacion = FirebaseAuth.getInstance()
    // variables para email y la contraseña del usuario
    val email = remember { mutableStateOf("") }
    val contrasena = remember { mutableStateOf("") }

    // variable para que salte error
    val errorMensaje = remember { mutableStateOf<String?>(null) }

    Box(
        modifier = modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .background(Color.White)
            .padding(24.dp)
    ) {
        Text(
            text = "LOGIN",
            color = Color.Black,
            style = TextStyle(fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp)
        )

        //label de l usuario
        Text(
            text = "USUARIO",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp, top = 150.dp)
        )

        // textfield para el email
        TextField(
            value = email.value,
            onValueChange = { email.value = it },  //aqui actualiza el email por el introducido
            colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xffd9d9d9)),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp, top = 180.dp)
                .fillMaxWidth(0.9f)
                .height(56.dp)
        )

        //label de la contraseña
        Text(
            text = "CONTRASEÑA",
            color = Color.Black,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp, top = 275.dp)
        )

        //text field para la ingresar la contraseña del usuario
        TextField(
            value = contrasena.value,
            onValueChange = { contrasena.value = it }, //darle el valor introducido a la variable de contraseña
            colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xffd9d9d9)),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp, top = 300.dp)
                .fillMaxWidth(0.9f)
                .height(56.dp),
            // metodo para ocultar la contraseña cuando la escribamos
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        // boton de entrar
        Button(
            onClick = {
                // verifico que ambos campos no estan vacio,
                if (email.value.isEmpty() || contrasena.value.isEmpty()) {
                    errorMensaje.value = "Por favor ingrese usuario y contraseña" // si esta vació salta el mensaje
                    return@Button
                }

                //metodo para comprobar lo ingresado
                autentificacion.signInWithEmailAndPassword(email.value, contrasena.value)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // si los usuarios coinciden con los guardados en firebase navega a la frame 2
                            contNavegador.navigate("ventCategorias")
                        } else {
                            //si no, salta error de inicio de sesion
                            errorMensaje.value = "Error de inicio de sesión: ${task.exception?.message}"
                        }
                    }
            },
            shape = RoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 24.dp, bottom = 32.dp)
                .fillMaxWidth(0.4f)
        ) {
            Text(
                text = "Entrar",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
        }

        // boton ventana registrarse
        Button(
            onClick = { contNavegador.navigate("ventana2") }, //ir a la ventana  de registro
            shape = RoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 32.dp)
                .fillMaxWidth(0.4f)
        ) {
            Text(
                text = "Registrarse",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
        }

        //error de autenticación con un Toast: error emergente
        val contexto = LocalContext.current
        LaunchedEffect(errorMensaje.value) {
            errorMensaje.value?.let { message ->
                Toast.makeText(
                    contexto,
                    message,
                    Toast.LENGTH_SHORT
                ).show()
                errorMensaje.value = null
            }
        }
    }
}

@Preview(widthDp = 400, heightDp = 802, showSystemUi = true)
@Composable
private fun Frame1Preview() {
    Proyecto_after_AplicacionTheme {
        Frame1(contNavegador = rememberNavController())
    }
}
