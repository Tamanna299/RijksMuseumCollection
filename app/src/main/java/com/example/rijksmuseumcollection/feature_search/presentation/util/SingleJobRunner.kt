package com.example.rijksmuseumcollection.feature_search.presentation.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SingleJobRunner {
    private var currentJob: Job? = null

    fun launchIn(scope: CoroutineScope, block: suspend () -> Unit) {
        currentJob?.cancel()
        currentJob = scope.launch {
            block()
        }
    }
}