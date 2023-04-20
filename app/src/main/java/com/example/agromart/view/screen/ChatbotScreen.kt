package com.example.agromart.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.agromart.R
import com.example.agromart.ui.theme.Dark_Green
import com.example.agromart.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotScreen(modifier: Modifier, navHostController: NavHostController) {
    var senderMessage by remember {
        mutableStateOf("")
    }
    var mapi = HashMap<String, String>()
    LaunchedEffect(key1 = mapi, block = {
        mapi.put("What crops grow best in sandy soil?", "Carrots, radishes, and sweet potatoes.")
        mapi.put("What is the ideal temperature for growing tomatoes?", "Between 70-80°F.")
        mapi.put("How often should I water my houseplants?", "When the top inch of soil is dry.")
        mapi.put("What is the best way to control aphids on plants?", "Ladybugs and neem oil")
        mapi.put(
            "How do I know when my fruit is ripe?",
            "The color, texture, and aroma of the fruit will change."
        )
        mapi.put(
            "What is the difference between hybrid and heirloom seeds?",
            "Hybrid seeds are created by crossbreeding different varieties while heirloom seeds are passed down through generations."
        )
        mapi.put(
            "How deep should I plant my seeds?",
            "Generally, plant seeds two to three times their diameter."
        )
        mapi.put(
            "What is the best way to store harvested vegetables?",
            "Store vegetables in a cool, dry place, such as a root cellar or refrigerator."
        )
        mapi.put(
            "What is crop rotation?",
            "Planting different crops in the same area each year to prevent soil depletion and pests."
        )
        mapi.put(
            "How can I improve soil fertility?",
            "Add compost, manure, or cover crops to enrich the soil."
        )
        mapi.put(
            "What is the best time of day to water my plants?",
            "Early morning or late evening when it's cooler and the water has time to absorb into the soil."
        )
        mapi.put(
            "What is the difference between organic and conventional farming?",
            "Organic farming uses natural methods without synthetic pesticides or fertilizers while conventional farming uses synthetic inputs."
        )
        mapi.put(
            "How can I prevent weeds in my garden?",
            "Mulching, hand-pulling, or using a pre-emergent herbicide can prevent weed growth."
        )
        mapi.put(
            "What is the best way to protect my plants from frost?",
            "Cover plants with blankets, burlap, or plastic sheets overnight"
        )
        mapi.put(
            "What are companion plants?",
            "Plants that grow well together, provide mutual benefits, and help deter pests."
        )
        mapi.put(
            "What is the best way to prune fruit trees?",
            "Cut off dead or diseased branches and prune for shape and size in late winter."
        )
        mapi.put(
            "How do I know if my soil needs more nutrients?",
            "Test the soil's pH level or look for stunted growth, yellowing leaves, or poor fruit development."
        )
        mapi.put(
            "What is the best way to control slugs and snails in my garden?",
            "Use copper barriers, beer traps, or diatomaceous earth to deter slugs and snails."
        )
        mapi.put(
            "What is the difference between determinate and indeterminate tomato plants?",
            "Determinate plants grow to a set height and produce all their fruit at once while indeterminate plants continue to grow and produce fruit throughout the season."
        )
        mapi.put(
            "How can I attract pollinators to my garden?",
            "Plant native flowers, provide water sources, and avoid pesticide use to attract bees, butterflies, and other pollinators."
        )
        mapi.put(
            "What is the best way to harvest vegetables like tomatoes and peppers?",
            "Use sharp scissors or a knife to cut the stem above the fruit."
        )
        mapi.put(
            "What is crop cover?",
            "A protective fabric placed over crops to prevent pest damage and regulate temperature."
        )
        mapi.put(
            "What is vermicomposting?",
            "A method of composting using worms to break down organic matter into rich fertilizer."
        )
        mapi.put("What is crop yield?", "The amount of crop harvested per unit of land or area.")
        mapi.put(
            "What is the best way to protect my garden from deer and other animals?",
            "Use fencing, netting, or plant repellent to protect your garden from animals."
        )
        mapi.put(
            "What is drip irrigation?",
            "A method of watering plants by slowly dripping water onto the soil at the root level."
        )
        mapi.put(
            "How can I tell if my plant needs more or less sunlight?",
            "Look for signs of sunburn or discoloration and adjust accordingly."
        )
        mapi.put(
            "What is grafting?",
            "A method of joining two plants together to create a stronger or more disease-resistant plant."
        )
        mapi.put(
            "What is the difference between direct seeding and transplanting?",
            "Direct seeding is planting seeds directly in the ground while transplanting is moving a seedling from a container to the ground."
        )
        mapi.put(
            "How can I make my own compost?",
            "Collect organic matter like leaves, grass clippings, and food scraps and let them decompose over time."
        )

    })
    val map by remember {
        mutableStateMapOf<String,String>()
    }

    Scaffold(bottomBar = {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = senderMessage,
                onValueChange = {
                    senderMessage = it
                },
                modifier,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                )
            )
            IconButton(onClick = {

                senderMessage = ""
            }) {
                Icon(imageVector = Icons.Rounded.Send, contentDescription = null)
            }
        }
    }, topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Chatbot",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

            },
            navigationIcon = {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            })
    }) {
        Box {

            LazyColumn(modifier.padding(it)) {
                items(mapi.toList()) {
                    sendView(it.first)
                    recieveView(it.second)
                }
            }
        }
    }
}

@Composable
fun sendView(message: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Card(
            shape = RoundedCornerShape(
                bottomEnd = 0.dp, topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp
            ),
            modifier = Modifier.padding(end = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Green, contentColor = Color.White)
        ) {
            Text(
                text = message, modifier = Modifier
                    .padding(20.dp)
            )
        }
    }
}

@Composable
fun recieveView(message: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Card(
            shape = RoundedCornerShape(
                bottomEnd = 20.dp, topStart = 20.dp, topEnd = 20.dp, bottomStart = 0.dp
            ),
            modifier = Modifier.padding(start = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Dark_Green,
                contentColor = Color.White
            )
        ) {
            Text(
                text = message, modifier = Modifier
                    .padding(20.dp)
            )
        }
    }
}