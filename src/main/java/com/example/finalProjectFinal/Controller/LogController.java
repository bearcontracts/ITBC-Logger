package com.example.finalProjectFinal.Controller;

import com.example.finalProjectFinal.Model.Log.Log;
import com.example.finalProjectFinal.Model.Requests.LogRequest;
import com.example.finalProjectFinal.Repository.LogRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class LogController {
    private com.example.finalProjectFinal.Token.tokenInitializer tokenInitializer;
    private LogRepository logRepository;

    @PostMapping(path = "api/logs/create")
    public ResponseEntity<String> createLog(@RequestHeader UUID token, @RequestBody LogRequest logRequest) {
        Log log = new Log();
        if (!(tokenInitializer.isTokenValid(token))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token is invalid");
        }

        if(logRequest.getMessage().length()>1024){
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Your message is too large");
        }
        log.setId(UUID.randomUUID());
        log.setMessage(logRequest.getMessage());
        log.setLocalDate(LocalDate.now());
        log.setLogType(logRequest.getLogType());


        logRepository.save(log);
        return new ResponseEntity<String>("Log created successful", HttpStatus.OK);
    }
}
