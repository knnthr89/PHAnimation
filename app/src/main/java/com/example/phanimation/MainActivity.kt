package com.example.phanimation

import android.graphics.Color
import android.graphics.fonts.FontFamily
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.phanimation.ui.theme.PHAnimationTheme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PHAnimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SearchBar(
                        query = "",
                        onQueryChange = {}
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedPlaceholder(
    hints: List<String>
){
    val iterator = hints.listIterator()
    
    val target by produceState(initialValue = hints.first()){
        iterator.doWhenHasNextOrPrevious { 
            value = it
        }
    }
    
    AnimatedContent(targetState = target,
    transitionSpec =  {ScrollAnimation()}
    ) {
        str ->
        Text(text = str)
    }
}

@Composable
fun SearchBar(
    query : String,
    onQueryChange : (String) -> Unit,
){
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 750.dp),
        placeholder = {
        AnimatedPlaceholder(hints = listOf(
            "Search your favourite food",
            "Search your favourite beverage",
            "Search your favourite movie",
            "Search your favourite book",
            "Search your favourite place",
        ))
    })
}