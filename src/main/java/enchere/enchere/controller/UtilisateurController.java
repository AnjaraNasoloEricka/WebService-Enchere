package enchere.enchere.controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enchere.enchere.connexion.Connexion;
import enchere.enchere.exception.ErrorJson;
import enchere.enchere.model.TokenUtilisateur;
import enchere.enchere.model.Utilisateur;
import enchere.enchere.repository.UtilisateurRepository;
import enchere.enchere.retour.*;;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
    @Autowired
    private UtilisateurRepository uRepository;

    @GetMapping
    public Map<String, Object> logins(@RequestBody Utilisateur utilisateur) {
        Map<String, Object> map = new HashMap<String, Object>();
        Utilisateur u = uRepository.login(utilisateur);
        if (u != null) {
            TokenUtilisateur token = new TokenUtilisateur();
            try {
                Connection co = new Connexion().getConnection();
                token.generateToken(co, (int) u.getId());
                u.setToken(token);
                map.put("data", u);
            } catch (Exception e) {
                map.put("erreur", new ErrorJson(500, "Informations non valides"));
            }
        } else {
            map.put("erreur", new ErrorJson(500, "Informations non valides"));
        }
        return map;
    }

}
