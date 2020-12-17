import by.gsu.pms.Connector;
import by.gsu.pms.Logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        Connector.init("products", "root", "root"); //устанавливаем соединие с БД

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Connector.getConnection()) {

            final String GET_PARAMS_QUERY = "SELECT `params` FROM `params_groups` WHERE `name` = ?";
            final String GET_PRODUCTS_WITHOUT_GIVEN_PARAMETER_QUERY = "SELECT `name` FROM `product_groups` WHERE `params_group` != ?";
            final String GET_INFO_BY_GROUP_QUERY = "SELECT `description` FROM `list` WHERE `product_group` = ?";
            final String GET_FULL_PRODUCT_INFORMATION_QUERY = "SELECT `product_group`, `description`, `date`, `specific_values` FROM `list` WHERE `name` = ?";
            final String DELETE_PRODUCT_BY_VALUES_QUERY = "DELETE FROM `list` WHERE `specific_values` = ?";

            System.out.println("Введите группу продукции:");
            String group = sc.nextLine(); //читаем строку с консоли
            Logic.getInfoWithOneParameter(connection, GET_PARAMS_QUERY, group);

            System.out.println("Введите перечень продукции, не содержащий заданного параметра:");
            String missingProduct = sc.nextLine(); //читаем строку с консоли
            Logic.getInfoWithOneParameter(connection, GET_PRODUCTS_WITHOUT_GIVEN_PARAMETER_QUERY, missingProduct);

            System.out.println("Введите информацию о продукции для заданной группы:");
            String groupNameForDescription = sc.nextLine(); //читаем строку с консоли
            Logic.getInfoWithOneParameter(connection, GET_INFO_BY_GROUP_QUERY, groupNameForDescription);

            System.out.println("Введите название продукции:");
            String productName = sc.nextLine(); //читаем строку с консоли
            Logic.getFullInfo(connection, GET_FULL_PRODUCT_INFORMATION_QUERY, productName);

            System.out.println("Введите название продукции на удаление:");
            String paramsForDeletingProduct = sc.nextLine(); //читаем строку с консоли
            Logic.deleteProduct(connection, DELETE_PRODUCT_BY_VALUES_QUERY, paramsForDeletingProduct);

            System.out.println("Введите название группы, из которой нужно выполнить перемещение:");
            String fromGroup = sc.nextLine(); //читаем строку с консоли
            System.out.println("Введите название группы для перемещния:");
            String groupName = sc.nextLine(); //читаем строку с консоли
            System.out.println("Введите название группы, в которую нужно выполнить перемещение:");
            String toGroup = sc.nextLine(); //читаем строку с консоли
            Logic.moveParameterGroup(connection, fromGroup, groupName, toGroup);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
