package eu.ecomind.interview.web.controller;

import eu.ecomind.interview.domain.entities.DepartmentEntity;
import eu.ecomind.interview.domain.services.DepartmentService;
import eu.ecomind.interview.web.controller.utils.ConverterUtils;
import eu.ecomind.interview.web.model.DepartmentRead;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping(path = "")
    public List<DepartmentRead> getDepartments() {
        return departmentService.listDepartments().stream().map(ConverterUtils::departmentToWeb).collect(Collectors.toList());
    }

    @PostMapping(path = "")
    public DepartmentRead createDepartment(@RequestBody DepartmentRead postData){
        var savedEntity = departmentService.saveDepartment(ConverterUtils.webToDepartment(postData));
        return ConverterUtils.departmentToWeb(savedEntity);
    }

    @GetMapping("/{id}")
    public DepartmentRead getDepartmentById(@PathVariable Integer id) {
        try {
            DepartmentEntity department = departmentService.getById(id);
            return ConverterUtils.departmentToWeb(department);
        } catch (NoSuchElementException e) {
            return new DepartmentRead();
            //Todo handle not found
        }
    }

    @PostMapping(path = "/{id}")
    public DepartmentRead updateDepartmentById(@PathVariable Integer id,@RequestBody DepartmentRead postData){
        postData.id(id);
        //Todo handle mismatch
        var departmentFromDB = getDepartmentById(id);
        BeanUtils.copyProperties(postData,departmentFromDB);
        return createDepartment(departmentFromDB);
    }
}
