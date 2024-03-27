package com.esteban.graficagmail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmailAdapter extends ArrayAdapter<Email> {

    public EmailAdapter(Context context, ArrayList<Email> emails) {
        super(context, 0, emails);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Email email = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_email, parent, false);
        }

        CircleImageView imageViewProfile = convertView.findViewById(R.id.imageViewProfile);
        TextView textViewSender = convertView.findViewById(R.id.textViewSender);
        TextView textViewSubject = convertView.findViewById(R.id.textViewSubject);
        TextView textViewInfo = convertView.findViewById(R.id.textViewInfo); // Asegúrate de que este TextView corresponda al de la información del correo electrónico
        TextView textViewTime = convertView.findViewById(R.id.textViewTime); // Nuevo TextView para mostrar la hora de recepción
        ImageView imageViewCheck = convertView.findViewById(R.id.imageViewCheck); // Icono de check

        // Cambia la visibilidad del icono de check según el estado de lectura del correo electrónico
        if (email.isRead()) {
            imageViewCheck.setVisibility(View.VISIBLE);
        } else {
            imageViewCheck.setVisibility(View.GONE);
        }
        // Configurar la hora de recepción si está disponible
        if (email.getReceivedTime() != null) {
            textViewTime.setVisibility(View.VISIBLE);
            textViewTime.setText(email.getReceivedTime());
        } else {
            textViewTime.setVisibility(View.GONE);
        }

        imageViewProfile.setImageResource(email.getProfileImage());
        textViewSender.setText(email.getSender());
        textViewSubject.setText(email.getSubject());
        textViewInfo.setText(email.getInfo()); // Establece el texto del TextView con la información del correo electrónico
        textViewTime.setText(email.getReceivedTime()); // Establece la hora de recepción

        return convertView;
    }
}
