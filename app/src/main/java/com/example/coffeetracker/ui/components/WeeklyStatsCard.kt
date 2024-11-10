package com.example.coffeetracker.ui.components

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