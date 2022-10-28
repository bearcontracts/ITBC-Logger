package com.example.finalProjectFinal.Model.Requests;

import com.example.finalProjectFinal.Model.Log.Log;
import com.example.finalProjectFinal.Model.Log.logType;
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
