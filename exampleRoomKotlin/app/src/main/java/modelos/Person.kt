package modelos

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="person_id")
    val person_id: Int,
    @ColumnInfo(name="nombre")
    val nombre: String,

)


