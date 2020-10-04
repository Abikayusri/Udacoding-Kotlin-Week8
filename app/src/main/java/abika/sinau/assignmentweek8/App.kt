package abika.sinau.assignmentweek8

import abika.sinau.assignmentweek8.di.module.databaseModule
import abika.sinau.assignmentweek8.di.module.repositoryModule
import abika.sinau.assignmentweek8.di.module.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Abika Chairul Yusri on 04/10/2020
 * Bismillahirrahmanirrahim
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                viewModelModule,
                repositoryModule,
                databaseModule
            )
        }
    }
}