package enchere.enchere.controller;

import enchere.enchere.model.*;

public class EnchereNotification {
    private Enchere enchere;
    private String notifString;

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    public EnchereNotification(Enchere enchere) {
        this.enchere = enchere;
    }

    public String getNotifString() {
        return notifString;
    }

    public void setNotifString(String notifString) {
        this.notifString = notifString;
    }

    public void initNotificationEnchere(){
        
    }

}
