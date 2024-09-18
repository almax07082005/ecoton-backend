package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.dto.EventDTO;
import ecoton.ecotonbackend.entity.EventEntity;
import ecoton.ecotonbackend.exceptions.EventNotExistException;
import ecoton.ecotonbackend.mapper.EventMapper;
import ecoton.ecotonbackend.repository.EventRepository;
import ecoton.ecotonbackend.request.create.EventCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final OrganizerService organizerService;

    public EventDTO getEvent(Integer id) {
        Optional<EventEntity> eventEntity = eventRepository.findById(id);
        if (eventEntity.isEmpty()) {
            throw new EventNotExistException();
        }

        return EventMapper.instance.map(eventEntity.get());
    }

    public void deleteEvent(Integer id) {
        Optional<EventEntity> eventEntity = eventRepository.findById(id);
        if (eventEntity.isEmpty()) {
            throw new EventNotExistException();
        }

        eventRepository.deleteById(id);
    }

    public void createEvent(EventCreateRequest eventCreateRequest) {
        eventRepository.save(EventEntity.builder()
                .name(eventCreateRequest.getName())
                .description(eventCreateRequest.getDescription())
                .mapsId(eventCreateRequest.getMapsId())
                .dateTime(eventCreateRequest.getDateTime())
                .imageName(eventCreateRequest.getImageName())
                .participants(new ArrayList<>())
                .organizer(organizerService.getOrganizerEntity(eventCreateRequest.getOrganizerId()))
                .build());
    }

    public void updateEvent(Integer id, EventCreateRequest eventCreateRequest) {
        Optional<EventEntity> eventEntity = eventRepository.findById(id);
        if (eventEntity.isEmpty()) {
            throw new EventNotExistException();
        }

        eventRepository.save(eventEntity.get().toBuilder()
                .name(eventCreateRequest.getName())
                .description(eventCreateRequest.getDescription())
                .mapsId(eventCreateRequest.getMapsId())
                .dateTime(eventCreateRequest.getDateTime())
                .imageName(eventCreateRequest.getImageName())
                .organizer(organizerService.getOrganizerEntity(eventCreateRequest.getOrganizerId()))
                .build());
    }
}
