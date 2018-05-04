package com.waracle.cakemgr.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndAndPassword(String username, String password);

}
