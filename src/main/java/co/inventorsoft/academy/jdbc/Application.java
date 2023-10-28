package co.inventorsoft.academy.jdbc;

import co.inventorsoft.academy.jdbc.dao.CompanyDao;
import co.inventorsoft.academy.jdbc.dao.impl.CompanyDaoImpl;
import co.inventorsoft.academy.jdbc.dao.impl.UserDaoImpl;
import co.inventorsoft.academy.jdbc.dao.UserDao;
import co.inventorsoft.academy.jdbc.exception.ServiceException;
import co.inventorsoft.academy.jdbc.model.Company;
import co.inventorsoft.academy.jdbc.model.User;
import co.inventorsoft.academy.jdbc.service.CompanyService;
import co.inventorsoft.academy.jdbc.service.UserService;
import co.inventorsoft.academy.jdbc.service.impl.CompanyServiceImpl;
import co.inventorsoft.academy.jdbc.service.impl.UserServiceImpl;

import java.util.Optional;

public class Application {
    public static void main(String[] args) throws ServiceException {
        /* test operations with User entity */
        testWithEntityUser();

        System.out.println("\n-----------------------------------------------------------------\n");

        /* test operations with Company entity */
        testWithEntityCompany();
    }

    private static void testWithEntityUser() throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);

        System.out.println("--->>> TEST USERS <<<---");

        //add:
        User newUser = new User("NewUser_1", "Lastname", "+1-000-00000", "user@gmail.com", "123", "description");
        Long userId = userService.add(newUser);

        System.out.println(">>> id of new user : " + userId);

        //get all:
        System.out.println("\n>>> get all users: \n" + userService.getAll());

        //get with id = 2
        Optional<User> userOptional = userService.get(2L);
        System.out.println("\n>>> get user with id = 2: \n" + (userOptional.isPresent() ? userOptional.get() : "error! User wasn't saved"));

        //update with id = 2:
        if (userOptional.isPresent()) {
            User user2 = userOptional.get();
            user2.setFirstname("*User with changed name");
            System.out.println("\n>>> update user id = 2: " + userService.update(user2));
        }

        //delete with id = 3:
        System.out.println("\n>>> delete user with id = 3: " + userService.delete(3L));

        //output all:
        System.out.println("\n>>> get all users after changes: \n" + userService.getAll());
    }

    private static void testWithEntityCompany() throws ServiceException {
        CompanyDao companyDao = new CompanyDaoImpl();
        CompanyService companyService = new CompanyServiceImpl(companyDao);

        System.out.println("--->>> TEST COMPANIES <<<---");

        //add:
        Company newCompany = new Company("Mercedes", "+1-000-000-111", "On the beach", "Vacanda", "North Pole", "000-0000", "Atlantis");
        Long companyId = companyService.add(newCompany);

        System.out.println("\n>>> new company id: " + companyId);

        //get all:
        System.out.println("\n>>> get all companies: \n" + companyService.getAll());

        //get with id = 3
        Optional<Company> companyOptional = companyService.get(3L);
        System.out.println("\n>>> get company with id = 3: \n" + (companyOptional.isPresent() ? companyOptional.get() : "error! Company wasn't saved"));

        //update with id = 3:
        if (companyOptional.isPresent()) {
            Company company3 = companyOptional.get();
            company3.setCompanyName("*Company with changed name");
            System.out.println("\n>>> update company with id = 3: " + companyService.update(company3));
        }

        //delete with id = 1:
        System.out.println("\n>>> delete company with id = 1: " + companyService.delete(1L));

        //output all:
        System.out.println("\n>>> get all companies after all changes: \n" + companyService.getAll());
    }


}
