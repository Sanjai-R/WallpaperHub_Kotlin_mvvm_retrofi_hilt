package com.example.wallpaper.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.sp
import com.example.wallpaper.ui.theme.poppinsFont
import com.example.wallpaper.view.auth.Input

data class InputField(
    val label: String,
    val value: String,
    val type: String,
    val error: String,
    val isPassword: Boolean,
    val hint: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableTextField(
    input: Input,testTag:String?="", onValueChange: (String) -> Unit,

) {
    OutlinedTextField(

        placeholder = {

            Text(
                text = input.hint,
                fontFamily = poppinsFont,
                color = Color(0xFFA0A3BD),
                fontSize = 14.sp
            )
        },
        value = input.value,
        onValueChange = {
            onValueChange(it)
        },


        modifier = Modifier.fillMaxWidth().testTag(input.testTag),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFF3F5F9),
            unfocusedBorderColor = Color.Transparent,
            ),





        )


}