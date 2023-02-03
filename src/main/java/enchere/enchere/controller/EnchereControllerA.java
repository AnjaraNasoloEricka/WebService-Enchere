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
import enchere.enchere.model.EnchereA;
import enchere.enchere.model.ImageEnchereA;

@RestController
@CrossOrigin("*")
@RequestMapping("/enchere")
public class EnchereControllerA {
    @PostMapping
    public ResponseEntity<?> insertEnchere(@RequestBody EnchereA ctg) throws SQLException {
        Map<String, Object> mymap = new HashMap<String, Object>();
        try {
            ctg.insertEnchere(ctg);
            mymap.put("success", new ErrorJson(200, "Enchere bien ajoutee"));

        } catch (Exception ex) {
            mymap.put("erreur", new ErrorJson(500, ex.getMessage()));
        }
        return new ResponseEntity<>(mymap, HttpStatus.OK);
    }
}
