package com.example.exampleroomkotlin

import AppDataBase.PeopleDb
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import modelos.*

class MainActivity : AppCompatActivity() {


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Context of MainActivity to BD  with "this"
        val app = PeopleDb.getDatabase(this)
        /*Room.databaseBuilder(this, PeopleDb::class.java, "persona2").allowMainThreadQueries()
            .build()*/

        //DAO
        val daoCareer = app.carerrDao()
        val daoPerson = app.personDao()
        val personCareerDao =app.personCareerDao()

        // var listCareer : MutableList<Career> = mutableListOf()

        //Creacion de CARRERA
       var prof =Career(1,"Baloncesto","Baloncesto es genial")
       var prof2 =Career(2,"Futbol","Futbol es genial")

       //Creacion de Persona
       var person2 =Person(1,"pepe")
       var person1 =Person(2,"juan")

       //CREACION PERSONA CON CARRERA
       var personCareer1= PersonCareer(1,1)
       var personCareer2= PersonCareer(2,1)


        // var carreras= listOf(prof,prof2)
        //var personas=listOf(person1,person2)
        // listCareer.addAll(carreras)

        Log.d("Inicia","Hola")

        //CONEXION A LOS HILOS
        lifecycleScope.launch {

            //val listadoPersona = app.room.personDao().getAll();
            //Log.d("","Oncreate:${listadoPersona.size}")
            // daoCareer.insert(carreras[0])
            //daoPerson.insertPerson(personas[0])

            //INSERT CAREER
            daoCareer.insert(prof)
            daoCareer.insert(prof2)

            //INSERT PERSON
            daoPerson.insertPerson(person2)
            daoPerson.insertPerson(person1)

            //VER TODOS LOS DATOS DE PERSONAS
            for (item in daoPerson.getAllPerson()){
                Log.d("Personas",item.nombre)
            }

            personCareerDao.insert(personCareer1)
            personCareerDao.insert(personCareer2)

            //VER TODAS LAS PERSONAS CON CAREER BALONCESTO
            for (item in personCareerDao.getProfessorForRepository("Baloncesto")){
                Log.d("PersonCarrer",item.nombre)
            }
        }


        }

    }
