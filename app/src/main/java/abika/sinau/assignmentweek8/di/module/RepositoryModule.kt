package abika.sinau.assignmentweek8.di.module

import abika.sinau.assignmentweek8.data.database.user.UsersDao
import abika.sinau.assignmentweek8.repository.users.RepositoryLocalUser
import abika.sinau.assignmentweek8.repository.users.RepositoryLocalUsersImpl
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Abika Chairul Yusri on 04/10/2020
 * Bismillahirrahmanirrahim
 */

val repositoryModule = module {

    fun provideUsersRepository(context: Context, dao: UsersDao): RepositoryLocalUser{
        return RepositoryLocalUsersImpl(context, dao)
    }

    single { provideUsersRepository(androidContext(), get()) }
}