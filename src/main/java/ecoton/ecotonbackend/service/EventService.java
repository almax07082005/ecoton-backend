package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.dto.EventDTO;
import ecoton.ecotonbackend.entity.EventEntity;
import ecoton.ecotonbackend.entity.OrganizerEntity;
import ecoton.ecotonbackend.exceptions.EventNotExistException;
import ecoton.ecotonbackend.mapper.EventMapper;
import ecoton.ecotonbackend.repository.EventRepository;
import ecoton.ecotonbackend.repository.OrganizerRepository;
import ecoton.ecotonbackend.request.create.EventCreateRequest;
import ecoton.ecotonbackend.request.update.EventUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final OrganizerService organizerService;
    private final OrganizerRepository organizerRepository;

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

        OrganizerEntity organizerEntity = eventEntity.get().getOrganizer();
        organizerEntity.getEvents().remove(eventEntity.get());
        organizerRepository.save(organizerEntity);

        eventRepository.delete(eventEntity.get());
    }

    public void createEvent(EventCreateRequest eventCreateRequest) {
        EventEntity eventEntity = eventRepository.save(EventEntity.builder()
                .name(eventCreateRequest.getName())
                .description(eventCreateRequest.getDescription())
                .mapsId(eventCreateRequest.getMapsId())
                .dateTime(eventCreateRequest.getDateTime())
                .imageName(eventCreateRequest.getImageName())
                .participants(new ArrayList<>())
                .organizer(organizerService.getOrganizerEntity(eventCreateRequest.getOrganizerId()))
                .build()
        );

        OrganizerEntity organizerEntity = organizerService.getOrganizerEntity(eventCreateRequest.getOrganizerId());
        organizerEntity.getEvents().add(eventEntity);
        organizerRepository.save(organizerEntity);
    }

    public void updateEvent(Integer id, EventUpdateRequest eventUpdateRequest) {
        Optional<EventEntity> eventEntity = eventRepository.findById(id);
        if (eventEntity.isEmpty()) {
            throw new EventNotExistException();
        }

        eventRepository.save(eventEntity.get().toBuilder()
                .name(eventUpdateRequest.getName())
                .description(eventUpdateRequest.getDescription())
                .mapsId(eventUpdateRequest.getMapsId())
                .dateTime(eventUpdateRequest.getDateTime())
                .imageName(eventUpdateRequest.getImageName())
                .build()
        );
    }
}
