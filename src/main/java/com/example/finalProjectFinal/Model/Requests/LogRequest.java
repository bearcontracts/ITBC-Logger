package com.example.finalProjectFinal.Model.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogRequest {
    private String message;
    private com.example.finalProjectFinal.Model.Log.logType logType;
}
