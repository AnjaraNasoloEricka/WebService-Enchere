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
import enchere.enchere.model.ImageEnchereA;

@RestController
@CrossOrigin("*")
@RequestMapping("/imageEnchere")
public class ImageEnchereControllerA {
    @PostMapping
    public Map<String, Object> insertImageEnchereA(@RequestBody ImageEnchereA ctg) throws SQLException {
        Map<String, Object> mymap = new HashMap<String, Object>();
        try {
            ImageEnchereA.insertImageEnchereA(ctg);
            mymap.put("success", new ErrorJson(200, "Image inseree"));
        } catch (Exception ex) {
            mymap.put("erreur", new ErrorJson(500, "Une erreur s'est produite lors de l'insertion de l'image"));
        }
        return mymap;
    }
}
