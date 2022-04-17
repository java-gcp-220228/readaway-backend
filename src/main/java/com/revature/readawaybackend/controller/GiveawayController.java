package com.revature.readawaybackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.readawaybackend.dtos.GiveawayDTO;
import com.revature.readawaybackend.models.Comment;
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

  @GetMapping("/giveaways/{giveaway_id}")
  public ResponseEntity<?> getById(@PathVariable("giveaway_id") String id) throws JsonProcessingException {
    try {
      Giveaway giveaway = giveawayService.getGiveawayById(id);
      GiveawayDTO dto = modelMapper.map(giveaway, GiveawayDTO.class);
      return ResponseEntity.ok().body(dto);
    } catch (HttpClientErrorException e) {
      return ResponseEntity.status(404).body(e.getMessage());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }

  }

  @GetMapping("/users/{user_id}/giveaways")
  public ResponseEntity<?> getAllByCreatorId(@PathVariable("user_id") String id) throws JsonProcessingException {
    try {
      Set<Giveaway> giveaways = giveawayService.getAllGiveawaysByCreatorId(id);
      Set<GiveawayDTO> dtos = new HashSet<>();
      for (Giveaway giveaway : giveaways) {
        dtos.add(modelMapper.map(giveaway, GiveawayDTO.class));
      }
      return ResponseEntity.ok().body(dtos);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }

  }

  @GetMapping("/giveaways/winners/{user_id}")
  public ResponseEntity<?> getAllByWinnerId(@PathVariable("user_id") String id) throws JsonProcessingException {
    try {
      Set<Giveaway> giveaways = giveawayService.getAllGiveawaysByWinnerId(id);
      Set<GiveawayDTO> dtos = new HashSet<>();
      for (Giveaway giveaway : giveaways) {
        dtos.add(modelMapper.map(giveaway, GiveawayDTO.class));
      }
      return ResponseEntity.ok().body(dtos);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }

  }

  @GetMapping("/giveaways")
  public ResponseEntity<?> getAllInProgress() throws JsonProcessingException {

    Set<Giveaway> giveaways = giveawayService.getAllGiveawaysInProgress();
    Set<GiveawayDTO> dtos = new HashSet<>();
    for (Giveaway giveaway : giveaways) {
      dtos.add(modelMapper.map(giveaway, GiveawayDTO.class));
    }
    return ResponseEntity.ok().body(dtos);
  }

  @PostMapping("/giveaways")
  public ResponseEntity<?> createGiveaway(@RequestBody Giveaway giveaway, @RequestHeader("Authorization") String headerValue) {
    String jwt = headerValue.split(" ")[1];
    giveawayService.addNewGiveaway(giveaway);
    return ResponseEntity.ok("");
  }

  @PostMapping("/giveaways/{giveaway_id}/comments")
  public ResponseEntity<?> addCommentToGiveaway(@PathVariable("giveaway_id") String id, @RequestBody Comment comment, @RequestHeader("Authorization") String headerValue) {
    String jwt = headerValue.split(" ")[1];
    giveawayService.addCommentToGiveaway(id, comment);
    return ResponseEntity.ok("");
  }

  @PostMapping("/giveaways/{giveaway_id}/entries/{user_id}")
  public ResponseEntity<?> addEntryToGiveaway(@PathVariable("giveaway_id") String giveawayId, @PathVariable("user_id") String userId, @RequestHeader("Authorization") String headerValue) {
    String jwt = headerValue.split(" ")[1];
    giveawayService.addEntryToGiveaway(giveawayId, userId);
    return ResponseEntity.ok("");
  }
}
