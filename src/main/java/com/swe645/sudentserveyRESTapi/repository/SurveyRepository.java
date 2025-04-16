package com.swe645.sudentserveyRESTapi.repository;

import com.swe645.sudentserveyRESTapi.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    // Additional custom query methods can be added here if needed
}