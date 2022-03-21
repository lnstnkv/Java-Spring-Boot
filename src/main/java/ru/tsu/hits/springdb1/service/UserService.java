package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.springdb1.dto.CreateUserDto;
import ru.tsu.hits.springdb1.dto.UserDto;
import ru.tsu.hits.springdb1.entity.UserEntity;
import ru.tsu.hits.springdb1.repository.UserRepository;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public UserDto save(CreateUserDto createUserDto) {
        var entity = new UserEntity(
                UUID.randomUUID().toString(),
                createUserDto.getName(),
                createUserDto.getEmail(),
                createUserDto.getPassword()
        );

        var savedEntity = userRepository.save(entity);
        return new UserDto(
                savedEntity.getUuid(),
                savedEntity.getName(),
                savedEntity.getEmail(),
                savedEntity.getPassword()
        );
    }

}
