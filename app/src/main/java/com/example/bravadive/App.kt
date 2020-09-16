package com.example.bravadive

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bravadive.activity.MapsActivity
import com.facebook.stetho.Stetho
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class App : Application() {

    // Estatico para poder llamar a la Db desdecualquier Activity.
    companion object {
        private var db: AppDatabase? = null

        //Funcion para crear el db sino esta creado. Lo comprovamos con el let.
        fun getDatabase(application: Application): AppDatabase {
            db?.let { return it }

            db = Room.databaseBuilder(application, AppDatabase::class.java, "main.db")
                .addCallback(getCallback())
                .build()
            return db as AppDatabase
        }

        //Funcion para cargar los datos al db.
        fun getCallback(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {

                    CoroutineScope(Dispatchers.IO).launch {

                        //Creamos todos los spots
                        var spots: List<Spot> = listOf(
                            Spot("vallpresona", "blabla", false, 41.753350f, 2.971817f),
                            Spot("delsConcagats", "Blabla", false, 41.753683f, 2.975700f),
                            Spot("denBosc", "Blabla", false, 41.753683f, 2.975700f),
                            Spot("delVigata", "Blabla", false, 41.753683f, 3.0222648f),
                            Spot("rocaSadolitx", "Blabla", false, 41.770833f, 3.028583f),
                            Spot("calaRovira", "Blabla", false, 41.821717f, 3.074117f),
                            Spot("rocaRoja", "Blabla", false, 41.817483f, 3.091067f),
                            Spot("laPintaella", "Blabla", false, 41.778633f, 3.042333f),
                            Spot("illesFormigues", "Blabla", false, 41.778633f, 3.042333f),
                            Spot("lesSofreres", "Blabla", false, 41.778633f, 3.042333f)
                        )

                        //Añadimos la lista de spots a la DB.
                        App.db?.spotDao()?.insertAll(spots)

                        App.db?.let {
                            spots = it.spotDao().getAll()
                        }
                        Log.w("añadidosApp", "Añadidos")

                        //Creamos los SpotImage y los relacionamos con su Spoti id.
                        val imageSpots: List<SpotImage> = listOf(
                            SpotImage(R.mipmap.image_test_1, spots[0].spotId),
                            SpotImage(R.mipmap.image_test_2, spots[0].spotId),
                            SpotImage(R.mipmap.image_test_3, spots[1].spotId),
                            SpotImage(R.mipmap.image_test_4, spots[1].spotId),
                            SpotImage(R.mipmap.image_test_5, spots[2].spotId),
                            SpotImage(R.mipmap.image_test_6, spots[2].spotId),
                            SpotImage(R.mipmap.image_test_7, spots[3].spotId),
                            SpotImage(R.mipmap.image_test_8, spots[3].spotId),
                            SpotImage(R.mipmap.image_test_9, spots[4].spotId),
                            SpotImage(R.mipmap.image_test_10, spots[4].spotId),
                            SpotImage(R.mipmap.image_test_11, spots[5].spotId),
                            SpotImage(R.mipmap.image_test_12, spots[5].spotId),
                            SpotImage(R.mipmap.image_test_13, spots[6].spotId),
                            SpotImage(R.mipmap.image_test_14, spots[6].spotId),
                            SpotImage(R.mipmap.image_test_15, spots[7].spotId),
                            SpotImage(R.mipmap.image_test_16, spots[7].spotId),
                            SpotImage(R.mipmap.image_test_17, spots[8].spotId),
                            SpotImage(R.mipmap.image_test_18, spots[8].spotId),
                            SpotImage(R.mipmap.image_test_19, spots[9].spotId),
                            SpotImage(R.mipmap.image_test_20, spots[9].spotId)
                        )
                        //Añado los SpotImage al DB.
                        App.db?.spotImageDao()?.insertAll(imageSpots)

                        //Creamos los SpotIcons y los relacionamos con su Spot id.
                        val iconSpots: List<SpotIcon> = listOf(
                            SpotIcon(R.drawable.ic_starfish, spots[0].spotId),
                            SpotIcon(R.drawable.ic_anchor, spots[0].spotId),
                            SpotIcon(R.drawable.ic_boya, spots[0].spotId),
                            SpotIcon(R.drawable.ic_consumo_oxigeno, spots[0].spotId),
                            SpotIcon(R.drawable.ic_corriente, spots[0].spotId),
                            SpotIcon(R.drawable.ic_luz_de_buceo, spots[0].spotId),
                            SpotIcon(R.drawable.ic_parada_descompresio, spots[1].spotId),
                            SpotIcon(R.drawable.ic_beach, spots[1].spotId),
                            SpotIcon(R.drawable.ic_rocas, spots[1].spotId),
                            SpotIcon(R.drawable.ic_sand, spots[1].spotId),
                            SpotIcon(R.drawable.ic_starfish, spots[1].spotId),
                            SpotIcon(R.drawable.ic_embarcaciones_frecuentes, spots[1].spotId),
                            SpotIcon(R.drawable.ic_vientos_desfavorables, spots[1].spotId),
                            SpotIcon(R.drawable.ic_prof_max, spots[2].spotId),
                            SpotIcon(R.drawable.ic_posidonia, spots[2].spotId),
                            SpotIcon(R.drawable.ic_nocturna_clean, spots[2].spotId),
                            SpotIcon(R.drawable.ic_corriente, spots[2].spotId),
                            SpotIcon(R.drawable.ic_anchor, spots[2].spotId),
                            SpotIcon(R.drawable.ic_parada_descompresio, spots[2].spotId),
                            SpotIcon(R.drawable.ic_consumo_oxigeno, spots[2].spotId),
                            SpotIcon(R.drawable.ic_posidonia, spots[3].spotId),
                            SpotIcon(R.drawable.ic_embarcaciones_frecuentes, spots[3].spotId),
                            SpotIcon(R.drawable.ic_coral, spots[3].spotId),
                            SpotIcon(R.drawable.ic_corriente, spots[3].spotId),
                            SpotIcon(R.drawable.ic_boya, spots[3].spotId),
                            SpotIcon(R.drawable.ic_starfish, spots[3].spotId),
                            SpotIcon(R.drawable.ic_parada_descompresio, spots[4].spotId),
                            SpotIcon(R.drawable.ic_anchor, spots[4].spotId),
                            SpotIcon(R.drawable.ic_nocturna_clean, spots[4].spotId),
                            SpotIcon(R.drawable.ic_luz_de_buceo, spots[4].spotId),
                            SpotIcon(R.drawable.ic_vientos_desfavorables, spots[4].spotId),
                            SpotIcon(R.drawable.ic_starfish, spots[4].spotId),
                            SpotIcon(R.drawable.ic_beach, spots[5].spotId),
                            SpotIcon(R.drawable.ic_posidonia, spots[5].spotId),
                            SpotIcon(R.drawable.ic_consumo_oxigeno, spots[5].spotId),
                            SpotIcon(R.drawable.ic_coral, spots[5].spotId),
                            SpotIcon(R.drawable.ic_sand, spots[5].spotId),
                            SpotIcon(R.drawable.ic_prof_max, spots[5].spotId)
                        )

                        //Añado los IconSpots al Db.
                        App.db?.spotIconDao()?.insertAll(iconSpots)
                    }
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        //Iniciamos Stettho
        Stetho.initializeWithDefaults(this)
        //Creamos la base de datos.
        getDatabase(this)
    }
}
