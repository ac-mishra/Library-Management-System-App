package com.librarymanagement;

import com.librarymanagement.security.PasswordUtil;

public class GeneratePassword {

    public static void main(String[] args) {

        System.out.println(
                PasswordUtil.hashPassword("admin123")
        );

    }

}