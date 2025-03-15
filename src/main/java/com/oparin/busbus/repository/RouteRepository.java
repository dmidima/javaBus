package com.oparin.busbus.repository;

import com.oparin.busbus.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query("SELECT r.duration FROM Route r")
    List<Integer> getAllDurations();

    @Query("SELECT r FROM Route r WHERE r.startPoint = ?1 AND r.endPoint = ?2")
    List<Route> findRoutesByStartAndEndPoint(String startPoint, String endPoint);

    @Modifying
    @Query("DELETE FROM Route r WHERE r.id = ?1")
    void deleteRouteById(int id);

}
