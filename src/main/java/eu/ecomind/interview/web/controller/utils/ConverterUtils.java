package eu.ecomind.interview.web.controller.utils;

import eu.ecomind.interview.db.entities.DepartmentDb;
import eu.ecomind.interview.web.model.DepartmentRead;
import eu.ecomind.interview.domain.entities.DepartmentEntity;
import org.springframework.beans.BeanUtils;

public class ConverterUtils {

    private ConverterUtils() {}

    public static DepartmentRead departmentToWeb(DepartmentEntity departmentEntity) {
        DepartmentRead departmentWeb = new DepartmentRead();
        BeanUtils.copyProperties(departmentEntity, departmentWeb);
        return departmentWeb;
    }

    public static DepartmentEntity webToDepartment(DepartmentRead departmentRead){
        DepartmentEntity entity = new DepartmentEntity();
        BeanUtils.copyProperties(departmentRead,entity);
        return entity;
    }

    public static DepartmentDb pojoToEntity (DepartmentEntity pojo){
        var converted = new DepartmentDb();
        BeanUtils.copyProperties(pojo,converted);
        return converted;
    }

    public static DepartmentEntity entityToPojo (DepartmentDb entity){
        var converted = new DepartmentEntity();
        BeanUtils.copyProperties(entity,converted);
        return converted;
    }

    public static DepartmentDb webToEntity(DepartmentRead departmentRead){
        return pojoToEntity(webToDepartment(departmentRead));
    }
}
