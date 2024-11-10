package com.example.coffeetracker.ui

@Composable
fun CoffeeTrackerScreen(
    viewModel: CoffeeTrackerViewModel = hiltViewModel()
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

