package com.swe645.sudentserveyRESTapi.services;

import com.swe645.sudentserveyRESTapi.entity.Survey;
import java.util.List;

/**
 * Service interface for handling business logic related to surveys
 */
public interface SurveyService {
    /**
     * Get all surveys from the database
     * @return List of all surveys
     */
    List<Survey> getAllSurveys();

    /**
     * Get a survey by its ID
     * @param id Survey ID
     * @return Survey object
     */
    Survey getSurveyById(Long id);

    /**
     * Create a new survey
     * @param survey Survey object to create
     * @return Created survey
     */
    Survey createSurvey(Survey survey);

    /**
     * Update an existing survey
     * @param id Survey ID to update
     * @param surveyDetails Updated survey details
     * @return Updated survey
     */
    Survey updateSurvey(Long id, Survey surveyDetails);

    /**
     * Delete a survey
     * @param id Survey ID to delete
     */
    void deleteSurvey(Long id);
}