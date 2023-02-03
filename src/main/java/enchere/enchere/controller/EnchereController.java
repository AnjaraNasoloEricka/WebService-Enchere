package enchere.enchere.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enchere.enchere.repository.EnchereRepository;
import enchere.enchere.model.*;

@RestController
@RequestMapping("/enchere")
public class EnchereController {

    @Autowired
    private EnchereRepository enchereRepository;

    @GetMapping("")
    public Map<String, Object> getAllEnchere() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Enchere> ls = enchereRepository.findAll();
            map.put("data", ls);
        } catch (Exception ex) {
            map.put("erreur", ex.getMessage());
        }

        return map;
    }

    @GetMapping("encherea/{id}")
    public List<ImageEnchereA> getAllImageEnchere(@PathVariable int id) {
        Enchere e = new Enchere();
        e.setId(new Long(id));
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            return e.initAllImageEnchere();
            // map.put("data", e.initAllImageEnchere());
        } catch (Exception ex) {
            ex.printStackTrace();
            // map.put("erreur", ex.getMessage());
        }
        return null;
    }

    @PostMapping("/search")
    public Map<String, Object> recherche(@RequestBody RechercheEnchere recherche) {
        Enchere e = new Enchere();
        String condition = (e.getSearchSQL(recherche));
        List<Enchere> ls = e.getAllEnchere(condition);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", ls);
        return map;
    }

}
