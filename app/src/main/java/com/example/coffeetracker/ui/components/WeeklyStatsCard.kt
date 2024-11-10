package com.example.coffeetracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coffeetracker.domain.WeeklyStats

@Composable
fun WeeklyStatsCard(stats: WeeklyStats) {
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
                text = "Weekly Summary",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem(
                    icon = Icons.Outlined.CalendarToday,
                    value = stats.totalCups.toString(),
                    label = "Week Total"
                )
                StatItem(
                    icon = Icons.Outlined.ShowChart,
                    value = stats.averageCups.toString(),
                    label = "Daily Avg"
                )
                StatItem(
                    icon = Icons.Outlined.WaterDrop,
                    value = "${stats.totalVolume}ml",
                    label = "Volume"
                )
            }
        }
    }
}