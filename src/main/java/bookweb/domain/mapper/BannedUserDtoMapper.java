package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.BannedUserDto;
import bookweb.domain.entity.BannedUser;
import org.springframework.stereotype.Component;

@Component
public class BannedUserDtoMapper implements Converter<BannedUser, BannedUserDto> {
    @Override
    public BannedUserDto convert(BannedUser from) {
        BannedUserDto bannedUserDto = new BannedUserDto();

        bannedUserDto.setBannedUserId(from.getBannedUserId());
        bannedUserDto.setBanDate(from.getBanDate());
        bannedUserDto.setReason(from.getReason());

        return bannedUserDto;
    }
}
