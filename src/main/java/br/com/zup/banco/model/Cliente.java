package br.com.zup.banco.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente {
    public static final int IDADE_MIN=18;
    
    @Id
    @NotNull(message = "O CPF é obrigatório.")
    @Pattern(regexp = "(\\d{11})?", message = "O CPF deve ser preenchido com 11 dígitos, somente os números.")
    private String cpf;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @JsonIgnore
    private String cpfFile;
    
    @NotBlank(message = "O sobrenome é obrigatório.")
    private String sobrenome;

    @NotNull(message = "O email é obrigatório.")
    @Pattern(regexp = "(.+)@(.+)(\\.\\w{2,3})$", message = "Preencha um e-mail válido.")
    private String email;

    
    @NotNull(message = "O nascimento é obrigatório.")
    private LocalDate nascimento;
    
    @OneToOne(mappedBy = "cliente")
    private Endereco endereco;

    public Boolean nascimentoCheck() {
        if (this.getNascimento().plusYears(IDADE_MIN).isBefore(LocalDate.now())) {
            return true;
        } else {
            return false;
        }
    }

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
        
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static int getIdadeMin() {
        return IDADE_MIN;
    }

    public String getCpfFile() {
        return cpfFile;
    }

    public void setCpfFile(String cpfFile) {
        this.cpfFile = cpfFile;
    }


    
}
