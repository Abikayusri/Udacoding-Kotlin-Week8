package abika.sinau.assignmentweek8.di.module

import abika.sinau.assignmentweek8.data.database.shops.ShopsDao
import abika.sinau.assignmentweek8.data.database.shops.ShopsDatabase
import abika.sinau.assignmentweek8.data.database.user.UsersDao
import abika.sinau.assignmentweek8.data.database.user.UsersDatabase
import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Abika Chairul Yusri on 04/10/2020
 * Bismillahirrahmanirrahim
 */

val databaseModule = module {
    fun provideUsersDatabase(application: Application) : UsersDatabase {
        return Room.databaseBuilder(application, UsersDatabase::class.java, "Users")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideUsersDao(database: UsersDatabase): UsersDao {
        return database.usersDao()
    }

    fun provideShopsDatabase(application: Application) : ShopsDatabase {
        return Room.databaseBuilder(application, ShopsDatabase::class.java, "shops")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideShopsDao(database: ShopsDatabase): ShopsDao{
        return database.shopsDao()
    }

    single { provideShopsDatabase(androidApplication()) }
    single { provideShopsDao(get()) }
    single { provideUsersDatabase(androidApplication()) }
    single { provideUsersDao(get()) }
}