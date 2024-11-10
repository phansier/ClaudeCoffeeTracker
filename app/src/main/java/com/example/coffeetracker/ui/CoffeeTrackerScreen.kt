package com.example.coffeetracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coffeetracker.domain.Coffee
import com.example.coffeetracker.domain.CoffeeSize
import com.example.coffeetracker.domain.CoffeeType
import com.example.coffeetracker.ui.components.CoffeeForm
import com.example.coffeetracker.ui.components.CoffeeItem
import com.example.coffeetracker.ui.components.DailyStatsCard
import com.example.coffeetracker.ui.components.WeeklyStatsCard
import com.github.mikephil.charting.charts.BarChart
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import com.example.coffeetracker.domain.DailyStats
import com.example.coffeetracker.domain.TrackerTab
import com.example.coffeetracker.domain.WeeklyStats

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeTrackerScreen(
    viewModel: CoffeeTrackerViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(TrackerTab.DAILY) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Coffee Tracker") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TabRow(
                selectedTabIndex = selectedTab.ordinal,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                TrackerTab.values().forEach { tab ->
                    Tab(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        text = { Text(tab.title) }
                    )
                }
            }

            when (selectedTab) {
                TrackerTab.DAILY -> DailyView(
                    stats = uiState.dailyStats,
                    coffees = uiState.todayCoffees,
                    onAddCoffee = viewModel::addCoffee,
                    onDeleteCoffee = viewModel::deleteCoffee
                )
                TrackerTab.WEEKLY -> WeeklyView(
                    stats = uiState.weeklyStats,
                    coffees = uiState.weekCoffees
                )
            }
        }
    }
}

@Composable
fun DailyView(
    stats: DailyStats,
    coffees: List<Coffee>,
    onAddCoffee: (CoffeeType, CoffeeSize) -> Unit,
    onDeleteCoffee: (Coffee) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Add Coffee Form
        CoffeeForm(onAddCoffee = onAddCoffee)

        // Daily Stats
        DailyStatsCard(stats = stats)

        // Coffee List
        LazyColumn {
            items(coffees) { coffee ->
                CoffeeItem(
                    coffee = coffee,
                    onDelete = { onDeleteCoffee(coffee) }
                )
            }
        }
    }
}

@Composable
fun WeeklyView(
    stats: WeeklyStats,
    coffees: List<Coffee>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        WeeklyStatsCard(stats = stats)

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            factory = { context ->
                BarChart(context).apply {
                    description.isEnabled = false
                    setDrawGridBackground(false)
                    // Configure chart settings
                }
            },
            update = { chart ->
                // Update chart data
                updateChartData(chart, coffees)
            }
        )
    }
}

