package com.ncov.ncov2019.Repository;


import com.ncov.ncov2019.domain.historydata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface historydataRepository extends JpaRepository<historydata,Integer> {
    public List<historydata> findByCity(String city);
    public List<historydata> findByProvince(String province);
}
