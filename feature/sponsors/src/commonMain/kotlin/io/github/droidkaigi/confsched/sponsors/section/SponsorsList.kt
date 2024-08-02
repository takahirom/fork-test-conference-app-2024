package io.github.droidkaigi.confsched.sponsors.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import io.github.droidkaigi.confsched.designsystem.theme.KaigiTheme
import io.github.droidkaigi.confsched.model.Plan.GOLD
import io.github.droidkaigi.confsched.model.Plan.PLATINUM
import io.github.droidkaigi.confsched.model.Plan.SUPPORTER
import io.github.droidkaigi.confsched.model.Sponsor
import io.github.droidkaigi.confsched.model.fakes
import io.github.droidkaigi.confsched.sponsors.SponsorsListUiState
import io.github.droidkaigi.confsched.sponsors.component.SponsorItem
import kotlinx.collections.immutable.toPersistentList
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SponsorsList(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    uiState: SponsorsListUiState,
    onSponsorsItemClick: (url: String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior?,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(6),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(padding)
            .padding(horizontal = 12.dp)
            .let {
                if (scrollBehavior != null) {
                    it.nestedScroll(scrollBehavior.nestedScrollConnection)
                } else {
                    it
                }
            },
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "PLATINUM SPONSORS",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
            )
        }
        items(
            items = uiState.platinumSponsors,
            span = { GridItemSpan(maxLineSpan) },
        ) { sponsor ->
            SponsorItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                sponsor = sponsor,
                onSponsorsItemClick = onSponsorsItemClick,
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "GOLD SPONSORS",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
            )
        }
        items(
            items = uiState.goldSponsors,
            span = { GridItemSpan(3) },
        ) { sponsor ->
            SponsorItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(77.dp),
                sponsor = sponsor,
                onSponsorsItemClick = onSponsorsItemClick,
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "SUPPORTERS",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
            )
        }
        items(
            items = uiState.platinumSponsors,
            span = { GridItemSpan(2) },
        ) { sponsor ->
            SponsorItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(77.dp),
                sponsor = sponsor,
                onSponsorsItemClick = onSponsorsItemClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun SponsorsListPreview() {
    KaigiTheme {
        Surface {
            SponsorsList(
                uiState = SponsorsListUiState(
                    platinumSponsors = Sponsor.fakes().filter { it.plan == PLATINUM }.toPersistentList(),
                    goldSponsors = Sponsor.fakes().filter { it.plan == GOLD }.toPersistentList(),
                    supporters = Sponsor.fakes().filter { it.plan == SUPPORTER }.toPersistentList(),
                ),
                padding = PaddingValues(),
                onSponsorsItemClick = {},
                scrollBehavior = null,
            )
        }
    }
}
