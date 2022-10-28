package com.example.finalProjectFinal.Model.Log;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true)
    private UUID id;
    @Column(name="message",nullable = false)
    private String message;
    @Column(name = "logType")
    @Enumerated(EnumType.STRING)
    private logType logType;
    @Column(name = "localDate",nullable = false)
    private LocalDate localDate;

    public Log(String message, com.example.finalProjectFinal.Model.Log.logType logType, LocalDate localDate) {
        this.message = message;
        this.logType = logType;
        this.localDate = localDate;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public com.example.finalProjectFinal.Model.Log.logType getLogType() {
        return logType;
    }

    public void setLogType(com.example.finalProjectFinal.Model.Log.logType logType) {
        this.logType = logType;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", logType=" + logType +
                ", localDate=" + localDate +
                '}';
    }
}
