package com.br.demo.service;

import com.br.demo.dto.CategoriaDTO;
import com.br.demo.model.Categoria;
import com.br.demo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> listarCategorias(){
        return categoriaRepository.findAll().stream()
                .map(c -> new CategoriaDTO(c.getNome(), c.getDescricao()))
                .collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoriaDTO(categoria.getNome(), categoria.getDescricao());
    }

    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO){
        Categoria novaCategoria = new Categoria(null, categoriaDTO.getNome(), categoriaDTO.getDescricao());
        Categoria categoriaSalva = categoriaRepository.save(novaCategoria);
        return new CategoriaDTO(categoriaSalva.getNome(), categoriaSalva.getDescricao());
    }

    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO categoriaDTO){
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoriaExistente.setNome(categoriaDTO.getNome());
        categoriaExistente.setDescricao(categoriaDTO.getDescricao());

        Categoria categoriaAtualizada = categoriaRepository.update(categoriaExistente);
        return new CategoriaDTO(categoriaAtualizada.getNome(), categoriaAtualizada.getDescricao());
    }

    public void excluirCategoria(Long id){
        categoriaRepository.deleteById(id);
    }
}