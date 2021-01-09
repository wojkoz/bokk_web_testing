package bookweb.service;

import bookweb.domain.dto.BannedUserDto;

import java.util.List;
import java.util.Optional;

public interface BannedUserService {
    Optional<BannedUserDto> findById(Long userId);
    List<BannedUserDto> findAll();
    void deleteBannedUser(BannedUserDto bannedUserDto);
}
