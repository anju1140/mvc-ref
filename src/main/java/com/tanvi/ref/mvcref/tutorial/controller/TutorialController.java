package com.tanvi.ref.mvcref.tutorial.controller;

import com.tanvi.ref.mvcref.tutorial.Repository.TutorialRepository;
import com.tanvi.ref.mvcref.tutorial.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title){
        try{
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            if(title == null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
            if(tutorials.isEmpty()){
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return  new ResponseEntity<>(tutorials, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getByTutorialId(@PathVariable("id") long id){
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if(tutorialData.isPresent()){
             return  new ResponseEntity<>(tutorialData.get(),HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial){
        try{
            Tutorial _tutorial = tutorialRepository.save(new Tutorial
                    (tutorial.getTitle(),tutorial.getDescription(),false));
            return new ResponseEntity<>(tutorial,HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id,@RequestBody Tutorial tutorial){
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if(tutorialData.isPresent()) {
            Tutorial tutorialEx = tutorialData.get();
            this.updateTutorial(tutorialEx,tutorial);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private void updateTutorial(Tutorial tutorialEx,Tutorial tutorialUp){
        tutorialEx.setTitle(tutorialUp.getTitle());
        tutorialEx.setDescription(tutorialUp.getDescription());
        tutorialEx.setPublished(tutorialUp.isPublished());

    }


}
