package com.example.prueba_sunwise.Model;

public interface IUser {
    String getCorreo();
    String getPassword();
    int isValid(String correo, String password);
}
