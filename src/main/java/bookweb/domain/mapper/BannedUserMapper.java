package bookweb.domain.mapper;

import bookweb.domain.converter.Converter;
import bookweb.domain.dto.BannedUserDto;
import bookweb.domain.entity.BannedUser;
import org.springframework.stereotype.Component;

@Component
public class BannedUserMapper implements Converter<BannedUserDto, BannedUser> {
    @Override
    public BannedUser convert(BannedUserDto from) {
        BannedUser bannedUser = new BannedUser();

        bannedUser.setBannedUserId(from.getBannedUserId());
        bannedUser.setBanDate(from.getBanDate());
        bannedUser.setReason(from.getReason());

        return bannedUser;
    }
}
