package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.BannedUserDto;
import bookweb.domain.entity.BannedUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BannedUserListMapper implements Converter<List<BannedUser>, List<BannedUserDto>> {
    @Override
    public List<BannedUserDto> convert(List<BannedUser> from) {
        return from.stream().map(
                bannedUser -> {
                    BannedUserDto bannedUserDto = new BannedUserDto();

                    bannedUserDto.setBannedUserId(bannedUser.getBannedUserId());
                    bannedUserDto.setBanDate(bannedUser.getBanDate());
                    bannedUserDto.setReason(bannedUser.getReason());

                    return bannedUserDto;
                }
        ).collect(Collectors.toList());
    }
}
