package enchere.enchere.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enchere.enchere.exception.ErrorJson;
import enchere.enchere.model.UtilisateurA;

@RestController
@CrossOrigin("*")
@RequestMapping("/utilisateura")

public class UtilisateurControllerA {
    @PostMapping
    public ResponseEntity<?> insertUtilisateur(@RequestBody UtilisateurA ctg) throws SQLException {
        Map<String, Object> mymap = new HashMap<String, Object>();
        try {
            UtilisateurA.insertUtilisateur(ctg);
            mymap.put("success", new ErrorJson(200, "Inscription reussie"));
        } catch (Exception ex) {
            mymap.put("erreur", new ErrorJson(500, "Une erreur s'est produite lors de l'inscription"));
        }
        return new ResponseEntity<>(mymap, HttpStatus.OK);
    }
}
