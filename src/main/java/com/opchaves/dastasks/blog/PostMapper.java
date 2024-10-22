package com.opchaves.dastasks.blog;

import static org.mapstruct.MappingConstants.ComponentModel.JAKARTA_CDI;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.time.LocalDateTime;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper to map all fields from a {@link Post} to another {@link Post}.
 *
 */
@Mapper(componentModel = JAKARTA_CDI, imports = { LocalDateTime.class })
public interface PostMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  public Post toEntity(Post input);

  @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  public void partialUpdate(Post input, @MappingTarget Post target);
}
