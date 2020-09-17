package com.example.bravadive.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bravadive.*
import com.example.bravadive.ViewModel.DetailActivityViewModel
import com.example.bravadive.adapter.TechnicIconAdapter
import com.example.bravadive.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel : DetailActivityViewModel

    companion object {
        const val CLAVE_1 = "myClave1"

        fun getIntent(context: Context, spot: Spot): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(
                    CLAVE_1,
                    spot.spotId
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(DetailActivityViewModel::class.java)

        //Comprovar que esten lo extras y si estan leerlos
        intent.extras.let {
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.IO) {

                    val spotId = intent.getIntExtra(CLAVE_1, 0)

                    val imageList = viewModel.getImages(spotId)
                    val iconList = viewModel.getIcons(spotId)
                    val spot = viewModel.getSpot(spotId)

                    withContext(Dispatchers.Main) {
                        val pagerAdapter = ViewPagerAdapter(imageList)
                        imageViewPager.adapter = pagerAdapter

                        val iconAdapter = TechnicIconAdapter(iconList)
                        rVtAspects.adapter = iconAdapter

                        tvSpotName.text = spot.name
                        tvSpottext.text = spot.text
                    }
                }
            }
        }
        iVLogo.setImageResource(R.mipmap.brava_dive_oval)
    }
}