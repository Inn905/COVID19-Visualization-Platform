package com.ncov.ncov2019.Repository;

import com.ncov.ncov2019.domain.analysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface analysis_Repository extends JpaRepository<analysis,Integer>{
    public List<analysis> findByCity(String city);
    public List<analysis> findByProvince(String province);

}
