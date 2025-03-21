package com.br.demo.dto.response;

public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private double preco;
    private String categoriaNome;

    public ProdutoResponseDTO(){

    }

    public ProdutoResponseDTO(Long id, String nome, double preco, String categoriaNome) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoriaNome = categoriaNome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getCategoriaNome() { return categoriaNome; }
}
