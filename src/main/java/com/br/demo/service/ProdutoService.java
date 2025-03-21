package com.br.demo.service;

import com.br.demo.dto.request.ProdutoRequestDTO;
import com.br.demo.dto.response.ProdutoResponseDTO;
import com.br.demo.model.Categoria;
import com.br.demo.model.Produto;
import com.br.demo.repository.CategoriaRepository;
import com.br.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProdutoResponseDTO> listarProdutos(){
        return produtoRepository.findAll().stream()
                .map(p -> new ProdutoResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getCategoria().getNome()))
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(Long id){
        return produtoRepository.findById(id)
                .map(p -> new ProdutoResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getCategoria().getNome()))
                .orElse(null);
    }

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO requestDTO){
        Categoria categoria = categoriaRepository.findById(requestDTO.getCategoriaId()).orElse(null);
        if (categoria == null) return null;

        Produto produto = new Produto(requestDTO.getNome(), requestDTO.getPreco(), requestDTO.getNumeroSerie(), categoria);
        produto = produtoRepository.save(produto);

        return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco(), categoria.getNome());
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO requestDTO) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            produto.setNome(requestDTO.getNome());
            produto.setPreco(requestDTO.getPreco());
            produto.setNumeroSerie(requestDTO.getNumeroSerie());
            Categoria categoria = categoriaRepository.findById(requestDTO.getCategoriaId()).orElse(null);
            if (categoria != null) {
                produto.setCategoria(categoria);
            }
            produtoRepository.save(produto);
            return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco(), categoria.getNome());
        }
        return null;
    }

    public void excluirProduto(Long id){
        produtoRepository.deleteById(id);
    }
}