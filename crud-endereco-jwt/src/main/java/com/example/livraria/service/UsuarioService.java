package com.example.livraria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.livraria.model.Endereco;
import com.example.livraria.model.Role;
import com.example.livraria.model.Usuario;
import com.example.livraria.repository.RoleRepository;
import com.example.livraria.repository.UsuarioRepository;

/**
 * Classe que representa os serviços do Usuario, esta vai poder acessar o devido repositório,
 * no caso o UsuarioRepository.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */

@Service
public class UsuarioService {
	
	/*
	 * Contém a anotação @Autowired para que quando a classe UsuarioService for usada o 
	 * Spring chame usuarioRepository.
	 */
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    /**
     * Método feito pra receber os valores passados lá no menu para depois serem 'setados'
     * e consequentemente virar um objeto para assim poder ser salvo o usuário usando o método 
     * 'save' da interface CrudRepository que é estendida pela interface UsuarioRepository.
     * 
     * @param nome
     * @param cpf
     * @param email
     * @param senha
     * @param cep
     * @param estado
     * @param cidade
     * @param rua
     * @param numero
     * @param bairro
     * @param complemento
     */
    public void salvar(Usuario usuario) {
    	usuario.addPapel(roleRepository.findById("ROLE_USER").get());
    	
	    usuarioRepository.save(usuario);
    }
    
    public void alterar(Usuario usuario, List<Role> papelUsuarioAntigo) {
    	usuario.addPapel(roleRepository.findById(papelUsuarioAntigo.get(0).getPapel()).get());
    	
    	usuarioRepository.save(usuario);
    }
  
    /**
     * Método feito para verificar se o email passado por parametro existe na base de dados,
     * podendo returnar true ou false.
     * 
     * @param email do usuário
     * @return true ou false
     */
    public boolean verificarEmail(String email){
    	return usuarioRepository.existsById(email);
    }

    /**
     * Método feito pra remover qualquer usuário.
     * 
     * @param o próprio usuário
     */
    public void remover(Usuario user) {
        usuarioRepository.delete(user);
    }

    /**
     * Método feito pra retornar um usuário através do email passado por parametro. 
     * 
     * @param email do usuário
     * @return objeto Usuario ou null
     */
    public Usuario getUsuario(String email) {
    	Optional<Usuario> user = usuarioRepository.findById(email);
        return user.isPresent() ? user.get() : null;
    }

    /**
     * Método feito pra retornar todos os usuários existentes sem exceção.
     * 
     * @return Lista de todos os objetos de Usuarios
     */
	public List<Usuario> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

	public List<Endereco> getEndereco(Usuario user){
		return user.getEndereco();
	}
	
	public void addEndereco(Endereco end){
		Usuario user =(Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		user.addEndereco(end);
		usuarioRepository.save(user);
	}
	
	
	
}