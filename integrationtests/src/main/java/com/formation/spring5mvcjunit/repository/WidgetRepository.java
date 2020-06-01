package com.formation.spring5mvcjunit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.formation.spring5mvcjunit.model.Widget;

import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget, Long> {

    @Query(value = "SELECT w FROM Widget w WHERE w.name LIKE ?1")
    List<Widget> findWidgetsWithNameLike(String name);
}
