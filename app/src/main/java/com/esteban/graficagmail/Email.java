package com.esteban.graficagmail;

import java.io.Serializable;
import java.util.Objects;

public class Email implements Serializable {
    private String sender;
    private int profileImage;
    private String subject;
    private String info;
    private String receivedTime; // Nuevo campo para la hora de recepción
    private boolean isRead;

    public Email(String sender, int profileImage, String subject, String info) {
        this.sender = sender;
        this.profileImage = profileImage;
        this.subject = subject;
        this.info = info;
        this.isRead = false;
    }

    // Constructor adicional para aceptar la hora de recepción
    public Email(String sender, int profileImage, String subject, String info, String receivedTime) {
        this.sender = sender;
        this.profileImage = profileImage;
        this.subject = subject;
        this.info = info;
        this.receivedTime = receivedTime;
        this.isRead = false;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    // Sobrescribe equals y hashCode para comparar objetos Email correctamente
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return sender.equals(email.sender) &&
                subject.equals(email.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, subject);
    }

    public String getSubject() {
        return subject;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public String getSender() {
        return sender;
    }

    public String getInfo() {
        return info;
    }

    // Getter para la hora de recepción
    public String getReceivedTime() {
        return receivedTime;
    }
}
