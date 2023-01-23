package modelos

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "career")
data class Career(
    @PrimaryKey
    @ColumnInfo(name="id")
    val id:Int,
    @ColumnInfo(name="career_name")
    val name: String,
    @ColumnInfo(name="description")
    val description: String
)
