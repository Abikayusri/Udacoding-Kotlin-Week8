package abika.sinau.assignmentweek8.data.database.shops

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

/**
 * Created by Abika Chairul Yusri on 21/09/2020
 * Bismillahirrahmanirrahim
 */
@Dao
interface ShopsDao {
    @Query("SELECT * FROM shops ORDER BY date ASC")
    fun getAllDataShops(): List<ShopsEntity>

    @Insert(onConflict = REPLACE)
    fun insertDataShops(shopsEntity: ShopsEntity)

    @Update
    fun updateDataShops(shopsEntity: ShopsEntity)

    @Delete
    fun deleteDataShops(shopsEntity: ShopsEntity)
}