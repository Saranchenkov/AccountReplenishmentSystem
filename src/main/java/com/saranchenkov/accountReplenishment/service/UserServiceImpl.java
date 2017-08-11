package com.saranchenkov.accountReplenishment.service;

import com.saranchenkov.accountReplenishment.model.User;
import com.saranchenkov.accountReplenishment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    Environment environment;

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public User get(Integer id) {

        LOGGER.info("Searching user with id: {}", id);
        return repository.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        LOGGER.info("Searching user with email: {}", email);
        User user = repository.findByEmail(email);
        LOGGER.info("User {} was found.", user);
        return user;
    }

    @Override
    @Transactional
    public User save(User user) {
        LOGGER.info("Save user: {}", user);
        return repository.save(user);
    }

    @Override
    @Transactional
    public void update(double balance, int id) {
        LOGGER.info("Updating user with id={} and balance={}", id, balance);
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
