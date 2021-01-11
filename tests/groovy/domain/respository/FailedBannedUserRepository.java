package domain.respository;

import bookweb.domain.dto.BannedUserDto;
import bookweb.domain.entity.BannedUser;
import bookweb.domain.entity.User;
import bookweb.domain.repository.BannedUserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

public class FailedBannedUserRepository implements BannedUserRepository {

    Map<Long, BannedUser> bannedUsers = new HashMap<>();

    @Override
    public List<BannedUser> findAll() {
        return new ArrayList<>(bannedUsers.values());
    }

    @Override
    public List<BannedUser> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<BannedUser> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<BannedUser> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(BannedUser entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends BannedUser> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends BannedUser> S save(S entity) {
        Long id = new Random().nextLong();
        bannedUsers.put(id, entity);
        entity.setBannedUserId(id);
        return entity;
    }

    @Override
    public <S extends BannedUser> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BannedUser> findById(Long aLong) {
        return Optional.ofNullable(bannedUsers.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends BannedUser> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<BannedUser> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BannedUser getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends BannedUser> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BannedUser> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends BannedUser> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends BannedUser> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BannedUser> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BannedUser> boolean exists(Example<S> example) {
        return false;
    }
}
