package com.example.probargestionrutasfirebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private CollectionReference collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        collectionReference = db.collection("Lugar");

        // Crear un objeto de tipo "Lugar" con los datos que deseas agregar
        Lugar lugar = new Lugar("Nombre de prueba", "Descripción de prueba");

        // Utilizar el método "add()" para agregar los datos a la colección en Firestore
       //agregarLugar(lugar);

        actualizarLugarPorNombre("Nombre de prueba", "Nombre de prueba2", "Pepe grillo");
        // Ejemplo de cómo llamar a las otras funciones
        //eliminarLugarPorNombre("Nombre de prueba");
         //eliminarLugar("id_del_lugar_a_eliminar");
        // actualizarLugar("id_del_lugar_a_actualizar", "Nuevo nombre", "Nueva descripción");
        // consultarLugar("id_del_lugar_a_consultar");
    }

    private void agregarLugar(Lugar lugar) {
        collectionReference.add(lugar)
                .addOnSuccessListener(documentReference -> {
                    Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Log.w("TAG", "Error adding document", e);
                });
    }

    private void eliminarLugarPorNombre(String nombre) {
        // Obtener una referencia a la colección "Lugar" en la base de datos
        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Lugar");

        // Crear una consulta para buscar el documento por el nombre del lugar
        Query query = collectionReference.whereEqualTo("nombre", nombre);

        // Ejecutar la consulta y eliminar el documento si se encuentra
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    document.getReference().delete()
                            .addOnSuccessListener(aVoid -> {
                                Log.d("TAG", "DocumentSnapshot successfully deleted!");
                            })
                            .addOnFailureListener(e -> {
                                Log.w("TAG", "Error deleting document", e);
                            });
                }
            } else {
                Log.d("TAG", "Error getting documents: ", task.getException());
            }
        });
    }


    private void eliminarLugar(String id) {
        collectionReference.document(id)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Log.d("TAG", "DocumentSnapshot successfully deleted!");
                })
                .addOnFailureListener(e -> {
                    Log.w("TAG", "Error deleting document", e);
                });
    }

    private void actualizarLugar(String id, String nombre, String descripcion) {
        collectionReference.document(id)
                .update(
                        "nombre", nombre,
                        "descripcion", descripcion
                )
                .addOnSuccessListener(aVoid -> {
                    Log.d("TAG", "DocumentSnapshot successfully updated!");
                })
                .addOnFailureListener(e -> {
                    Log.w("TAG", "Error updating document", e);
                });
    }

    private void actualizarLugarPorNombre(String nombreABuscar, String nuevoNombre, String nuevaDescripcion) {
        collectionReference
                .whereEqualTo("nombre", nombreABuscar)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        documentSnapshot.getReference().update("nombre", nuevoNombre, "descripcion", nuevaDescripcion)
                                .addOnSuccessListener(aVoid -> Log.d("TAG", "DocumentSnapshot successfully updated!"))
                                .addOnFailureListener(e -> Log.w("TAG", "Error updating document", e));
                    }
                })
                .addOnFailureListener(e -> Log.w("TAG", "Error getting documents.", e));
    }



    private void consultarLugarPorNombre(String nombre) {
        // Obtener una referencia a la colección "Lugar" en la base de datos
        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Lugar");

        // Crear una consulta para buscar el documento por el nombre del lugar
        Query query = collectionReference.whereEqualTo("nombre", nombre);

        // Ejecutar la consulta y mostrar los documentos encontrados
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d("TAG", document.getId() + " => " + document.getData());
                }
            } else {
                Log.d("TAG", "Error getting documents: ", task.getException());
            }
        });
    }


    private void consultarLugar(String id) {
        collectionReference.document(id)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Lugar lugar = documentSnapshot.toObject(Lugar.class);
                        Log.d("TAG", "DocumentSnapshot data: " + lugar);
                    } else {
                        Log.d("TAG", "No such document");
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("TAG", "Error getting document", e);
                });
    }
}


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Obtener una referencia a la colección "Lugar" en la base de datos
        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Lugar");

        // Crear un objeto de tipo "Lugar" con los datos que deseas agregar
        Lugar lugar = new Lugar("Nombre de prueba", "Descripción de prueba");

        // Utilizar el método "add()" para agregar los datos a la colección en Firestore
        collectionReference.add(lugar).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("TAG", "Error adding document", e);
            }
        });




        /*Map<String, Object> lugar = new HashMap<>();
        lugar.put("test", "Los Angeles");
        lugar.put("2", "CA");
        lugar.put("3", "USA");

        db.collection("Lugar").document("LU")
                .set(lugar)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

        //leer
        db.collection("users")
                .add(lugar)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }

    }
}*/