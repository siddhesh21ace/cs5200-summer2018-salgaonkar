package edu.northeastern.cs5200.person;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE p.username=:username")
    Iterable<Person> findPersonByUsername(@Param("username") String username);

    @Query("SELECT p FROM Person p WHERE p.username=:username AND p.password=:password")
    Iterable<Person> findUserByCredentials(@Param("username") String username,
                                           @Param("password") String password);
}
