package com.saranchenkov.accountReplenishment.service;

import com.saranchenkov.accountReplenishment.model.Balance;
import com.saranchenkov.accountReplenishment.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Ivan on 17.06.2017.
 */

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    Environment environment;

    BalanceRepository repository;

    @Autowired
    public BalanceServiceImpl(BalanceRepository repository){
        this.repository = repository;
    }

    @Override
    public Balance get(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Balance getByUserEmail(String userEmail) {
        return repository.findByUserEmail(userEmail);
    }

    @Override
    public Balance save(Balance balance) {
        return repository.save(balance);
    }

    @Override
    public Page<Balance> getCurrentPage(int pageNumber) {
        final int PAGE_SIZE = environment.getRequiredProperty("spring.rest.page-size", Integer.class);
        final PageRequest page = new PageRequest(pageNumber, PAGE_SIZE);
        return repository.findAll(page);
    }

    @Override
    public Page<Balance> getFilterPage(int pageNumber, Date startDate, Date endDate) {
        final int PAGE_SIZE = environment.getRequiredProperty("spring.rest.page-size", Integer.class);
        final PageRequest page = new PageRequest(pageNumber, PAGE_SIZE);
        return repository.filterAll(page, startDate, endDate);
    }
}
