package com.carrerbridge.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.carrerbridge.dto.AccountType;
import com.carrerbridge.dto.UserDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private Long id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    private AccountType accountType;

    // Explicitly define the constructor (this should fix the error)
    public User(Long id, String name, String email, String password, AccountType accountType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    public UserDTO toDTO() {
        return new UserDTO(this.id, this.name, this.email, this.password, this.accountType);
    }
}
