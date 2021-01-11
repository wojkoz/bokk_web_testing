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

    List<CreateUserDto> createUserDtoList

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

        createUserDtoList = new ArrayList<>()
        createUserDtoList.push(userDto)
        createUserDtoList.push(userDto2)
    }

    def "should return list of all banned users"() {
        given: "create 2 users then ban 2 users"
        UserDto userDto = userService.createUser(createUserDtoList[0])
        UserDto userDto2 = userService.createUser(createUserDtoList[1])

        userService.banUser(userDto.getUserId())
        userService.banUser(userDto2.getUserId())

        when: "on call method findAll()"
        List<UserDto> bannedUsers = bannedUserService.findAll()

        then: "return list of all banned users"
        bannedUsers.size() == 2
    }

    def "should return banned user by given id"() {
        given: "create user then ban user"
        UserDto userDto = userService.createUser(createUserDtoList[0])

        userService.banUser(userDto.getUserId())

        when: "on call method findById()"
        Optional<UserDto> bannedUser = bannedUserService.findById(userDto.getUserId())

        then: "should return user"

        bannedUser.isPresent()
        bannedUser.get().getUserId() == userDto.getUserId()
    }
}
