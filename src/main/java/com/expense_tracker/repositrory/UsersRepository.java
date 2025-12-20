package com.expense_tracker.repositrory;


import com.expense_tracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
//already we have in build operations:

//    🧠 Layman analogy
//    Think of JpaRepository like a toolbox.
//    It already has the basic tools (CRUD).
//    You only add extra tools when your project needs them (like “find expenses between two dates”).

//You already get, but we need to implement this logic in service layer:
//
//    save(entity) → Create or Update
//
//    findById(id) → Read one
//
//    findAll() → Read all
//
//    deleteById(id) → Delete one
//
//    deleteAll() → Delete all
//
//    So you don’t need to write getAllUsers() or deleteUserById() yourself.


//    so these are optional we created
    //find a single user by email
    Users findByEmail(String userEmail);

//   find a single user by username
    Users findByName(String userName);

//    find all users when created two time stamps
    List<Users> findByCreatedAt(LocalDateTime createdAt);

}
