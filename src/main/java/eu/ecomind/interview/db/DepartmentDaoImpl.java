package eu.ecomind.interview.db;

import eu.ecomind.interview.db.entities.DepartmentDb;
import eu.ecomind.interview.domain.DepartmentDao;
import eu.ecomind.interview.domain.entities.DepartmentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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
}
