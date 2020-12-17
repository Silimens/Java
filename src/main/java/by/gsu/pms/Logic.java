package by.gsu.pms;

import java.sql.*;

public class Logic {

    public static void createTables(Connection connection) throws SQLException {

        final String CREATE_LIST_TABLE = "CREATE TABLE `list` (\n" +
                " `name` varchar(30) NOT NULL,\n" +
                " `product_group` varchar(30) NOT NULL,\n" +
                " `description` varchar(255) NOT NULL,\n" +
                " `date` date NOT NULL,\n" +
                " `specific_values` varchar(255) NOT NULL,\n" +
                " KEY `product_group` (`product_group`),\n" +
                " CONSTRAINT `list_ibfk_1` FOREIGN KEY (`product_group`) REFERENCES `product_groups` (`name`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

        final String CREATE_PARAMS_GROUPS_TABLE = "CREATE TABLE `params_groups` (\n" +
                " `name` varchar(30) NOT NULL,\n" +
                " `params` varchar(30) NOT NULL,\n" +
                " KEY `name` (`name`),\n" +
                " KEY `params` (`params`),\n" +
                " CONSTRAINT `params_groups_ibfk_1` FOREIGN KEY (`params`) REFERENCES `params_units` (`name`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

        final String CREATE_PARAMS_UNITS_TABLE = "CREATE TABLE `params_units` (\n" +
                " `name` varchar(30) NOT NULL,\n" +
                " `unit` varchar(20) NOT NULL,\n" +
                " KEY `name` (`name`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

        final String CREATE_PRODUCT_GROUPS_TABLE = "CREATE TABLE `product_groups` (\n" +
                " `name` varchar(30) NOT NULL,\n" +
                " `params_group` varchar(30) NOT NULL,\n" +
                " KEY `name` (`name`),\n" +
                " KEY `params_group` (`params_group`),\n" +
                " CONSTRAINT `product_groups_ibfk_1` FOREIGN KEY (`params_group`) REFERENCES `params_groups` (`name`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8";

        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_LIST_TABLE);
            statement.execute(CREATE_PARAMS_GROUPS_TABLE);
            statement.execute(CREATE_PARAMS_UNITS_TABLE);
            statement.execute(CREATE_PRODUCT_GROUPS_TABLE);
        }
    }

    public static void getInfoWithOneParameter(Connection connection, String query, String param) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(query)) { //для запроса на получение данных по заданному параметру

            ps.setString(1, param); //подставляем названием группы в запрос

            try (ResultSet rs = ps.executeQuery()) { //результат запроса
                if (rs.isBeforeFirst()) {
                    while (rs.next()) { //пока есть что выводить
                        System.out.println(rs.getString(1)); //достаем из столбика
                    }
                } else {
                    System.out.println("Данные отсутсвуют");
                }
            }
        }
    }

    public static void getFullInfo(Connection connection, String query, String param) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, param);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.isBeforeFirst()) { //проверка на то, получили ли мы какие-либо данные
                    while (rs.next()) {
                        System.out.println("Product group: " + rs.getString(1));
                        System.out.println("Description: " + rs.getString(2));
                        System.out.println("Date: " + rs.getString(3));
                        System.out.println("Specific values: " + rs.getString(4));
                    }
                } else {
                    System.out.println("Данные отсутсвуют");
                }
            }
        }
    }

    public static void deleteProduct(Connection connection, String query, String param) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, param);
            int result = ps.executeUpdate(); //result будет равен 1, если запрос отработал успешно

            if (result > 0) { //а вот и проверочка, все ли прошло успешно
                System.out.println("Данные о продукте успешно удалены");
            } else {
                System.out.println("Заданная покупка не найдена");
            }
        }
    }

    public static void moveParameterGroup(Connection connection, String fromProduct, String group, String toProduct) throws SQLException {

        final String DELETE_PARAMS_GROUP_QUERY = "DELETE FROM product_groups WHERE `name` = ? AND `params_group` = ?";
        final String INSERT_PARAMS_GROUP_QUERY = "INSERT INTO `product_groups`(`name`, `params_group`) VALUES (?, ?)";

        PreparedStatement ps = null; //здесь я вынес ps перед try-catch для того, чтобы использовать его дважды

        try {
            ps = connection.prepareStatement(DELETE_PARAMS_GROUP_QUERY);

            ps.setString(1, fromProduct);
            ps.setString(2, group);
            int result = ps.executeUpdate();

            if (result > 0) {
                ps = connection.prepareStatement(INSERT_PARAMS_GROUP_QUERY);

                ps.setString(1, toProduct);
                ps.setString(2, group);
                result = ps.executeUpdate();

                if (result > 0) {
                    System.out.println("Перемещение выполнено успешно");
                }
            } else {
                System.out.println("Группа параметров не найдена в заданной групе товаров");
            }
        } finally {
            ps.close(); //обязательно закрытие ресурсов, т.к. мы использовали try без автоматического закрытия ресурсов
        }
    }
}
