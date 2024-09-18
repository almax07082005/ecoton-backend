package ecoton.ecotonbackend.controller;

import ecoton.ecotonbackend.dto.EventDTO;
import ecoton.ecotonbackend.request.create.EventCreateRequest;
import ecoton.ecotonbackend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/events")
@RestController
// TODO add update method to event and organizer
// TODO one-to-many relationship is broken
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Integer id) {
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> createEvent(@RequestBody EventCreateRequest eventCreateRequest) {
        eventService.createEvent(eventCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable Integer id, @RequestBody EventCreateRequest eventCreateRequest) {
        eventService.updateEvent(id, eventCreateRequest);
        return ResponseEntity.ok().build();
    }
}
