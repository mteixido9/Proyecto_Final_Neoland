package com.example.bravadive.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bravadive.*
import com.example.bravadive.adapter.TechnicIconAdapter
import com.example.bravadive.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

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

    fun getSpot(spotId: Int): Spot {
        return App.getDatabase(application).spotDao().loadSpotbyId(spotId)
    }

    fun getIcons(spotId: Int): List<SpotIcon> {
        return App.getDatabase(application).spotIconDao().loadSpotIconbyId(spotId)
    }

    fun getImages(spotId: Int): List<SpotImage> {
        return App.getDatabase(application).spotImageDao().loadSpotImagebyId(spotId)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Comprovar que esten lo extras y si estan leerlos
        intent.extras.let {
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.IO) {

                    val spotId = intent.getIntExtra(CLAVE_1, 0)

                    val imageList = getImages(spotId)
                    val iconList = getIcons(spotId)
                    val spot = getSpot(spotId)

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


        /*val imageList = listOf(
            R.mipmap.image_test_1,
            R.mipmap.image_test_2
        )*/

        /*val iconList= listOf(
            R.drawable.ic_starfish,
            R.drawable.ic_beach,
            R.drawable.ic_boya,
            R.drawable.ic_consumo_oxigeno,
            R.drawable.ic_coral,
            R.drawable.ic_corriente,
            R.drawable.ic_embarcaciones_frecuentes,
            R.drawable.ic_luz_de_buceo,
            R.drawable.ic_nocturna_clean,
            R.drawable.ic_parada_descompresio,

        )*/

        iVLogo.setImageResource(R.mipmap.brava_dive_oval)


    }
}