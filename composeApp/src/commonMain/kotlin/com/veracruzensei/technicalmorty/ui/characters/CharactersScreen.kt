package com.veracruzensei.technicalmorty.ui.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.tuempresa.tuapp.generated.resources.Res
import com.tuempresa.tuapp.generated.resources.icon_characters
import com.tuempresa.tuapp.generated.resources.icon_filters
import com.tuempresa.tuapp.generated.resources.icon_menu_vertical
import com.tuempresa.tuapp.generated.resources.icon_search
import com.veracruzensei.technicalmorty.domain.model.CharacterModel
import com.veracruzensei.technicalmorty.ui.core.colors.BackgroundPrimaryColor
import com.veracruzensei.technicalmorty.ui.core.colors.FontPrimaryColor
import com.veracruzensei.technicalmorty.ui.core.colors.UnfocusedContainerColorTextField
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen(
    navigateToDetailScreen: (CharacterModel) -> Unit
) {
    val charactersViewModel = koinViewModel<CharactersViewModel>()
    val state by charactersViewModel.state.collectAsState()
    val characters = state.characters.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundPrimaryColor)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderCharacters()
        SearchBarCharacters(
            query = state.query,
            onQueryChange = { query -> charactersViewModel.updateQuery(query) }
        )
        Spacer(modifier = Modifier.size(16.dp))
        CharactersGridList(
            characters = characters,
            navigateToDetail = navigateToDetailScreen
        )
    }
}

@Composable
fun CharactersGridList(
    characters: LazyPagingItems<CharacterModel>,
    navigateToDetail: (CharacterModel) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when {
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                //Initial load
                item(span = { GridItemSpan(2) }) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color.Green)
                    }
                }
            }

            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                //Empty list
                item {
                    Text("No hay items")
                }
            }

            else -> {
                //Items
                items(characters.itemCount) { position ->
                    characters[position]?.let { characterModel ->
                        CharacterItemList(characterModel) { character ->
                            navigateToDetail(character)
                        }
                    }
                }

                if (characters.loadState.append is LoadState.Loading) {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(Modifier.size(64.dp), color = Color.Green)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItemList(characterModel: CharacterModel, onItemSelected: (CharacterModel) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onItemSelected(characterModel) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(56.dp).clip(shape = CircleShape),
            model = characterModel.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(Res.drawable.icon_characters)
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            CharacterName(characterModel.name)
            CharacterStatus(characterModel.status)
        }
    }
}

@Composable
fun CharacterStatus(status: String) {
    Text(
        text = status,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = FontPrimaryColor
    )
}

@Composable
fun CharacterName(name: String) {
    Text(
        text = name,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = FontPrimaryColor
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
            modifier = Modifier.size(24.dp),
            tint = FontPrimaryColor
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
        fontSize = 18.sp,
        color = FontPrimaryColor
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
            modifier = Modifier.size(24.dp),
            tint = FontPrimaryColor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarCharacters(
    query: String,
    onQueryChange: (String) -> Unit
) {
    SearchBar(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = { onQueryChange(it) },
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
        shape = RoundedCornerShape(8.dp),
        colors = SearchBarDefaults.colors(
            containerColor = UnfocusedContainerColorTextField,
            dividerColor = Color.LightGray
        )
    )
}

