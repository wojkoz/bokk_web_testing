package service

import bookweb.domain.dto.CreateUserDto
import bookweb.domain.dto.UserAuth
import bookweb.domain.mapper.UserDtoMapper
import bookweb.domain.mapper.UserListMapper
import bookweb.domain.mapper.UserMapper
import bookweb.domain.repository.BannedUserRepository
import bookweb.domain.repository.UserRepository
import bookweb.service.AuthenticateService
import bookweb.service.UserService
import bookweb.service.serviceImpl.AuthenticateServiceImpl
import bookweb.service.serviceImpl.UserServiceImpl
import domain.respository.FailedBannedUserRepository
import domain.respository.FailedUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Specification

class AuthenticateServiceSpec extends Specification{
    UserDtoMapper userDtoMapper = new UserDtoMapper()
    UserListMapper listMapper = new UserListMapper()
    UserMapper userMapper = new UserMapper()
    UserService userService
    AuthenticateService authenticateService

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
        authenticateService = new AuthenticateServiceImpl(userRepository)
    }

    def "should authenticate user"(){
        given:" 2 users in database"
        userService.createUser(createUserDtoList[0])
        userService.createUser(createUserDtoList[1])
        UserAuth userAuth = new UserAuth("w@w.pl", "12345" )

        when: "authUser method runs"
        UserDetails userDetails = authenticateService.authUser(userAuth)

        then:" user is authenticated"
        noExceptionThrown()
        userDetails.password == userAuth.password
        userDetails.username == userAuth.login

    }

    def "should not authenticate user with wrong login"(){
        given:" 2 users in database"
        userService.createUser(createUserDtoList[0])
        userService.createUser(createUserDtoList[1])
        UserAuth userAuth = new UserAuth("w.pl", "12345" )

        when: "authUser method runs"
        UserDetails userDetails = authenticateService.authUser(userAuth)
        then:" user is authenticated"
        userDetails == null
        thrown(UsernameNotFoundException)

    }
}
