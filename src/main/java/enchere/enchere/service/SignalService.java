package enchere.enchere.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.apache.http.client.HttpClient;

import com.google.common.net.MediaType;

public class SignalService {

    public String callWS(String requestBody) throws Exception {
        URL url = new URL("https://onesignal.com/api/v1/notifications");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic MjA4ZTU5Y2YtMzQ3Ny00N2MwLTljZTMtYjZlMWNhNjk5Yjg4");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            if (requestBody != null) {
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(requestBody);
                out.flush();
                out.close();
                out.writeBytes(requestBody);
            }

            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
            return output;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in NetClientGet:- " + e);
            return conn.getResponseMessage();
        }

    }

}
