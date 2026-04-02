package com.projetojpa.services;

import com.projetojpa.entities.User;
import com.projetojpa.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    public User update(Long id, User user) {

        User userDB = findById(id);

        userDB.setNome(user.getNome());
        userDB.setEmail(user.getEmail());
        userDB.setCpf(user.getCpf());
        userDB.setTelefone(user.getTelefone());
        userDB.setEndereco(user.getEndereco());
        userDB.setGenero(user.getGenero());
        userDB.setAltura(user.getAltura());
        userDB.setPeso(user.getPeso());
        userDB.setDataNasc(user.getDataNasc());

        return repository.save(userDB);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User login(User user) {

        User userDB = repository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getPassword().equals(userDB.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return userDB;
    }
}