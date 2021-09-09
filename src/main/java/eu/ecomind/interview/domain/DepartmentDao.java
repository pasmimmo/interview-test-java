package eu.ecomind.interview.domain;

import eu.ecomind.interview.domain.entities.DepartmentEntity;

import java.util.Collection;

public interface DepartmentDao {
    Collection<DepartmentEntity> list();
}
