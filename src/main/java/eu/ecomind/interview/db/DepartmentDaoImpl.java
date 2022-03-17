package eu.ecomind.interview.db;

import eu.ecomind.interview.db.entities.DepartmentDb;
import eu.ecomind.interview.domain.DepartmentDao;
import eu.ecomind.interview.domain.entities.DepartmentEntity;
import eu.ecomind.interview.web.controller.utils.ConverterUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
interface DepartmentRepository extends JpaRepository<DepartmentDb, Integer> {
}

@Component
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    DepartmentRepository repository;

    @Override
    public Collection<DepartmentEntity> list() {
        return repository.findAll().stream().map(db -> {
            DepartmentEntity entity = new DepartmentEntity();
            BeanUtils.copyProperties(db, entity);
            return entity;
        }).collect(Collectors.toList());
    }

    @Override
    public DepartmentEntity createDepartment(DepartmentEntity departmentEntity){
        var entity = repository.save(ConverterUtils.pojoToEntity(departmentEntity));
        return ConverterUtils.entityToPojo(entity);
    }

    @Override
    public DepartmentEntity findDepartmentById(Integer id) {
        var department = repository.findById(id);
        if (department.isPresent())
            return ConverterUtils.entityToPojo(department.get());
        throw new NoSuchElementException();
    }

    @Override
    public DepartmentEntity removeDepartment(DepartmentEntity data) {
        var dbData=repository.findById(data.getId());
        if (dbData.isPresent()){
            data = ConverterUtils.entityToPojo(dbData.get());
        }
        repository.delete(ConverterUtils.pojoToEntity(data));
        return data;
    }
}
