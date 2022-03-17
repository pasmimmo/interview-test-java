package eu.ecomind.interview.web.controller;

import eu.ecomind.interview.domain.services.DepartmentService;
import eu.ecomind.interview.web.controller.utils.ConverterUtils;
import eu.ecomind.interview.web.model.DepartmentRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
}
