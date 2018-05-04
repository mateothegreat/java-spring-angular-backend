package com.waracle.cakemgr.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UsersRepository extends JpaRepository<User, Long> {

}
