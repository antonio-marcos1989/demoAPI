package com.br.demo.service;

import com.br.demo.dto.CategoriaDTO;
import com.br.demo.model.Categoria;
import com.br.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> listarCategorias(){
        return categoriaRepository.findAll().stream()
                .map(c -> new CategoriaDTO(c.getId(), c.getNome(), c.getDescricao()))
                .collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(Long id){
        return categoriaRepository.findById(id)
                .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao()))
                .orElse(null);
    }

    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria(null, categoriaDTO.getNome(), categoriaDTO.getDescricao());
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoriaDTO.getDescricao());
    }

    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setNome(categoriaDTO.getNome());
            categoriaRepository.save(categoria);
            return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoriaDTO.getDescricao());
        }
        return null;
    }

    public void excluirCategoria(Long id){
        categoriaRepository.deleteById(id);
    }
}