package com.esteban.graficagmail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_email);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("email")) {
            // Obtener el objeto Email enviado desde MainActivity
            Email email = (Email) intent.getSerializableExtra("email");

            // Obtener referencias a las vistas en el layout
            de.hdodenhof.circleimageview.CircleImageView imageViewProfile = findViewById(R.id.imageViewProfile);
            TextView textViewSender = findViewById(R.id.textViewSender);
            TextView textViewSubject = findViewById(R.id.textViewSubject);
            TextView textViewInfo = findViewById(R.id.textViewInfo);

            // Establecer los datos en las vistas
            assert email != null;
            imageViewProfile.setImageResource(email.getProfileImage());
            textViewSender.setText(email.getSender());
            textViewSubject.setText(email.getSubject());
            textViewInfo.setText(email.getInfo()); // Establecer el texto con la información del correo electrónico
        }
    }
}
