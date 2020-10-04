package abika.sinau.assignmentweek8.data.database.shops

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Abika Chairul Yusri on 21/09/2020
 * Bismillahirrahmanirrahim
 */
@Entity(tableName = "shops")
data class ShopsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String? =null,

    @ColumnInfo(name = "note")
    var note: String? =null,

    @ColumnInfo(name = "date")
    var date: String? = null
)