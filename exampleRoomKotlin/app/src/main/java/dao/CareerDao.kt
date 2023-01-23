package dao

import androidx.room.*
import modelos.Career

@Dao
interface CareerDao {
    @Query("SELECT * FROM Career")
     fun getAll(): List<Career>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(career: Career)
/*
@Query("SELECT * from user WHERE region IN (:regions)")
suspend fun loadUsersByRegion(regions: List<String>): List<User>
*/
}
