package com.nighthawk.spring_portfolio.mvc.gamesession;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
public interface GamesessionJpaRepository extends JpaRepository<Gamesession, Long> {
    Gamesession findByGameId(int gameId); 
    List<Gamesession> findAllByGameId(int gameId);
    List<Gamesession> findAllByUserId(int userId);

}
