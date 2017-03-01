package org.rbo.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.rbo.model.Authority;
import org.rbo.model.User;
import org.rbo.service.dto.UserDto;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Convert from UserDTO to User model
 * @author vitalii.levash
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Convert to USER and ignore custom fields
     * @param userDto transformation model of user entity
     * @return mapped user entity
     */
    @Mappings({
            @Mapping(target = "accountNonExpired", ignore = true),
            @Mapping(target = "accountNonLocked", ignore = true),
            @Mapping(target = "credentialsNonExpired", ignore = true),
            @Mapping(target = "phoneBookList",ignore = true),
            @Mapping(target = "authorities",ignore = true)

            })
    User toUser(UserDto userDto);

    /**
     * Convert USER entity to transformation object. Hide meta columns.
     * @param user entity to convert
     * @return converted dto
     */
    @Mappings({
            @Mapping(target = "authorities",ignore = true)
    })
    UserDto toUserDto(User user);

    /**
     * Default convert authorities set to transformation object set of Strings.
     * @param authorities set
     * @return converted strings authorities
     */
    default Set<String> stringsFromAuthorities (Set<Authority> authorities) {
        return authorities.stream().map(Authority::getName)
                .collect(Collectors.toSet());
    }

    /**
     * Default convert authorities from transformation object
     * @param strings authorities set
     * @return converted authorities
     */
    default Set<Authority> authoritiesFromStrings(Set<String> strings) {
        return strings.stream().map(string -> {
            Authority auth = new Authority();
            auth.setName(string);
            return auth;
        }).collect(Collectors.toSet());
    }
}
