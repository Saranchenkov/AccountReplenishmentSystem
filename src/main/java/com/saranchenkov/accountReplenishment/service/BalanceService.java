package com.saranchenkov.accountReplenishment.service;

import com.saranchenkov.accountReplenishment.model.Balance;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 * Created by Ivan on 17.06.2017.
 */
public interface BalanceService {

    Balance get(Integer id);

    Balance getByUserEmail(String email);

    Balance save(Balance balance);

    Page<Balance> getCurrentPage(int pageNumber);

    Page<Balance> getFilterPage(int pageNumber, Date startDate, Date endDate);
}
