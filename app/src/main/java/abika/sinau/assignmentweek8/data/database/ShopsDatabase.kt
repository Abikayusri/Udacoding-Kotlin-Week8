package abika.sinau.assignmentweek8.data.database

import abika.sinau.assignmentweek8.data.database.shops.ShopsDao
import abika.sinau.assignmentweek8.data.database.shops.ShopsEntity
import abika.sinau.assignmentweek8.data.database.user.UsersDao
import abika.sinau.assignmentweek8.data.database.user.UsersEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Abika Chairul Yusri on 21/09/2020
 * Bismillahirrahmanirrahim
 */
@Database(entities = [ShopsEntity::class, UsersEntity::class], version = 1)
abstract class ShopsDatabase : RoomDatabase() {
    abstract fun shopsDao(): ShopsDao
    abstract fun usersDao(): UsersDao

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