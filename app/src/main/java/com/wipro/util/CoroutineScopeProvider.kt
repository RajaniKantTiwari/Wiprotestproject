package com.wipro.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

object CoroutineScopeProvider {
    private val parentJob = Job()
    fun coroutineScopeIo():CoroutineScope{
       return CoroutineScope(Dispatchers.IO + parentJob)
    }
    fun coroutineScopeMain():CoroutineScope{
        return CoroutineScope(Dispatchers.Main + parentJob)
    }

}