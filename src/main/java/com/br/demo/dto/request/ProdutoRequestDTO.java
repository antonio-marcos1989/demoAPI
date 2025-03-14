package com.br.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto não pode estar vazio")
    private String nome;

    @NotNull(message = "O preço não pode ser nulo")
    @Positive(message = "O preço deve ser maior que zero")
    private double preco;

    @NotBlank(message = "O número de sério não pode estar vazio")
    private String numeroSerie;

    public ProdutoRequestDTO(){

    }

    public ProdutoRequestDTO(String nome, double preco, String numeroSerie) {
        this.nome = nome;
        this.preco = preco;
        this.numeroSerie = numeroSerie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
}
