package com.example.bt1_tuan2_kotlin

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bt1_tuan2_kotlin.ui.theme.BT1_tuan2_kotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BT1_tuan2_kotlinTheme {
                ProfileScreen()
            }
        }
    }
}


@Composable
fun ProfileScreen() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(12.dp)
        ) {
            Title("THỰC HÀNH 01")
            LabelColumn(
                name = name,
                age = age,
                result = result,
                onNameChange = {
                    name = it
                },
                onAgeChange = {
                    age = it
                },
            )
            BtnCheck("Kiểm tra", onClick =  {
                try {
                    var checkAge = age.toInt()
                    when {
                        checkAge >= 65 -> result = "người già"
                        checkAge >= 6 -> result = "người lớn"
                        checkAge >= 2 -> result = "trẻ em"
                        else -> result = "em bé"
                    }
                }

                catch(e: Exception){
                    result = "Vui lòng nhập số ở phần tuổi"
                }

            })
        }
    }
}

@Composable
fun Title(tvTitle: String) {
    Text(
        text = tvTitle,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 56.dp, bottom = 32.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )
}

@Composable
fun AppInputField(label: String, text: String, onValueChange: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.width(100.dp)
        ) {
            Text(
                label, fontWeight = FontWeight.Bold, fontSize = 16.sp
            )
        }
        InputTextField(text, onValueChange)
    }
}

@Composable
fun LabelColumn(
    name: String,
    age: String,
    result: String,
    onNameChange: (String) -> Unit,
    onAgeChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.LightGray, shape = RoundedCornerShape(12.dp)
            )
            .padding(24.dp)
    ) {
        AppInputField("Họ và tên", name, onNameChange)
        Box(modifier = Modifier.height(20.dp))
        AppInputField("Tuổi", age, onAgeChange)
        AgeDislay(result, name)
    }
}

@Composable
fun InputTextField(text: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
            .clip(shape = RoundedCornerShape(8.dp)),
        value = text,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun BtnCheck(check: String, onClick: () -> Unit) {
    Button(
        onClick = onClick, shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(top = 42.dp)
    ) {
        Text(
            text = check, modifier = Modifier.padding(horizontal = 32.dp), fontSize = 24.sp
        )
    }
}

@Composable
fun AgeDislay(result: String, name:String) {
    if (result.isNotEmpty()) {
        return Text(
            text = "$name là $result",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}


