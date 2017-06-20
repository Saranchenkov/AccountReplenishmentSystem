package com.saranchenkov.accountReplenishment.service;

import com.saranchenkov.accountReplenishment.model.User;
import org.springframework.data.domain.Page;

/**
 * Created by Ivan on 17.06.2017.
 */
public interface UserService {

    User get(Integer id);

    User getByEmail(String email);

    User save(User user);

    void update(double balance, int id);

    Page<User> getCurrentPage(int pageNumber);

    boolean isEmailUnique(Integer id, String email);
}
