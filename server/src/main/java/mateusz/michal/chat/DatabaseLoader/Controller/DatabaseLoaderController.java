package mateusz.michal.chat.DatabaseLoader.Controller;

import mateusz.michal.chat.DatabaseLoader.Service.DatabaseLoaderService;
import mateusz.michal.chat.Structure.RespondStructure.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DatabaseLoaderController {

    @Autowired
    DatabaseLoaderService databaseLoaderService;

    @GetMapping("/api/populate")
    public ResponseEntity<IJsonResponse> populate(){
        List<MyError> errors = databaseLoaderService.populateDatabase();
        if(errors.size() == 0) {
            return ResponseEntity.ok(
                    JsonResponseFactory.createResponse(new SimpleDataResponse("data has been added into database"))
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResponseFactory.createResponse(errors));
    }
}
