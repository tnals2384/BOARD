package org.example.springMybatisBoard.global;

public class AlertException extends RuntimeException {

    private final AlertType alertType;
    public AlertException(AlertType alertType) {
        this.alertType = alertType;
    }

    public AlertType getAlertType() {
        return alertType;
    }
}
