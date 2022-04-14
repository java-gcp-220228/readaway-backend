package com.revature.readawaybackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.readawaybackend.dtos.GiveawayDTO;
import com.revature.readawaybackend.models.Giveaway;
import com.revature.readawaybackend.service.GiveawayService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class GiveawayController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GiveawayService giveawayService;

    // @Autowired
    // JWTService jwtService;

    // Get By Id
    @GetMapping("/giveaways/{giveaway_id}")
    public ResponseEntity<?> getById(@PathVariable("giveaway_id") String id) throws JsonProcessingException {

        try {
            Giveaway giveaway = giveawayService.getGiveawayById(id);
            GiveawayDTO dto = modelMapper.map(giveaway, GiveawayDTO.class);
            String json = new ObjectMapper().writeValueAsString(dto);
            return ResponseEntity.ok().body(json);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    // Get All By Creator Id
    @GetMapping("/users/{user_id}/giveaways")
    public ResponseEntity<?> getAllByCreatorId(@PathVariable("user_id") String id) throws JsonProcessingException {
        try {
            Set<Giveaway> giveaways =  giveawayService.getAllGiveawaysByCreatorId(id);
            Set<GiveawayDTO> dtos = new HashSet<>();
            for (Giveaway giveaway: giveaways) {
                dtos.add(modelMapper.map(giveaway, GiveawayDTO.class));
            }
            String json = new ObjectMapper().writeValueAsString(dtos);
            return ResponseEntity.ok().body(json);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    // Get All By Winner Id
    @GetMapping("/giveaways/winners/{user_id}")
    public ResponseEntity<?> getAllByWinnerId(@PathVariable("user_id") String id) throws JsonProcessingException {
        try {
            Set<Giveaway> giveaways = giveawayService.getAllGiveawaysByWinnerId(id);
            Set<GiveawayDTO> dtos = new HashSet<>();
            for (Giveaway giveaway: giveaways) {
                dtos.add(modelMapper.map(giveaway, GiveawayDTO.class));
            }
            String json = new ObjectMapper().writeValueAsString(dtos);
            return ResponseEntity.ok().body(json);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    // Get All In Progress
    @GetMapping("/giveaways")
    public ResponseEntity<?> getAllInProgress() throws JsonProcessingException {

        Set<Giveaway> giveaways =  giveawayService.getAllGiveawaysInProgress();
        Set<GiveawayDTO> dtos = new HashSet<>();
        for (Giveaway giveaway: giveaways) {
            dtos.add(modelMapper.map(giveaway, GiveawayDTO.class));
        }
        String json = new ObjectMapper().writeValueAsString(dtos);
        return ResponseEntity.ok().body(json);


    }

    // Post New Giveaway
    @PostMapping("/giveaways")
    public ResponseEntity<?> createGiveaway(@RequestBody Giveaway giveaway, @RequestHeader("Authorization") String headerValue) {
        String jwt = headerValue.split(" ")[1];
        giveawayService.addNewGiveaway(giveaway);
        return ResponseEntity.status(200).body("ok");
    }
}
