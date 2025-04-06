package database;

import database.DAO.DepartmentDAO;
import model.Department;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentDAOTest {

    private DepartmentDAO departmentDAO;

    @BeforeEach
    void setUp() {
        departmentDAO = new DepartmentDAO();
    }

    @Test
    void testAddDepartment() throws SQLException {
        Department department = new Department(0, "Research", "New York", 50000);
        boolean result = departmentDAO.addDepartment(department);
        assertTrue(result, "Department should be added successfully.");
    }

    @Test
    void testGetAllDepartments() throws SQLException {
        List<Department> departments = departmentDAO.getAllDepartments();
        assertNotNull(departments, "Department list should not be null.");
        // Optionally check that it's not empty if you already added some
        // assertFalse(departments.isEmpty(), "Department list should not be empty.");
    }

    @Test
    void testUpdateDepartment() throws SQLException {
        // First, add a department to update
        Department department = new Department(0, "HR", "Boston", 40000);
        departmentDAO.addDepartment(department);

        // Get all and pick last one (just added)
        List<Department> departments = departmentDAO.getAllDepartments();
        Department last = departments.get(departments.size() - 1);

        // Update
        last.setName("HR Updated");
        boolean updated = departmentDAO.updateDepartment(last);
        assertTrue(updated, "Department should be updated successfully.");
    }

    @Test
    void testDeleteDepartment() throws SQLException {
        Department department = new Department(0, "Temp", "Testville", 12345);
        departmentDAO.addDepartment(department);
        List<Department> departments = departmentDAO.getAllDepartments();
        Department last = departments.get(departments.size() - 1);

        boolean deleted = departmentDAO.deleteDepartment(last.getId());
        assertTrue(deleted, "Department should be deleted successfully.");
    }
}
