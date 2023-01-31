package enchere.enchere.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        map.put("data", enchereRepository.findAll());
        return map;
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
