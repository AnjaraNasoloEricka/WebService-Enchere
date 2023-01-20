package enchere.enchere.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import enchere.enchere.model.Enchere;
import enchere.enchere.repository.EnchereRepository;

@Service
public class ServiceEnchere {

    @Autowired
    private EnchereRepository eRepository;

    @Scheduled(fixedRate = 1000)
    public void checkFinEnchere() {

        /*
         * List<Enchere> lsenchere = eRepository.findNotFinished();
         * for (Enchere enchere : lsenchere) {
         * try {
         * enchere.setFini(eRepository);
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         * }
         */

    }
}
