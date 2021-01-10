package service;

import model.User;
import static util.Resolver.resolveTemplate;

public class UserCreator {
    public static final String USER_LOGIN = "test.data.user.%s.login";
    public static final String USER_PASSWORD = "test.data.user.%s.password";

    public static User withCredentialsFromProperty(String orderNumber){
        orderNumber = orderNumber.toLowerCase();
        return new User(TestDataReader.getTestData(resolveTemplate(USER_LOGIN,orderNumber)), TestDataReader.getTestData(resolveTemplate(USER_PASSWORD,orderNumber)));
    }

}