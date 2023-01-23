package dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import modelos.*

@Dao
interface PersonCarrerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(professorLanguage:PersonCareer);
    @Query("SELECT * FROM person,career INNER JOIN personCareer ON person.person_id=personCareer.person_id WHERE career.id= personCareer.career_id AND career_name = :career_nombre")
            fun getProfessorForRepository(career_nombre: String): List<Person>

}