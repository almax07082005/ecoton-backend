package ecoton.ecotonbackend.service;

import ecoton.ecotonbackend.dto.OrganizerDTO;
import ecoton.ecotonbackend.entity.OrganizerEntity;
import ecoton.ecotonbackend.exceptions.OrganizerNotExistException;
import ecoton.ecotonbackend.mapper.OrganizerMapper;
import ecoton.ecotonbackend.repository.OrganizerRepository;
import ecoton.ecotonbackend.request.create.OrganizerCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    public OrganizerDTO getOrganizer(Integer id) {
        Optional<OrganizerEntity> organizerEntity = organizerRepository.findById(id);
        if (organizerEntity.isEmpty()) {
            throw new OrganizerNotExistException();
        }

        return OrganizerMapper.instance.map(organizerEntity.get());
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

        organizerRepository.deleteById(id);
    }

    public void createOrganizer(OrganizerCreateRequest organizerCreateRequest) {
        organizerRepository.save(OrganizerEntity.builder()
                .name(organizerCreateRequest.getName())
                .type(organizerCreateRequest.getType())
                .events(new ArrayList<>())
                .build());
    }
}
