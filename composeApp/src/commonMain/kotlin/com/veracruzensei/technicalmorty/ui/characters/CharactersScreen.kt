package com.veracruzensei.technicalmorty.ui.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tuempresa.tuapp.generated.resources.Res
import com.tuempresa.tuapp.generated.resources.icon_filters
import com.tuempresa.tuapp.generated.resources.icon_menu_vertical
import com.tuempresa.tuapp.generated.resources.icon_search
import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen(
    navigateToDetailScreen: () -> Unit
) {
    val charactersViewModel = koinViewModel<CharactersViewModel>()

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderCharacters()
        SearchBarCharacters()
        ButtonGoToDetail(navigateToDetailScreen)
        ListCharacters(navigateToDetailScreen = navigateToDetailScreen)
    }
}

@Composable
fun ButtonGoToDetail(navigateToDetailScreen: () -> Unit) {
    Button(
        onClick = { }
    ) {
        Text(text = "Go to detail")
    }
}

@Composable
fun ListCharacters(
    characters: List<CharacterModel> = emptyList(),
    navigateToDetailScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        characters.forEach { characterModel ->
            ItemCharacterModel(characterModel)
        }
    }
}

@Composable
fun ItemCharacterModel(characterModel: CharacterModel) {
    Row {
        ImageCharacter(characterModel.image)
        Spacer(modifier = Modifier.size(8.dp))
        Column {
            CharacterName(name = characterModel.name)
            Spacer(modifier = Modifier.size(8.dp))
            CharacterStatus(characterModel.status)
        }
    }
}

@Composable
fun ImageCharacter(image: String) {
    Image(
        modifier = Modifier.clip(
            shape = CircleShape
        ),
        painter = painterResource(Res.drawable.icon_filters),
        contentDescription = "image character"
    )
    /*AsyncImage(
        modifier = Modifier
            .padding(1.dp)
            .defaultMinSize(minHeight = 30.dp)
            .size(68.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(8.dp),
        model = ImageRequest.Builder(LocalContext.current)
            .data(avatar)
            .decoderFactory(SvgDecoder.Factory())
            .diskCachePolicy(CachePolicy.ENABLED)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(R.drawable.icon_chicken_serious),
        error = painterResource(R.drawable.icon_image_day_night)
    )*/
}

@Composable
fun CharacterStatus(status: String) {
    Text(
        text = status,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CharacterName(name: String) {
    Text(
        text = name,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun HeaderCharacters() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButtonMenuVertical()
        TextCharacters(modifier = Modifier.weight(1f))
        IconButtonFilters()
    }
}

@Composable
fun IconButtonMenuVertical() {
    IconButton(
        onClick = { }
    ) {
        Icon(
            painter = painterResource(Res.drawable.icon_menu_vertical),
            contentDescription = "icon_menu_vertical",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun TextCharacters(modifier: Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = "Characters",
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}

@Composable
fun IconButtonFilters() {
    IconButton(
        onClick = { }
    ) {
        Icon(
            painter = painterResource(Res.drawable.icon_filters),
            contentDescription = "icon_filters",
            modifier = Modifier.size(24.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarCharacters(query: String = "") {
    SearchBar(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth(),
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = { },
                onSearch = { },
                expanded = false,
                onExpandedChange = {},
                enabled = true,
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(start = 16.dp).size(24.dp),
                        painter = painterResource(Res.drawable.icon_search),
                        contentDescription = null,
                    )
                },
            )
        },
        expanded = false,
        onExpandedChange = {},
        content = {},
        shape = RoundedCornerShape(8.dp)
    )
}

