package com.esteban.graficagmail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewEmails;
    private ArrayList<Email> emailList;
    private EmailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar la lista de correos electrónicos
        emailList = new ArrayList<>();
        initializeEmails();

        // Enlazar ListView
        listViewEmails = findViewById(R.id.listViewEmails);

        // Crear y establecer el adaptador
        adapter = new EmailAdapter(this, emailList);
        listViewEmails.setAdapter(adapter);

        // Manejar clics en elementos de la lista
        listViewEmails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleItemClick(position);
            }
        });
    }

    // Inicializa la lista de correos electrónicos
    private void initializeEmails() {
        emailList.add(new Email("Carlos Gómez", R.drawable.persona1, "Actualización del proyecto", "Adjunto encontrarás la última actualización del proyecto para revisión.", "8:00 AM"));
        emailList.add(new Email("David Rodríguez", R.drawable.persona2, "Solicitud de vacaciones", "Quisiera solicitar vacaciones para la próxima semana del 1 al 5 de abril.", "9:15 AM"));
        emailList.add(new Email(" Ana López", R.drawable.persona3, "Recordatorio de pago", "Este es un recordatorio amistoso para realizar el pago de la factura pendiente antes del fin de mes.", "10:30 AM"));
        emailList.add(new Email(" Laura Martínez", R.drawable.persona4, "Felicitaciones por tu cumpleaños", " ¡Feliz cumpleaños! Espero que tengas un día maravilloso lleno de alegría y felicidad.", "11:45 AM"));
        emailList.add(new Email("Mario Pérez:", R.drawable.persona5, " Reunión de equipo", "Recordatorio de la reunión de equipo mañana a las 10:00 a.m.", "1:00 PM"));
        // Agrega más correos electrónicos aquí...
    }

    // Maneja el clic en un elemento de la lista
    private void handleItemClick(int position) {
        // Obtener el correo electrónico seleccionado
        Email selectedEmail = emailList.get(position);
        // Marcar como leído
        selectedEmail.setRead(true);
        // Guardar el estado actualizado en SharedPreferences
        saveReadStatus();
        // Actualizar el adaptador
        adapter.notifyDataSetChanged();
        // Iniciar la actividad de detalle
        Intent intent = new Intent(MainActivity.this, DetalleEmailActivity.class);
        intent.putExtra("email", selectedEmail);
        startActivity(intent);
    }

   
    @Override
    protected void onResume() {
        super.onResume();
        loadReadStatus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveReadStatus();
    }

    // Cargar el estado de lectura de cada correo electrónico desde SharedPreferences
    private void loadReadStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("EmailReadStatus", Context.MODE_PRIVATE);
        for (Email email : emailList) {
            email.setRead(sharedPreferences.getBoolean(email.getSubject(), false));
        }
    }

    // Guardar el estado de lectura de cada correo electrónico en SharedPreferences
    private void saveReadStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("EmailReadStatus", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (Email email : emailList) {
            editor.putBoolean(email.getSubject(), email.isRead());
        }
        editor.apply();
    }

}
