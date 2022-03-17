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

    public DepartmentEntity saveDepartment(DepartmentEntity newDepartment){
        return departmentDao.createDepartment(newDepartment);
    }

    public DepartmentEntity getById(Integer id) {
        return departmentDao.findDepartmentById(id);
    }
}