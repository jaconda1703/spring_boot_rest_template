package org.example;

public class App {

    public static void main(String[] args) {
        UserClient client = new UserClient();

        client.getCookieAndAllUsers();

        String part1 = client.saveUser();
        String part2 = client.updateUser();
        String part3 = client.deleteUser();

        System.out.println(part1 + part2 + part3);
    }
}
