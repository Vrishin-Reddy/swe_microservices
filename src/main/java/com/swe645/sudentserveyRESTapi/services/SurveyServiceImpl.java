package com.swe645.sudentserveyRESTapi.services;

import com.swe645.sudentserveyRESTapi.exception.ResourceNotFoundException;
import com.swe645.sudentserveyRESTapi.entity.Survey;
import com.swe645.sudentserveyRESTapi.repository.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of SurveyService interface
 */
@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(Long id) {
        return surveyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Survey not found with id: " + id));
    }

    @Override
    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public Survey updateSurvey(Long id, Survey surveyDetails) {
        Survey survey = getSurveyById(id);

        // Update all fields
        survey.setFirstName(surveyDetails.getFirstName());
        survey.setLastName(surveyDetails.getLastName());
        survey.setStreetAddress(surveyDetails.getStreetAddress());
        survey.setCity(surveyDetails.getCity());
        survey.setState(surveyDetails.getState());
        survey.setZip(surveyDetails.getZip());
        survey.setTelephone(surveyDetails.getTelephone());
        survey.setEmail(surveyDetails.getEmail());
        survey.setSurveyDate(surveyDetails.getSurveyDate());
        survey.setLikedMost(surveyDetails.getLikedMost());
        survey.setInterestedBy(surveyDetails.getInterestedBy());
        survey.setLikelihoodRecommend(surveyDetails.getLikelihoodRecommend());
        survey.setComments(surveyDetails.getComments());

        return surveyRepository.save(survey);
    }

    @Override
    public void deleteSurvey(Long id) {
        Survey survey = getSurveyById(id);
        surveyRepository.delete(survey);
    }
}