package eu.ecomind.interview.web.controller.utils;

import eu.ecomind.gshub.interview.web.model.DepartmentRead;
import eu.ecomind.interview.domain.entities.DepartmentEntity;
import org.springframework.beans.BeanUtils;

public class ConverterUtils {

    public static DepartmentRead departmentToWeb(DepartmentEntity departmentEntity) {
        DepartmentRead departmentWeb = new DepartmentRead();
        BeanUtils.copyProperties(departmentEntity, departmentWeb);
        return departmentWeb;
    }
}
