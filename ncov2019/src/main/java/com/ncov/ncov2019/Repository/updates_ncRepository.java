package com.ncov.ncov2019.Repository;

import com.ncov.ncov2019.domain.updates_nc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface updates_ncRepository extends JpaRepository<updates_nc,Integer> {
    public List<updates_nc> findByReportdate(String reportdate);
    public List<updates_nc> findByProvince(String province);
    public List<updates_nc> findByCity(String city);
}
