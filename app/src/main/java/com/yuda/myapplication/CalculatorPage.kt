package com.yuda.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udojava.evalex.Expression

@Composable
@Preview(showBackground = true)
fun CalculatorPage(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("0") }

   Column (
       modifier = Modifier
           .fillMaxSize()
           .background(Color(0xffe0e0e0))
           .padding(16.dp),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center
   ) {
       Text(
           text = input,
           fontSize = 32.sp,
           fontWeight = FontWeight.Bold,
           modifier = Modifier.fillMaxWidth(),
           color = Color.Black
       )

       Text(
           text = result,
           fontSize = 48.sp,
           fontWeight = FontWeight.Bold,
           modifier = Modifier.fillMaxWidth(),
           color = Color.Black
       )

       Spacer(modifier = Modifier.height(16.dp))

       Column (
           modifier = Modifier
               .fillMaxWidth()
               .background(Color.White)
               .padding(16.dp)
       ) {
           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceEvenly
           ) {
               CalculatorButton("1") {input += "1"}
               CalculatorButton("2") {input += "2"}
               CalculatorButton("3") {input += "3"}
               CalculatorButton("+") {input += "+"}
           }

           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceEvenly
           ) {
               CalculatorButton("4") {input += "4"}
               CalculatorButton("5") {input += "5"}
               CalculatorButton("6") {input += "6"}
               CalculatorButton("-") {input += "-"}
           }

           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceEvenly
           ) {
               CalculatorButton("7") {input += "7"}
               CalculatorButton("8") {input += "8"}
               CalculatorButton("9") {input += "9"}
               CalculatorButton("*") {input += "*"}
           }

           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceEvenly
           ) {
               CalculatorButton("C") {input = ""}
               CalculatorButton("0") {input += "0"; result = "0"}
               CalculatorButton("=") {
                   result = try {
                       eval(input).toString()
                   } catch (e: Exception) {
                       "Error"
                   }
               }
               CalculatorButton("/") {input += "/"}
           }
       }
   }
}

@Composable
fun CalculatorButton(symbol: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .size(64.dp)
    ) {
        Text(text = symbol, fontSize = 24.sp)
    }
}

fun eval(expression: String): String {
    var result = Expression(expression).eval()
    return result.toPlainString()
}