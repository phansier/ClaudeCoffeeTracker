package com.example.coffeetracker.ui.components

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
                    icon = Icons.Outlined.Coffee,
                    value = stats.totalCups.toString(),
                    label = "Cups"
                )
                StatItem(
                    icon = Icons.Outlined.WaterDrop,
                    value = "${stats.totalVolume}ml",
                    label = "Volume"
                )
                StatItem(
                    icon = Icons.Outlined.BoltCircle,
                    value = "${stats.totalCaffeine}mg",
                    label = "Caffeine"
                )
            }
        }
    }
}