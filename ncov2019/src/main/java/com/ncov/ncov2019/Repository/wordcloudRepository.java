package com.ncov.ncov2019.Repository;

import com.ncov.ncov2019.domain.wordcloud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface wordcloudRepository extends JpaRepository<wordcloud,Integer> {
}
