package enchere.enchere.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enchere.enchere.exception.ErrorJson;
import enchere.enchere.model.SoldeUtilisateurA;

@RestController
@CrossOrigin("*")
@RequestMapping("/soldeUtilisateur")
public class SoldeUtilisateurControllerA {
    @PostMapping
    public ResponseEntity<?> insertEnchere(@RequestBody SoldeUtilisateurA ctg) throws SQLException {
        Map<String, Object> mymap = new HashMap<String, Object>();
        try {
            SoldeUtilisateurA.insertSoldeUtilisateurA(ctg);
            mymap.put("success", new ErrorJson(200, "Votre demande d'ajout de solde a bien reussie"));
        } catch (Exception ex) {
            mymap.put("erreur", new ErrorJson(500, "Une erreur s'est produite lors de votre demande"));
        }
        return new ResponseEntity<>(mymap, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getSumSoldeUser(@PathVariable int id) {
        Map<String, Object> mymap = new HashMap<String, Object>();
        try {
            mymap.put("data", new Double(SoldeUtilisateurA.mySoldeUser(id)));
        } catch (Exception e) {
            mymap.put("erreur", new ErrorJson(500, e.getMessage()));
        }
        return mymap;
    }
}
