package com.ddutra9.cadastrousuario.infrastructure.model;

import com.ddutra9.cadastrousuario.application.resonse.UserResponse;
import com.ddutra9.cadastrousuario.domain.CPF;
import com.ddutra9.cadastrousuario.domain.Email;
import com.ddutra9.cadastrousuario.domain.Password;
import com.ddutra9.cadastrousuario.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "_user")
@Getter
@Setter
@NoArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String cpf;
    @Size(max = 200)
    @NotBlank
    private String name;
    @Size(max = 50)
    @NotBlank
    private String email;
    @Size(max = 50)
    @NotBlank
    private String password;

    public UserData(String cpf, String name, String email, String password) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserResponse toUserResonse() {
        return new UserResponse(this.cpf, this.name, this.email, this.password);
    }

    public User toUser() {
        return new User(new CPF(this.getCpf()), this.name, new Email(this.email), new Password(this.password));
    }
}
