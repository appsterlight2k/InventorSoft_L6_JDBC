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
        /* test users: */
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);

        System.out.println("--->>> TEST USERS <<<---");
        //add:
        User newUser = new User("NewUser_1", "Lastname", "+1-000-00000", "user@gmail.com", "123", "description");
        Long userId = userService.add(newUser);

        System.out.println(">>> new user id: " + userId);

        //get all:
        System.out.println(">>> get all users: " + userService.getAll());

        //get with id = 2
        Optional<User> userOpt = userService.get(2L);
        System.out.println(">>> get user with id = 2: " + (userOpt.isPresent() ? userOpt.get() : "error! User wasn't saved"));

        //update:
        User user2 = userOpt.get();
        user2.setFirstname("--->> New user with changed name");
        System.out.println(">>> update user id = 2: " + userService.update(user2));

        //delete with id = 3:
        System.out.println(">>> delete user with id = 3: " + userService.delete(3L));

        //output all:
        System.out.println(">>> get all users after changes: " + userService.getAll());

        System.out.println("\n-----------------------------------------------------------------\n");

        CompanyDao companyDao = new CompanyDaoImpl();
        CompanyService companyService = new CompanyServiceImpl(companyDao);

        System.out.println("--->>> TEST COMPANIES <<<---");

        //add:
        Company newCompany = new Company("Mercedes", "+1-000-000-111", "On the beach", "Vacanda", "North Pole", "000-0000", "Atlantis");
        Long companyId = companyService.add(newCompany);

        System.out.println(">>> new company id: " + companyId);

        //get all:
        System.out.println(">>> get all company: " + companyService.getAll());

        //get with id = 3
        Optional<Company> companyOptional = companyService.get(3L);
        System.out.println(">>> get company with id = 3: " + (companyOptional.isPresent() ? companyOptional.get() : "error! Company wasn't saved"));

        //update:
        Company company3 = companyOptional.get();
        company3.setCompanyName("--->> New company with changed name");
        System.out.println(">>> update company id = 3: " + companyService.update(company3));

        //delete with id = 3:
        System.out.println(">>> delete company with id = 1: " + companyService.delete(1L));

        //output all:
        System.out.println(">>> get all companies after changes: " + companyService.getAll());

    }
}
