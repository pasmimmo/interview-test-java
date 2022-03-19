package eu.ecomind.interview.web.controller.utils;

import eu.ecomind.interview.db.entities.DepartmentDb;
import eu.ecomind.interview.domain.entities.DepartmentEntity;
import eu.ecomind.interview.web.model.DepartmentRead;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Objects;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class ConverterUtilsTest {
    private static final String ADDRESS = "Milky way";
    private static final int ID = 1;
    private static final String NAME = "Earth";



    @Test
    public void pojoToEntity() {
        var oracle = generateDepartmentRead();
        var test = ConverterUtils.pojoToEntity(generateDepartmentEntity());
        Assert.assertTrue(
                Objects.equals(oracle.getAddress(), test.getAddress()) &&
                        Objects.equals(oracle.getName(), test.getName()) &&
                        Objects.equals(oracle.getId(), test.getId())
        );
    }

    @Test
    public void entityToPojo() {
        var oracle = generateDepartmentRead();
        var test = ConverterUtils.entityToPojo(generateDepartmentDB());
        Assert.assertTrue(
                Objects.equals(oracle.getAddress(), test.getAddress()) &&
                        Objects.equals(oracle.getName(), test.getName()) &&
                        Objects.equals(oracle.getId(), test.getId())
        );
    }

    @Test
    public void webToEntity() {
        var oracle = generateDepartmentRead();
        var test = ConverterUtils.webToEntity(generateDepartmentRead());
        Assert.assertTrue(
                Objects.equals(oracle.getAddress(), test.getAddress()) &&
                        Objects.equals(oracle.getName(), test.getName()) &&
                        Objects.equals(oracle.getId(), test.getId())
        );
    }

    private DepartmentDb generateDepartmentDB() {
        var departmentDB = new DepartmentDb();
        departmentDB.setName(NAME);
        departmentDB.setAddress(ADDRESS);
        departmentDB.setId(ID);
        return departmentDB;
    }

    private DepartmentEntity generateDepartmentEntity(){
        var firstDep = new DepartmentEntity();
        firstDep.setAddress(ADDRESS);
        firstDep.setId(ID);
        firstDep.setName(NAME);
        return firstDep;
    }

    private DepartmentRead generateDepartmentRead(){
        DepartmentRead mockInputData= new DepartmentRead();
        mockInputData.setAddress(ADDRESS);
        mockInputData.setId(ID);
        mockInputData.setName(NAME);
        return mockInputData;
    }
}