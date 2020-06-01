package com.formation.spring5mvcjunit.service;

import java.util.List;
import java.util.Optional;

import com.formation.spring5mvcjunit.model.Widget;

public interface WidgetService {
    Optional<Widget> findById(Long id);
    List<Widget> findAll();
    Widget save(Widget widget);
    void deleteById(Long id);
}
