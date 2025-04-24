package com.pavan.Jpa.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pavan.Jpa.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	
	// Spring Data JPA automatically provides methods like:
    // save(), findById(), findAll(), deleteById(), count(), etc.

    // You can also define custom query methods here (as shown below)
	
	// Method using naming convention
    User findByEmail(String email);

    // Method using @Query with JPQL
    @Query("SELECT u FROM User u WHERE u.firstname = :firstname")
    List<User> findUsersByFirstName(@Param("firstname") String firstname);

    // Method using @Query with native SQL
    @Query(value = "SELECT * FROM users WHERE last_name = :lastname", nativeQuery = true)
    List<User> findUsersByLastNameNative(@Param("lastname") String lastname);
}
