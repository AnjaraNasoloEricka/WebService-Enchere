package enchere.enchere.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enchere.enchere.repository.EnchereRepository;
import enchere.enchere.repository.HistoriqueEnchereRepository;
import enchere.enchere.retour.DataRetour;
import enchere.enchere.exception.ErrorJson;
import enchere.enchere.model.*;

@RestController
@RequestMapping("/historique")
@EnableMongoRepositories
public class HistoriqueEnchereController {

    @Autowired
    private EnchereRepository enchereRepository;

    @Autowired
    private HistoriqueEnchereRepository hRepository;

    @GetMapping("/{iduser}")
    public Map<String, Object> getAllHistoriqueEnchere(@PathVariable int iduser) {
        Map<String, Object> map = new HashMap();
        List<HistoriqueEnchere> all = hRepository.findAll();
        List<HistoriqueEnchere> ls = new HistoriqueEnchere().historique(enchereRepository, iduser, all);
        map.put("data", ls);
        return map;
    }

    @PostMapping
    public ResponseEntity<?> insertHistoriqueEnchere(@RequestBody HistoriqueEnchere h) {
        Map<String, Object> mymap = new HashMap<String, Object>();
        try {
            h.checkEnchereProperty(enchereRepository);
            hRepository.insert(h);
            Enchere ench = h.getEnchere(enchereRepository);
            h.updateEnchereProperty(ench);
            enchereRepository.save(ench);
            mymap.put("success", new ErrorJson(200, "Valeur bien enregistree"));
            return new ResponseEntity(mymap, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            mymap.put("erreur", new ErrorJson(500, e.getMessage()));
            return new ResponseEntity(mymap, HttpStatus.BAD_REQUEST);
        }
    }

}
