package org.tensorflow.lite.examples.imageclassification.viewmodels

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import java.text.NumberFormat

class YogaViewModel: ViewModel() {
    private val _countDownTimer: MutableLiveData<String> = MutableLiveData()
    val countDownTimer: LiveData<String> = _countDownTimer
    val accuracyList: MutableList<Float?> = mutableListOf()

    fun initTimer(timeInMillis: Long, intervalInMillis: Long) {
        object : CountDownTimer(timeInMillis, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                // Used for formatting digit to be in 2 digits only
                val f: NumberFormat = DecimalFormat("00")
                val sec = millisUntilFinished / intervalInMillis % 60
                _countDownTimer.value = f.format(sec)
            }

            // When the task is over it will print 00:00:00 there
            override fun onFinish() {
                _countDownTimer.value  = "Time Up!"
                Log.d("chkList", calculateAverageAccuracy().toString())
            }
        }.start()
    }

    fun calculateAverageAccuracy():Double{
        var average = 0.0
        accuracyList.forEach { accuracyItem ->
            if(accuracyItem != null)
               average += accuracyItem
        }

        if(average != 0.0){
            average /= accuracyList.size
        }
        return average
    }
}