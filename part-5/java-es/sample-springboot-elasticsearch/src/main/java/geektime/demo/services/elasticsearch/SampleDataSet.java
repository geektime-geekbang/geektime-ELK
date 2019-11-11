package geektime.demo.services.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import geektime.demo.services.elasticsearch.model.Department;
import geektime.demo.services.elasticsearch.model.Employee;
import geektime.demo.services.elasticsearch.model.Organization;
import geektime.demo.services.elasticsearch.repository.EmployeeRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleDataSet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleDataSet.class);

    @Autowired
    EmployeeRepository repository;
    @Autowired
    ElasticsearchTemplate template;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            bulk(i);
        }
    }

    public void bulk(int ii) {
        try {
            // check if the index is existed
            if (!template.indexExists(Constants.EMPLOYEE_INDEX)) {
                template.createIndex(Constants.EMPLOYEE_INDEX);
            }
            ObjectMapper mapper = new ObjectMapper();
            List<IndexQuery> queries = new ArrayList<>();
            List<Employee> employees = rndEmployees();
            for (Employee employee : employees) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(employee.getId().toString());
                indexQuery.setSource(mapper.writeValueAsString(employee));
                //Set the index name & doc type
                indexQuery.setIndexName(Constants.EMPLOYEE_INDEX);
                indexQuery.setType(Constants.EMPLOYEE_INDEX_TYPE);
                queries.add(indexQuery);
            }
            if (queries.size() > 0) {
                template.bulkIndex(queries);
            }
            template.refresh(Constants.EMPLOYEE_INDEX);
            LOGGER.info("BulkIndex completed: {}", ii);
        } catch (Exception e) {
            LOGGER.error("Error bulk index", e);
        }
    }

    private List<Employee> rndEmployees(){
        List<Employee> employees = new ArrayList<>();
        int id = (int) repository.count();
        LOGGER.info("Starting from id: {}", id);
        for (int i = id; i < 100 + id; i++) {
            Random r = new Random();
            Faker faker = new Faker();
            Employee employee = new Employee();
            employee.setId((long) i);
            employee.setName(faker.name().username());
            employee.setAge(r.nextInt(60));
            employee.setPosition(faker.job().position());
            int departmentId = r.nextInt(5000);
            employee.setDepartment(new Department((long) departmentId, faker.company().name()));
            int organizationId = departmentId % 100;
            employee.setOrganization(new Organization((long) organizationId, "TestO" + organizationId, "Test Street No. " + organizationId));
            employees.add(employee);
        }
        return employees;

    }


}
