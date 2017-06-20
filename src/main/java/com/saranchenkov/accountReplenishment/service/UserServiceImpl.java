package com.saranchenkov.accountReplenishment.service;

import com.saranchenkov.accountReplenishment.model.User;
import com.saranchenkov.accountReplenishment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ivan on 17.06.2017.
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    Environment environment;

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public User get(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public void update(double balance, int id) {
        repository.update(balance, id);
    }

    @Override
    public Page<User> getCurrentPage(int pageNumber) {
        final int PAGE_SIZE = environment.getRequiredProperty("spring.rest.page-size", Integer.class);
        final Sort sort = new Sort(
                new Sort.Order(Sort.Direction.DESC, "registerDate"),
                new Sort.Order(Sort.Direction.ASC, "email")
        );
        final PageRequest page = new PageRequest(pageNumber, PAGE_SIZE, sort);
        return repository.findAll(page);
    }

    @Override
    public boolean isEmailUnique(Integer id, String email) {
        final User user = repository.findByEmail(email);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }
}
