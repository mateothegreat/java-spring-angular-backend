package com.waracle.cakemgr.clients;

import com.waracle.cakemgr.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ClientsRepository extends JpaRepository<Client, Long> {

}
