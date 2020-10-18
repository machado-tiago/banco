package br.com.zup.banco.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Cep é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Digite o CEP no formato: 99999-999.")
    private String cep;

    @NotBlank(message = "Rua é obrigatória.")
    private String rua;

    @NotBlank(message = "Bairro é obrigatório.")
    private String bairro;

    @NotBlank(message = "Complemento é obrigatório.")
    private String complemento;

    @NotBlank(message = "Cidade é obrigatória.")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório.")
    private String estado;

    @OneToOne
    @JoinColumn(name = "cliente_cpf")
    @JsonIgnore
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



    
}
