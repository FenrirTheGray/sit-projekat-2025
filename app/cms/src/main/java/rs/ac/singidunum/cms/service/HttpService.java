package rs.ac.singidunum.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class HttpService {

    @Autowired
    private UserStoreService userStoreService;

    public final String BASE_URL = System.getenv().getOrDefault("API_BASE_URL", "http://localhost:7999");
    public final String API_BASE_URL = BASE_URL + "/api";
    public final String AUTH_BASE_URL = BASE_URL + "/auth";

    private final RestClient apiClient;

    public HttpService(){
        apiClient = RestClient.create();
    }

    public <T> T get(String endpoint, Class<T> responseType, boolean authorize) {
        try {
            var requestSpec = apiClient
                    .get()
                    .uri(endpoint);

            String token = userStoreService.getToken();
            if (authorize && token != null && !token.isEmpty()) requestSpec.header("Authorization", "Bearer " + userStoreService.getToken());

            return requestSpec
                    .retrieve()
                    .body(responseType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T get(String endpoint, ParameterizedTypeReference<T> responseType, boolean authorize) {
        try {
            var requestSpec = apiClient
                    .get()
                    .uri(endpoint);

            String token = userStoreService.getToken();
            if (authorize && token != null && !token.isEmpty()) requestSpec.header("Authorization", "Bearer " + userStoreService.getToken());

            return requestSpec
                    .retrieve()
                    .body(responseType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T get(String endpoint, ParameterizedTypeReference<T> responseType){
        return get(endpoint, responseType, true);
    }
    public <T> T get(String endpoint, Class<T> responseType){
        return get(endpoint, responseType, true);
    }

    public <T, S> S post(String endpoint, T sendObject, ParameterizedTypeReference<S> responseType, boolean authorize) {

        try {
            var requestSpec = apiClient
                    .post()
                    .uri(endpoint);

            String token = userStoreService.getToken();
            if (authorize && token != null && !token.isEmpty()) requestSpec.header("Authorization", "Bearer " + userStoreService.getToken());
            ResponseEntity<S> response = requestSpec
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(sendObject)
                    .retrieve()
                    .toEntity(responseType);

            System.out.println(response.getStatusCode());
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T, S> S post(String endpoint, T sendObject, Class<S> responseType, boolean authorize) {

        try {
            var requestSpec = apiClient
                    .post()
                    .uri(endpoint);

            String token = userStoreService.getToken();
            if (authorize && token != null && !token.isEmpty()) requestSpec.header("Authorization", "Bearer " + userStoreService.getToken());
            ResponseEntity<S> response = requestSpec
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(sendObject)
                    .retrieve()
                    .toEntity(responseType);

            System.out.println(response.getStatusCode());
            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T, S> S post(String endpoint, T sendObject, Class<S> responseType) {
        return post(endpoint, sendObject, responseType, true);
    }

    public <T, S> S post(String endpoint, T sendObject, ParameterizedTypeReference<S> responseType) {
        return post(endpoint, sendObject, responseType, true);
    }

    public <T, S> S put(String endpoint, String id, T sendObject, Class<S> responseType, boolean authorize) {
        try {
            String uri = endpoint + "/" + id;
            var requestSpec = apiClient.put().uri(uri);

            String token = userStoreService.getToken();
            if (authorize && token != null && !token.isEmpty()) {
                requestSpec.header("Authorization", "Bearer " + token);
            }

            ResponseEntity<S> response = requestSpec
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(sendObject)
                    .retrieve()
                    .toEntity(responseType);

            System.out.println(response.getStatusCode());
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T, S> S put(String endpoint, String id, T sendObject, Class<S> responseType) {
        return put(endpoint, id, sendObject, responseType, true);
    }

    public <T, S> S put(String endpoint, String id, T sendObject, ParameterizedTypeReference<S> responseType, boolean authorize) {
        try {
            String uri = endpoint + "/" + id;
            var requestSpec = apiClient.put().uri(uri);

            String token = userStoreService.getToken();
            if (authorize && token != null && !token.isEmpty()) {
                requestSpec.header("Authorization", "Bearer " + token);
            }

            ResponseEntity<S> response = requestSpec
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(sendObject)
                    .retrieve()
                    .toEntity(responseType);

            System.out.println(response.getStatusCode());
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T, S> S put(String endpoint, String id, T sendObject, ParameterizedTypeReference<S> responseType) {
        return put(endpoint, id, sendObject, responseType, true);
    }

    public void delete(String endpoint, String id, boolean authorize) {
        try {
            String uri = endpoint + "/" + id;
            var requestSpec = apiClient.delete().uri(uri);

            String token = userStoreService.getToken();
            if (authorize && token != null && !token.isEmpty()) {
                requestSpec.header("Authorization", "Bearer " + token);
            }

            ResponseEntity<Void> response = requestSpec.retrieve().toEntity(Void.class);
            System.out.println(response.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Delete failed: " + e.getMessage(), e);
        }
    }

    public void delete(String endpoint, String id) {
        delete(endpoint, id, true);
    }
}
