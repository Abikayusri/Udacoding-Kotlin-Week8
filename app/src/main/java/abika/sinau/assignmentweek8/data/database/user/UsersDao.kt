package abika.sinau.assignmentweek8.data.database.user

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * Created by Abika Chairul Yusri on 21/09/2020
 * Bismillahirrahmanirrahim
 */
@Dao
interface UsersDao {
    @Query("SELECT * FROM users")
    fun getAllDataUsers(): LiveData<List<UsersEntity>>

    @Query("SELECT * FROM users WHERE email=:email AND password=:password")
    fun getSelectedDataUsers(email: String, password: String): Boolean

//    fun getSelectedDataUsers(email: String, password: String): LiveData<List<UsersEntity>>
//    fun getSelectedDataUsers(email: String, password: String): Flowable<UsersEntity>
//    fun getSelectedDataUsers(email: String, password: String): Single<UsersEntity>

    @Insert(onConflict = REPLACE)
    fun insertDataUsers(usersEntity: UsersEntity)
}