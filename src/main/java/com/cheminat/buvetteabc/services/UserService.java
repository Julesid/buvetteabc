package com.cheminat.buvetteabc.services;

import com.cheminat.buvetteabc.data.UserAbc;
import com.cheminat.buvetteabc.data.UserRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<UserAbc> get(Long id) {
        return repository.findById(id);
    }

    public UserAbc update(UserAbc entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<UserAbc> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<UserAbc> list(Pageable pageable, Specification<UserAbc> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
