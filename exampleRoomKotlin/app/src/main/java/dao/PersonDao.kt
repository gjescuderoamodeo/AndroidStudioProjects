package dao

import androidx.lifecycle.LiveData
import androidx.room.*
import modelos.Career
import modelos.Person
import java.util.concurrent.Flow

@Dao
interface PersonDao {

    @Query("SELECT * FROM Person")
      fun getAllPerson():List<Person>

     @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(person:Person)

    /* @Transaction
     @Query("SELECT * FROM Person ")
     fun getPersonWithCarrer():List<PersonWithCareer>*/

}