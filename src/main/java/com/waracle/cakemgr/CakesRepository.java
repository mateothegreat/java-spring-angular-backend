package com.waracle.cakemgr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CakesRepository extends JpaRepository<Cake, Long> {

    List<Cake> findAll();

}
