package ecoton.ecotonbackend.mapper;

import ecoton.ecotonbackend.dto.EventDTO;
import ecoton.ecotonbackend.entity.EventEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

    EventMapper instance = Mappers.getMapper(EventMapper.class);

    @AfterMapping
    default EventDTO map(EventEntity eventEntity) {
        return EventDTO.builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .description(eventEntity.getDescription())
                .mapsId(eventEntity.getMapsId())
                .dateTime(eventEntity.getDateTime())
                .imageName(eventEntity.getImageName())
                .participantsAmount(eventEntity.getParticipants().size())
                .orgName(eventEntity.getOrganizer().getName())
                .build();
    }
}
