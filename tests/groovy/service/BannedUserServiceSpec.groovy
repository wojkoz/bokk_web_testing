package service

import bookweb.domain.dto.BannedUserDto
import bookweb.domain.dto.CreateUserDto
import bookweb.domain.dto.UserDto
import bookweb.domain.mapper.BannedUserDtoMapper
import bookweb.domain.mapper.BannedUserListMapper
import bookweb.domain.mapper.BannedUserMapper
import bookweb.domain.mapper.UserDtoMapper
import bookweb.domain.mapper.UserListMapper
import bookweb.domain.mapper.UserMapper
import bookweb.domain.repository.BannedUserRepository
import bookweb.domain.repository.UserRepository
import bookweb.service.BannedUserService
import bookweb.service.UserService
import bookweb.service.serviceImpl.BannedUserServiceImpl
import bookweb.service.serviceImpl.UserServiceImpl
import domain.respository.FailedBannedUserRepository
import domain.respository.FailedUserRepository
import spock.lang.Specification

class BannedUserServiceSpec extends Specification{

    BannedUserService bannedUserService
    BannedUserDtoMapper bannedUserDtoMapper = new BannedUserDtoMapper()
    BannedUserListMapper bannedUserListMapper = new BannedUserListMapper()
    BannedUserMapper bannedUserMapper = new BannedUserMapper()
    BannedUserRepository bannedUserRepository = new FailedBannedUserRepository()

    UserService userService
    UserDtoMapper userDtoMapper = new UserDtoMapper()
    UserListMapper listMapper = new UserListMapper()
    UserMapper userMapper = new UserMapper()
    UserRepository userRepository = new FailedUserRepository()
    ArrayList<UserDto> createUserList = new ArrayList<>()

    def setup(){
        userService = new UserServiceImpl(userDtoMapper, listMapper, userMapper, userRepository, bannedUserRepository)
        bannedUserService = new BannedUserServiceImpl(bannedUserRepository, bannedUserListMapper, bannedUserDtoMapper, bannedUserMapper)

        CreateUserDto userDto = new CreateUserDto()
        userDto.setAdmin(false)
        userDto.setEmail("alek@ciur.com")
        userDto.setPassword("Ciu123")
        userDto.setName("Aleksander")
        userDto.setSurname("Ciurej")

        CreateUserDto userDto2 = new CreateUserDto()
        userDto.setAdmin(false)
        userDto.setEmail("woj@gmail.com")
        userDto.setPassword("Wojkoz123")
        userDto.setName("Wojciech")
        userDto.setSurname("Koziol")

        UserDto user = userService.createUser(userDto)
        UserDto user2 = userService.createUser(userDto2)

        createUserList.push(user)
        createUserList.push(user2)
    }

    def "should return list of all banned users"() {
        given: "ban 2 users"
        userService.banUser(createUserList[0].getUserId())
        userService.banUser(createUserList[1].getUserId())

        when: "on call method findAll()"
        ArrayList<BannedUserDto> bannedUsers = bannedUserService.findAll()

        then: "return list of all banned users"
        bannedUsers.size() == 2
    }

    def "should return banned user by given id"() {
        given: "ban user"
        Long userId = createUserList[0].getUserId()
        userService.banUser(userId)

        when: "on call method findById()"
        Optional<BannedUserDto> bannedUser = bannedUserService.findById(userId)

        then: "should return banned user"
        !bannedUser.isEmpty()
        bannedUser.isPresent()
        bannedUser.get().getBannedUserId() == userId
    }
}
