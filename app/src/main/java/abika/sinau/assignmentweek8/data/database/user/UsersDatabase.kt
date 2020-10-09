package abika.sinau.assignmentweek8.data.database.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Abika Chairul Yusri on 21/09/2020
 * Bismillahirrahmanirrahim
 */
//@Database(entities = [UsersEntity::class], version = 1)
//abstract class UsersDatabase : RoomDatabase() {
//    abstract fun usersDao(): UsersDao
//
//    companion object {
//        private var instance: UsersDatabase? = null
//        fun getInstanceUsers(context: Context): UsersDatabase? {
//            if (instance == null) {
//                synchronized(UsersDatabase::class) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        UsersDatabase::class.java,
//                        "dbusers.db"
//                    )
//                        .build()
//                }
//            }
//            return instance
//        }
//    }
//}