package restclient.service;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import restclient.DTO.User;

import java.util.List;

@Component
public class Communicator {

    private final RestTemplate restTemplate;

    private String url = "http://94.198.50.185:7081/api/users";

    private String cookies;

    public Communicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<User> allUsers() {
        ResponseEntity<List<User>> response =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {
                        });
        List<User> listUsers = response.getBody();
        return listUsers;
    }

    public void addUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookies);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        String response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class, user).getBody();
        System.out.println("add");
        System.out.println(response);
    }

    public void editUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookies);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        System.out.println(cookies);

        String response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, user).getBody();
        System.out.println("upd");
        System.out.println(response);

    }

    public void deleteUser(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookies);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        System.out.println(cookies);
        String response = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, entity, String.class).getBody();
        System.out.println("del");
        System.out.println(response);
    }


    public String getSessionId() {
//            HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        HttpHeaders response = restTemplate.headForHeaders(url);
//            cookies = response.getHeaders().get("Set-Cookie").get(0);
        cookies = response.get("Set-Cookie").get(0);
        System.out.println(cookies);
        return cookies;
    }

//    private HttpHeaders setHeaders() {
//
//        headers.set(HttpHeaders.COOKIE, cookie);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return headers;
//    }


}
