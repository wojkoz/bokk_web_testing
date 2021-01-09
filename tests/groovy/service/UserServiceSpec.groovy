package service

import bookweb.domain.dto.CreateUserDto
import bookweb.domain.dto.UserDto
import bookweb.domain.mapper.UserDtoMapper
import bookweb.domain.mapper.UserListMapper
import bookweb.domain.mapper.UserMapper
import bookweb.domain.repository.BannedUserRepository
import bookweb.domain.repository.UserRepository
import bookweb.service.UserService
import bookweb.service.serviceImpl.UserServiceImpl
import domain.respository.FailedBannedUserRepository
import domain.respository.FailedUserRepository
import spock.lang.Specification

class UserServiceSpec extends Specification{

    UserDtoMapper userDtoMapper = new UserDtoMapper()
    UserListMapper listMapper = new UserListMapper()
    UserMapper userMapper = new UserMapper()
    UserService userService

    List<CreateUserDto> createUserDtoList

    def setup(){
        CreateUserDto userDto = new CreateUserDto()
        userDto.setAdmin(false)
        userDto.setEmail("w@w.pl")
        userDto.setName("WOjciech")
        userDto.setPassword("12345")
        userDto.setSurname("koz")

        CreateUserDto userDto2 = new CreateUserDto()
        userDto2.setAdmin(false)
        userDto2.setEmail("q@q.pl")
        userDto2.setName("query")
        userDto2.setPassword("12345")
        userDto2.setSurname("iop")

        createUserDtoList = new ArrayList<>()
        createUserDtoList.push(userDto)
        createUserDtoList.push(userDto2)

        UserRepository userRepository = new FailedUserRepository()
        BannedUserRepository bannedUserRepository = new FailedBannedUserRepository()
        userService = new UserServiceImpl(userDtoMapper, listMapper, userMapper, userRepository, bannedUserRepository)
    }



    def "should add new user"() {
        when: "add a new user"
        CreateUserDto userDto = new CreateUserDto()
        userDto.setAdmin(false)
        userDto.setEmail("w@w.pl")
        userDto.setName("WOjciech")
        userDto.setPassword("12345")
        userDto.setSurname("koz")

        def user = userService.createUser(userDto)
        then: "user is created"
        userService.getById(user.getUserId()).isPresent()
    }

    def "getAll should return 2 users"(){
        given: "2 users are in base"
        userService.createUser(createUserDtoList[0])
        userService.createUser(createUserDtoList[1])

        when: "getAll method run"
        List<UserDto> users = userService.getAll()

        then: "list of users is returned"
        users.size() == 2
    }

    def "should return user with given id"(){
        given: "2 users are in base"
        userService.createUser(createUserDtoList[0])
        UserDto userDto = userService.createUser(createUserDtoList[1])

        when:"getById method runs with given id"
        Optional<UserDto> user = userService.getById(userDto.getUserId())

        then:"user with given id should be returned"
        user.isPresent()
        user.get().getUserId() == userDto.getUserId()
    }

    def "should return Optional of nullable when given id is wrong"(){
        given: "2 users are in base"
        userService.createUser(createUserDtoList[0])
        UserDto userDto = userService.createUser(createUserDtoList[1])

        when:"getById method runs with given id"
        Optional<UserDto> user = userService.getById(4)

        then:"user with given id should be returned"
        !user.isPresent()
    }

    def "should return user with given email"(){
        given: "2 users are in base"
        userService.createUser(createUserDtoList[0])
        UserDto userDto = userService.createUser(createUserDtoList[1])

        when:"getById method runs with given email"
        Optional<UserDto> user = userService.getByEmail(userDto.getEmail())

        then:"user with given email should be returned"
        user.isPresent()
        user.get().getEmail() == userDto.getEmail()
    }

    def "should return Optional of nullable when given email is wrong"(){
        given: "2 users are in base"
        userService.createUser(createUserDtoList[0])
        UserDto userDto = userService.createUser(createUserDtoList[1])

        when:"getById method runs with given email"
        Optional<UserDto> user = userService.getByEmail("uuu@alee.email")

        then:"user with given email should be returned"
        !user.isPresent()
    }

    def "should delete user by given id"(){
        given: "2 users are in base"
        UserDto userDto = userService.createUser(createUserDtoList[0])
        userService.createUser(createUserDtoList[1])

        when: "deleteById method runs"
        Optional<UserDto> deletedUser = userService.deleteById(userDto.getUserId())

        then: "should be 1 user in base, should return deleted user"
        userService.getAll().size() == 1
        deletedUser.isPresent()
        deletedUser.get().getUserId() == userDto.getUserId()

    }

    def "should delete user by given email"(){
        given: "2 users are in base"
        UserDto userDto = userService.createUser(createUserDtoList[0])
        userService.createUser(createUserDtoList[1])

        when: "deleteByEmail method runs"
        Optional<UserDto> deletedUser = userService.deleteByEmail(userDto.getEmail())

        then: "should be 1 user in base, should return deleted user"
        userService.getAll().size() == 1
        deletedUser.isPresent()
        deletedUser.get().getEmail() == userDto.getEmail()

    }

    def "should return true for existing email"(){
        given: "2 users are in base"
        UserDto userDto = userService.createUser(createUserDtoList[0])
        userService.createUser(createUserDtoList[1])

        when: "checkIfEmailExists method runs"
        boolean email = userService.checkIfEmailExists(userDto.getEmail())

        then: "should return true"
        email
    }

    def "should return false for not existing email"(){
        given: "2 users are in base"
        UserDto userDto = userService.createUser(createUserDtoList[0])
        userService.createUser(createUserDtoList[1])

        when: "checkIfEmailExists method runs"
        boolean email = userService.checkIfEmailExists("ale@gra.pe")

        then: "should return false"
        !email
    }

    def "should update user in database"(){
        given: "2 users are in base"
        UserDto userDto = userService.createUser(createUserDtoList[0])
        userService.createUser(createUserDtoList[1])

        userDto.setSurname("uuuu")

        when: "updateUser method runs"
        UserDto updatedUser = userService.updateUser(userDto)

        then: "user should have surname uuuu"
        updatedUser.getSurname() == "uuuu"

    }

}
