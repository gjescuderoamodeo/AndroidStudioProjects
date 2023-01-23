package AppDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dao.CareerDao
import dao.PersonCarrerDao
import dao.PersonDao
import modelos.*

@Database( entities= [Person::class, Career::class, PersonCareer::class],  version =4, exportSchema = false)

abstract class PeopleDb : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun carerrDao(): CareerDao
    abstract fun personCareerDao(): PersonCarrerDao

    companion object{
        @Volatile
         private var INSTANCE :PeopleDb? = null
         fun getDatabase(context : Context) : PeopleDb{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PeopleDb::class.java,
                    "prueba2db"
                )
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3,MIGRATION_3_4)
                    .build()
                INSTANCE = instance
                return instance
            }
//.addMigrations(MIGRATION_1_2, MIGRATION_2_3,MIGRATION_3_4)
        }
          val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `person` (" +
                        "`nombre` TEXT NOT NULL, " +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")

            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `career` (" +
                        "`descripcion` TEXT NOT NULL, " +
                        "`career_name` TEXT, " +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")



            }
        }
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `personCareer` (" +
                        "`person_id` INTEGER," +
                        "`career_id` INTEGER ," +
                        "PRIMARY KEY(`person_id`,`career_nombre`),"+
                        "FOREIGN KEY(`career_id`) REFERENCES `Career`(`id`),"+
                        "FOREIGN KEY(`person_id`) REFERENCES `Person`(`id`) ON DELETE CASCADE "+
                        ")")

            }
        }

    }

}

//package AppDataBase

//import androidx.room.Embedded
//import androidx.room.Relation
//import modelos.Person

/*data class PersonWithCareer(
    @Embedded val Ca: Person,
    @Relation(
        parentColumn = "nombre",
        entityColumn = "career"
    )
    val persons:List<Person>
)*/
