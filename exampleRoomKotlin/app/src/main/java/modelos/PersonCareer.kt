package modelos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import modelos.Career
import modelos.Person

@Entity(tableName = "personCareer",primaryKeys = ["person_id","career_id"],
foreignKeys =[ ForeignKey(entity = Career::class, parentColumns = ["id"], childColumns = ["career_id"]),
    ForeignKey(entity = Person::class, parentColumns = ["person_id"], childColumns = ["person_id"])])
data class PersonCareer(
    @ColumnInfo(name="person_id",index = true)
    val person_id : Int,
    @ColumnInfo(name="career_id",index = true)
    val career_id: Int

)
