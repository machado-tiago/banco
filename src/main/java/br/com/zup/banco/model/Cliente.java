package br.com.zup.banco.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Cliente {
    
    @Id
    @Pattern(regexp = "99999999999")
    private String cpf;

    @NotNull(message = "O nome é obrigatório.")
    private String nome;

    @NotNull(message = "O sobrenome é obrigatório.")
    private String sobrenome;

    @Email
    // @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address") ou regexp = "^(.+)@(.+)$"
    private String email;
    private LocalDate nascimento;
    @OneToOne
    private Endereco endereco;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
    

    
}
