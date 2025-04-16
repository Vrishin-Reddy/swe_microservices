package com.swe645.sudentserveyRESTapi.controller;

import com.swe645.sudentserveyRESTapi.entity.Survey;
import com.swe645.sudentserveyRESTapi.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = "*")
public class SurveyController {

    private static final Logger logger = Logger.getLogger(SurveyController.class.getName());

    @Autowired
    private SurveyService surveyService;

    @GetMapping
    public ResponseEntity<List<Survey>> getAllSurveys() {
        try {
            List<Survey> surveys = surveyService.getAllSurveys();
            return new ResponseEntity<>(surveys, HttpStatus.OK);
        } catch (Exception e) {
            logger.severe("Error getting all surveys: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id) {
        try {
            Survey survey = surveyService.getSurveyById(id);
            return new ResponseEntity<>(survey, HttpStatus.OK);
        } catch (Exception e) {
            logger.severe("Error getting survey by id: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createSurvey(@RequestBody Survey survey) {
        try {
            logger.info("Received survey data: " + survey);
            Survey newSurvey = surveyService.createSurvey(survey);
            return new ResponseEntity<>(newSurvey, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.severe("Error creating survey: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Error creating survey: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @RequestBody Survey surveyDetails) {
        try {
            Survey updatedSurvey = surveyService.updateSurvey(id, surveyDetails);
            return new ResponseEntity<>(updatedSurvey, HttpStatus.OK);
        } catch (Exception e) {
            logger.severe("Error updating survey: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable Long id) {
        try {
            surveyService.deleteSurvey(id);
            return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK); 
        } catch (Exception e) {
            logger.severe("Error deleting survey: " + e.getMessage());
            return new ResponseEntity<>("Failed to delete survey.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}