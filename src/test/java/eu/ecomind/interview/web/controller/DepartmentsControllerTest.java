package eu.ecomind.interview.web.controller;

import eu.ecomind.interview.domain.entities.DepartmentEntity;
import eu.ecomind.interview.domain.services.DepartmentService;
import eu.ecomind.interview.web.controller.utils.ConverterUtils;
import eu.ecomind.interview.web.model.DepartmentRead;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentsControllerTest {
    private static final String ADDRESS = "Milky way";
    private static final int ID = 1;
    private static final String NAME = "Earth";

    @InjectMocks
    private DepartmentsController departmentsController;
    @Mock
    private DepartmentService departmentService;

    @Before
    public void setUP(){
        Mockito.when(departmentService.saveDepartment(Mockito.any()))
                .thenReturn(generateDepartmentEntity());
        Mockito.when(departmentService.listDepartments())
                .thenReturn(generateListDepartments());
        Mockito.when(departmentService.getById(Mockito.anyInt()))
                .thenReturn(generateDepartmentEntity());
        Mockito.when(departmentService.removeDepartment(Mockito.any()))
                .thenReturn(generateDepartmentEntity());
    }

    @Test
    public void getDepartments() {
        var oracle = generateListDepartments().stream().map(ConverterUtils::departmentToWeb).collect(Collectors.toList());
        Assert.assertEquals(departmentsController.getDepartments(),oracle);
    }

    @Test
    public void createDepartment() {

        var test=departmentsController.createDepartment(generateDepartmentRead());
        Assert.assertTrue(
                test.getId() == ID &&
                        Objects.equals(test.getAddress(), ADDRESS) &&
                        Objects.equals(test.getName(), NAME)
        );
    }

    @Test
    public void getDepartmentById() {
        var oracle = ConverterUtils.departmentToWeb(generateDepartmentEntity());
        var test = departmentsController.getDepartmentById(1);
        Assert.assertEquals(test,oracle);
    }

    @Test
    public void getDepartmentByIdErrorCase(){
        Mockito.when(departmentService.getById(Mockito.anyInt()))
                .thenThrow(new NoSuchElementException());
        Assert.assertNotNull(departmentsController.getDepartmentById(ID));
    }

    @Test
    public void updateDepartmentById() {
        var oracle = ConverterUtils.departmentToWeb(generateDepartmentEntity());
        var test = departmentsController.updateDepartmentById(1,generateDepartmentRead());
        Assert.assertEquals(test,oracle);
    }

    @Test
    public void patchDepartmentById() {
        var testWithVoidParameter = new DepartmentRead();
        testWithVoidParameter.setAddress(ADDRESS);
        var test = departmentsController.patchDepartmentById(1,testWithVoidParameter);
        var oracle = generateDepartmentRead();
        Assert.assertEquals(oracle,test);

        testWithVoidParameter = new DepartmentRead();
        testWithVoidParameter.setId(ID);
        Assert.assertEquals(oracle,test);

        testWithVoidParameter = new DepartmentRead();
        testWithVoidParameter.setName(NAME);
        Assert.assertEquals(oracle,test);

        testWithVoidParameter = generateDepartmentRead();
        testWithVoidParameter.setName(null);
        Assert.assertEquals(oracle,test);

        testWithVoidParameter = generateDepartmentRead();
        testWithVoidParameter.setAddress(null);
        Assert.assertEquals(oracle,test);

        testWithVoidParameter = generateDepartmentRead();
        testWithVoidParameter.setId(null);
        Assert.assertEquals(oracle,test);

    }

    @Test
    public void removeDepartmentById() {
        var oracle = new ResponseEntity<>(generateDepartmentEntity(), HttpStatus.ACCEPTED);
        Assert.assertEquals(oracle.getStatusCode(), departmentsController.removeDepartmentById(ID).getStatusCode());
    }

    @Test
    public void incompleteData() {
        Assert.assertTrue(
                DepartmentsController.incompleteData(
                        generateDepartmentReadMock(true)
                ));
        Assert.assertFalse(
                DepartmentsController.incompleteData(
                        generateDepartmentReadMock(false)
                ));
    }

    private DepartmentRead generateDepartmentRead(){
        DepartmentRead mockInputData= new DepartmentRead();
        mockInputData.setAddress(ADDRESS);
        mockInputData.setId(ID);
        mockInputData.setName(NAME);
        return mockInputData;
    }

    private DepartmentRead generateDepartmentReadMock(boolean voidMock){
        if (voidMock)
            return new DepartmentRead();
        DepartmentRead departmentRead = new DepartmentRead();
        departmentRead.setId(100);
        departmentRead.setName("Testing Department");
        departmentRead.setAddress("quality road 5");
        return departmentRead;
    }

    private DepartmentEntity generateDepartmentEntity(){
        var firstDep = new DepartmentEntity();
        firstDep.setAddress(ADDRESS);
        firstDep.setId(ID);
        firstDep.setName(NAME);
        return firstDep;
    }

    private Collection<DepartmentEntity> generateListDepartments(){
        Collection<DepartmentEntity> myList = new ArrayList<>();
        myList.add(generateDepartmentEntity());

        var secondDep = new DepartmentEntity();
        var thirdDep = new DepartmentEntity();
        secondDep.setId(2);
        secondDep.setName("Mars");
        secondDep.setAddress("Milky Way");
        thirdDep.setId(3);
        thirdDep.setName("Venus");
        thirdDep.setAddress(ADDRESS);
        myList.add(secondDep);
        myList.add(thirdDep);

        return myList;
    }
}