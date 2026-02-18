package org.example;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class UserClient {

    private final RestTemplate rt = new RestTemplate();
    private final String URL = "http://94.198.50.185:7081/api/users";

    private String cookie;

    public String getCookieAndAllUsers() {
        ResponseEntity<String> responseEntity = rt.exchange(URL, HttpMethod.GET, null, String.class); //метод, который отправляет http запросы и получает ответы

        String setCookie = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        this.cookie = (setCookie != null) ? setCookie.split(";",2)[0]:null;

        String users = responseEntity.getBody();
        return users;
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.COOKIE, cookie);
        return headers;
    }

    public String saveUser(){
        HttpHeaders headers = getHeaders();
        String json = "{\"id\":3,\"name\":\"James\",\"lastName\":\"Brown\",\"age\":30}";

        HttpEntity <String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<String> resp = rt.exchange(URL, HttpMethod.POST, entity, String.class);
        return resp.getBody();
    }

    public String updateUser(){
        HttpHeaders headers = getHeaders();
        String json = "{\"id\":3,\"name\":\"Thomas\",\"lastName\":\"Shelby\",\"age\":30}";

        HttpEntity <String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<String> resp = rt.exchange(URL, HttpMethod.PUT, entity, String.class);
        return resp.getBody();
    }

    public String deleteUser(){
        HttpHeaders headers = getHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> resp = rt.exchange(URL + "/3", HttpMethod.DELETE, entity, String.class);

        return resp.getBody();
    }
}
