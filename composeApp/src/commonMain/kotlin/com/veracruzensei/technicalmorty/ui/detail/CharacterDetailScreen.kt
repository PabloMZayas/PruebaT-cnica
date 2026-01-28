package com.veracruzensei.technicalmorty.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.tuempresa.tuapp.generated.resources.Res
import com.tuempresa.tuapp.generated.resources.icon_back
import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import com.veracruzensei.technicalmorty.ui.core.colors.BackgroundPrimaryColor
import com.veracruzensei.technicalmorty.ui.core.colors.FontPrimaryColor
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharacterDetailScreen(
    characterModel: CharacterModel,
    onBack: () -> Unit = {}
) {

    koinViewModel<CharacterDetailViewModel>()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundPrimaryColor),
        topBar = { TopAppBarDetailScreen(onBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(BackgroundPrimaryColor)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                //.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageCharacter(characterModel.image)
                Spacer(modifier = Modifier.size(20.dp))
                NameCharacter(characterModel.name)
                SpeciesCharacter(characterModel.species)
                GenderCharacter(characterModel.gender)
            }
            OtherCharacterInfo(characterModel.originName, characterModel.locationName, characterModel.episodes)
        }
    }
}

@Composable
fun OtherCharacterInfo(
    originName: String,
    locationName: String,
    episodes: List<String>
) {
    TextOrigin(originName)
    Spacer(modifier = Modifier.size(16.dp))
    TextLocation(locationName)
    Spacer(modifier = Modifier.size(16.dp))
    TextEpisodes(episodes)
}

@Composable
fun TextEpisodes(episodes: List<String>) {
    Text(
        text = "Episodes",
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = FontPrimaryColor
    )
    episodes.forEach {
        Text(
            text = it,
            fontSize = 16.sp,
            color = FontPrimaryColor
        )
    }
}

@Composable
fun TextLocation(locationName: String) {
    Text(
        text = "Last known location",
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = FontPrimaryColor
    )
    Text(
        text = locationName,
        fontSize = 16.sp,
        color = FontPrimaryColor
    )
}

@Composable
fun TextOrigin(originName: String) {
    Text(
        text = "Origin",
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = FontPrimaryColor
    )
    Text(
        text = originName,
        fontSize = 16.sp,
        color = FontPrimaryColor
    )
}

@Composable
fun GenderCharacter(gender: String) {
    Text(
        text = gender,
        fontSize = 16.sp,
        color = FontPrimaryColor
    )
}

@Composable
fun SpeciesCharacter(species: String) {
    Text(
        text = species,
        fontSize = 16.sp,
        color = FontPrimaryColor
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDetailScreen(onBack: () -> Unit) {
    TopAppBar(
        modifier = Modifier,
        title = { TextCharacterDetail() },
        navigationIcon = { BackButton(onBack) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackgroundPrimaryColor
        )
    )
}

@Composable
fun NameCharacter(name: String) {
    Text(
        text = name,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = FontPrimaryColor
    )
}

@Composable
fun ImageCharacter(image: String) {
    AsyncImage(
        model = image,
        contentDescription = null,
        modifier = Modifier.size(190.dp).clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TextCharacterDetail() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Character Details",
        textAlign = TextAlign.Center,
        color = FontPrimaryColor
    )
}

@Composable
fun BackButton(onBack: () -> Unit = {}) {
    IconButton(
        onClick = onBack
    ) {
        Icon(
            painter = painterResource(Res.drawable.icon_back),
            contentDescription = "back button",
            tint = FontPrimaryColor
        )
    }
}
