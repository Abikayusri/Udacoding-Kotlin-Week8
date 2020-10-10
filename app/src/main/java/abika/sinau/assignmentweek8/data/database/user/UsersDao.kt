package abika.sinau.assignmentweek8.data.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Abika Chairul Yusri on 21/09/2020
 * Bismillahirrahmanirrahim
 */
@Dao
interface UsersDao {
    @Query("SELECT * FROM users")
//    fun getAllDataUsers(): LiveData<List<UsersEntity>>
    fun getAllDataUsers(): List<UsersEntity>

    //    @Query("SELECT * FROM users WHERE email=:email AND password=:password")
    // count berguna untuk menghitung baris, kalo kita select count
    // AS status bisa diganti
    @Query("SELECT COUNT(id) AS status FROM users WHERE email=:email AND password=:password")
    fun getSelectedDataUsers(email: String, password: String): Int


    //    @Insert(onConflict = REPLACE)
    @Insert
    fun insertDataUsers(usersEntity: UsersEntity)
}