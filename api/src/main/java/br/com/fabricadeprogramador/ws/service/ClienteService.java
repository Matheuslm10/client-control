package br.com.fabricadeprogramador.ws.service;

import br.com.fabricadeprogramador.ws.model.Cliente;
import br.com.fabricadeprogramador.ws.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    // Negocios
    public Cliente cadastrar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Collection<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public void excluir (Cliente cliente){
        clienteRepository.delete(cliente);
    }

    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findOne(id);
    }

    public Cliente alterar(Cliente cliente){
        return clienteRepository.save(cliente);
    }
}
