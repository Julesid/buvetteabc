package com.cheminat.buvetteabc.services;

import com.cheminat.buvetteabc.data.User;
import com.cheminat.buvetteabc.data.UserRepository;
import com.cheminat.buvetteabc.data.SoldeRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final SoldeRepository soldeRepository;

    // Constructeur avec les deux repositories
    public UserService(UserRepository repository, SoldeRepository soldeRepository) {
        this.repository = repository;
        this.soldeRepository = soldeRepository;
    }

    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    public User update(User entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<User> list(Pageable pageable, Specification<User> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

    // Méthode pour obtenir le solde d'un utilisateur
    public Double getBalanceForUser(Long userId) {
        // Recherche du solde associé à l'utilisateur, retourne 0.0 si absent
        return soldeRepository.findByUserId(userId)
                              .map(solde -> solde.getSOLDE())
                              .orElse(0.0);
    }
}
