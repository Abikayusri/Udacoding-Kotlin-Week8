package abika.sinau.assignmentweek8.data.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

/**
 * Created by Abika Chairul Yusri on 21/09/2020
 * Bismillahirrahmanirrahim
 */
@Dao
interface UsersDao {
    @Query("SELECT * FROM users")
    fun getAllDataUsers(): List<UsersEntity>

    @Query("SELECT COUNT(id) AS status FROM users WHERE email=:email AND password=:password")
    fun getSelectedDataUsers(email: String, password: String): Int

    @Query("SELECT COUNT(id) AS status FROM users WHERE username=:name OR email=:email")
    fun  getSelectedUsers(name: String, email: String): Int

    @Insert
    fun insertDataUsers(usersEntity: UsersEntity)
}