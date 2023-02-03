package enchere.enchere.controller;

import enchere.enchere.service.SignalService;
import enchere.enchere.model.Notification;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/notif")
public class NotificationController {

    @PostMapping()
    public String notif() throws Exception {
        String requestBody = "{\"app_id\":\"f43c166c-4351-40de-8114-d992abf563f4\",\"included_segments\": [\"Subscribed Users\"],\"contents\": {\"en\": \"Ceci est une notification qui marche \"},\"name\": \"INTERNAL_CAMPAIGN_NAME\"}";

        return new SignalService().callWS(requestBody);
    }
}
