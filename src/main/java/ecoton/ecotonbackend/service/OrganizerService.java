package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.dto.OrganizerDTO;
import ecoton.ecotonbackend.entity.OrganizerEntity;
import ecoton.ecotonbackend.exceptions.OrganizerNotExistException;
import ecoton.ecotonbackend.mapper.OrganizerMapper;
import ecoton.ecotonbackend.repository.EventRepository;
import ecoton.ecotonbackend.repository.OrganizerRepository;
import ecoton.ecotonbackend.request.create.OrganizerCreateRequest;
import ecoton.ecotonbackend.request.update.OrganizerUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class OrganizerService {

    private final OrganizerRepository organizerRepository;
    private final EventRepository eventRepository;

    public OrganizerDTO getOrganizer(Integer id) {
        Optional<OrganizerEntity> organizerEntity = organizerRepository.findById(id);
        if (organizerEntity.isEmpty()) {
            throw new OrganizerNotExistException();
        }

        return OrganizerMapper.instance.map(organizerEntity.get());
    }

    public List<OrganizerDTO> getAllOrganizers() {
        List<OrganizerDTO> organizerDTOs = new ArrayList<>();

        for (OrganizerEntity organizerEntity : organizerRepository.findAll()) {
            organizerDTOs.add(OrganizerMapper.instance.map(organizerEntity));
        }

        return organizerDTOs;
    }

    public OrganizerEntity getOrganizerEntity(Integer id) {
        Optional<OrganizerEntity> organizerEntity = organizerRepository.findById(id);
        if (organizerEntity.isEmpty()) {
            throw new OrganizerNotExistException();
        }

        return organizerEntity.get();
    }

    public void deleteOrganizer(Integer id) {
        Optional<OrganizerEntity> organizerEntity = organizerRepository.findById(id);
        if (organizerEntity.isEmpty()) {
            throw new OrganizerNotExistException();
        }

        eventRepository.deleteAll(organizerEntity.get().getEvents());
        organizerRepository.delete(organizerEntity.get());
    }

    public void createOrganizer(OrganizerCreateRequest organizerCreateRequest) {
        organizerRepository.save(OrganizerEntity.builder()
                .name(organizerCreateRequest.getName())
                .type(organizerCreateRequest.getType())
                .userRole(organizerCreateRequest.getUserRole())
                .legalEntityId(organizerCreateRequest.getLegalEntityId())
                .events(new ArrayList<>())
                .build());
    }

    public void updateOrganizer(Integer id, OrganizerUpdateRequest organizerUpdateRequest) {
        Optional<OrganizerEntity> organizerEntity = organizerRepository.findById(id);
        if (organizerEntity.isEmpty()) {
            throw new OrganizerNotExistException();
        }

        organizerRepository.save(organizerEntity.get().toBuilder()
                .name(organizerUpdateRequest.getName())
                .type(organizerUpdateRequest.getType())
                .build()
        );
    }
}
