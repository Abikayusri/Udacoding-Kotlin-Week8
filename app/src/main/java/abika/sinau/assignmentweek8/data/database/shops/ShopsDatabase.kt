package abika.sinau.assignmentweek8.data.database.shops

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Abika Chairul Yusri on 21/09/2020
 * Bismillahirrahmanirrahim
 */
@Database(entities = [ShopsEntity::class], version = 1)
abstract class ShopsDatabase : RoomDatabase() {
    abstract fun shopsDao(): ShopsDao

    companion object {
        private var instance: ShopsDatabase? = null
        fun getInstanceShops(context: Context): ShopsDatabase? {
            if (instance == null) {
                synchronized(ShopsDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShopsDatabase::class.java,
                        "dbshops.db"
                    )
                        .build()
                }
            }
            return instance
        }
    }
}