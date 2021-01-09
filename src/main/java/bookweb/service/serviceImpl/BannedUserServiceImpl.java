package bookweb.service.serviceImpl;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.BannedUserDto;
import bookweb.domain.entity.BannedUser;
import bookweb.domain.repository.BannedUserRepository;
import bookweb.service.BannedUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BannedUserServiceImpl implements BannedUserService {
    private final BannedUserRepository bannedUserRepository;
    private final Converter<List<BannedUser>, List<BannedUserDto>> bannedUserListMapper;
    private final Converter<BannedUser,BannedUserDto> bannedUserDtoMapper;
    private final Converter<BannedUserDto, BannedUser> bannedUserMapper;

    public BannedUserServiceImpl(BannedUserRepository bannedUserRepository,
                                 Converter<List<BannedUser>, List<BannedUserDto>> bannedUserListMapper,
                                 Converter<BannedUser, BannedUserDto> bannedUserDtoMapper,
                                 Converter<BannedUserDto, BannedUser> bannedUserMapper) {
        this.bannedUserRepository = bannedUserRepository;
        this.bannedUserListMapper = bannedUserListMapper;
        this.bannedUserDtoMapper = bannedUserDtoMapper;
        this.bannedUserMapper = bannedUserMapper;
    }

    @Override
    public Optional<BannedUserDto> findById(Long userId) {
        Optional<BannedUser> bannedUser = bannedUserRepository.findById(userId);
        if(bannedUser.isPresent()){
            return bannedUser.map(bannedUserDtoMapper::convert);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public List<BannedUserDto> findAll() {
        List<BannedUser> bannedUsers = bannedUserRepository.findAll();
        return bannedUserListMapper.convert(bannedUsers);
    }

    @Override
    public void deleteBannedUser(BannedUserDto bannedUserDto) {
        BannedUser bannedUser = bannedUserMapper.convert(bannedUserDto);
        bannedUserRepository.delete(bannedUser);
    }
}
