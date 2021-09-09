package eu.ecomind.interview.domain.services;

import eu.ecomind.interview.domain.DepartmentDao;
import eu.ecomind.interview.domain.entities.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    public Collection<DepartmentEntity> listDepartments() {
        return departmentDao.list();
    }
}