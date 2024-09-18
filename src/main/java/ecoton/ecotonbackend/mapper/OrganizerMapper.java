package ecoton.ecotonbackend.mapper;

import ecoton.ecotonbackend.dto.OrganizerDTO;
import ecoton.ecotonbackend.entity.OrganizerEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizerMapper {

    OrganizerMapper instance = Mappers.getMapper(OrganizerMapper.class);

    @AfterMapping
    default OrganizerDTO map(OrganizerEntity organizerEntity) {
        return OrganizerDTO.builder()
                .id(organizerEntity.getId())
                .name(organizerEntity.getName())
                .type(organizerEntity.getType())
                .build();
    }
}
