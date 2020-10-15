package br.com.zup.banco.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Cliente {
    
    @Id
    @NotNull(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{11}?", message = "O CPF deve ser preenchido com 11 dígitos, somente os números.")
    private String cpf;

    @NotNull(message = "O nome é obrigatório.")
    private String nome;

    @NotNull(message = "O sobrenome é obrigatório.")
    private String sobrenome;

    @Pattern(regexp = "(.+)@(.+)[.](.+)", message = "Preencha um e-mail válido.")
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
