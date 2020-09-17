package com.example.bravadive.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bravadive.App
import com.example.bravadive.Spot
import com.example.bravadive.SpotIcon
import com.example.bravadive.SpotImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailActivityViewModel(application: Application) : AndroidViewModel(application) {


    suspend fun getSpot(spotId: Int): Spot {
        return withContext(Dispatchers.IO) {
            return@withContext App.getDatabase(getApplication()).spotDao().loadSpotbyId(spotId)
        }
    }

    suspend fun getIcons(spotId: Int): List<SpotIcon> {
        return withContext(Dispatchers.IO) {
            return@withContext App.getDatabase(getApplication()).spotIconDao().loadSpotIconbyId(spotId)
        }
    }

    suspend fun getImages(spotId: Int): List<SpotImage> {
        return withContext(Dispatchers.IO) {
            return@withContext App.getDatabase(getApplication()).spotImageDao().loadSpotImagebyId(spotId)
        }
    }


}