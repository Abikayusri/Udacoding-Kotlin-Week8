package abika.sinau.assignmentweek8.repository.users

import abika.sinau.assignmentweek8.data.database.user.UsersEntity

/**
 * Created by Abika Chairul Yusri on 04/10/2020
 * Bismillahirrahmanirrahim
 */
interface RepositoryLocalUser {
    fun addUsers(user: UsersEntity, responseHandler: (Boolean?) -> Unit, errorHandler: (Throwable) -> Unit)
    fun getLoginUsersDatabase( email: String, password: String, responseHandler: (Boolean?) -> Unit, errorHandler: (Throwable) -> Unit)
}