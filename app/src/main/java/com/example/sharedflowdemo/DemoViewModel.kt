package com.example.sharedflowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DemoViewModel : ViewModel() {
    // 1. Создаём MutableSharedFlow (приватный)
    private val _sharedFlow = MutableSharedFlow<Int>()

    // 2. Делаем его доступным только для чтения (публичный)
    val sharedFlow = _sharedFlow.asSharedFlow()

    // 3. Инициализация потока
    init {
        sharedFlowInit()
    }

    // 4. Функция запуска потока
    private fun sharedFlowInit() {
        viewModelScope.launch {
            for (i in 1..1000) {
                delay(2000)
                println("Emitting $i")  // ← добавляем вывод
                _sharedFlow.emit(i)
            }
        }
    }
}