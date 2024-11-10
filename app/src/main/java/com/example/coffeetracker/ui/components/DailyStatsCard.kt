package com.example.coffeetracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coffeetracker.domain.DailyStats

@Composable
fun DailyStatsCard(stats: DailyStats) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Today's Stats",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem(
//                    icon = Icons.Outlined.Coffee,
                    icon = Icons.Outlined.AccountCircle,
                    value = stats.totalCups.toString(),
                    label = "Cups"
                )
                StatItem(
//                    icon = Icons.Outlined.WaterDrop,
                    icon = Icons.Outlined.MoreVert,
                    value = "${stats.totalVolume}ml",
                    label = "Volume"
                )
                StatItem(
//                    icon = Icons.Outlined.BoltCircle,
                    icon = Icons.Outlined.Menu,
                    value = "${stats.totalCaffeine}mg",
                    label = "Caffeine"
                )
            }
        }
    }
}