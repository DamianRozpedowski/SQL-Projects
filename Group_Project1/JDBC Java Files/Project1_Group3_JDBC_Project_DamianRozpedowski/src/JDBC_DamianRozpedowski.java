import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class JDBC_DamianRozpedowski {
    private static final String serverName = "localhost";
    private static final String port = "13001";
    private static final String username = "sa";
    private static final String password = "PH@123456789";

    private static final String connectionUrl = String.format(
            "jdbc:sqlserver://%s:%s;user=%s;password=%s",
            serverName, port, username, password
    );

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JDBC_DamianRozpedowski::initializeGUI);
    }

        private static void initializeGUI() {
            JFrame frame = new JFrame("SQL Query Selector");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel mainPanel = new JPanel(new GridLayout(0, 1)); // Main layout for sections

            JPanel mediumPanel = new JPanel(new GridLayout(0, 1));
            mediumPanel.setBorder(BorderFactory.createTitledBorder("Project 1 Queries - Damian Rozpedowski"));

            // Add subsections for each database under Medium
            JPanel northwindsPanel = new JPanel();
            northwindsPanel.setBorder(BorderFactory.createTitledBorder("Northwinds2022TSQLV7"));

            // Adding query buttons for Northwinds2022TSQLV7
            for (int i = 0; i < 8; i++) {
                int queryIndex = i;
                JButton queryButton = new JButton("Medium Query " + (i + 1));
                queryButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
                queryButton.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
                if(i == 0 || i == 2 || i == 4) queryButton.setBackground(Color.RED);
                if (i == 6)queryButton.setBackground(Color.YELLOW);
                queryButton.addActionListener(e -> executeQueryAndDisplayGUI(queryIndex));
                northwindsPanel.add(queryButton);
            }

            // Adding buttons for Complex Query 1, 2, 3, and 4
            JButton complexQueryButton1 = new JButton("Complex Query 1");
            complexQueryButton1.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton1.setBackground(Color.LIGHT_GRAY); // Set background color for Complex Query buttons
            complexQueryButton1.addActionListener(e -> executeQueryAndDisplayGUI(13)); // Assuming index 13 for Complex Query 1
            northwindsPanel.add(complexQueryButton1);

            JButton complexQueryButton2 = new JButton("Complex Query 2");
            complexQueryButton2.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton2.setBackground(Color.YELLOW); // Set background color for Complex Query buttons
            complexQueryButton2.addActionListener(e -> executeQueryAndDisplayGUI(14)); // Assuming index 14 for Complex Query 2
            northwindsPanel.add(complexQueryButton2);

            JButton complexQueryButton3 = new JButton("Complex Query 3");
            complexQueryButton3.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton3.setBackground(Color.LIGHT_GRAY); // Set background color for Complex Query buttons
            complexQueryButton3.addActionListener(e -> executeQueryAndDisplayGUI(15)); // Assuming index 15 for Complex Query 3
            northwindsPanel.add(complexQueryButton3);

            JButton complexQueryButton4 = new JButton("Complex Query 4");
            complexQueryButton4.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton4.setBackground(Color.GREEN); // Set background color for Complex Query buttons
            complexQueryButton4.addActionListener(e -> executeQueryAndDisplayGUI(16)); // Assuming index 16 for Complex Query 4
            northwindsPanel.add(complexQueryButton4);

            mediumPanel.add(northwindsPanel);

            JPanel adventureWorksPanel = new JPanel();
            adventureWorksPanel.setBorder(BorderFactory.createTitledBorder("AdventureWorks2017"));

            // Adding query buttons for AdventureWorks2017
            JButton queryButton1 = new JButton("Medium Query 9");
            queryButton1.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
            queryButton1.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
            queryButton1.addActionListener(e -> executeQueryAndDisplayGUI(8)); // Assuming index 8 for the first new query
            adventureWorksPanel.add(queryButton1);

            JButton queryButton2 = new JButton("Medium Query 10");
            queryButton2.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
            queryButton2.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
            queryButton2.addActionListener(e -> executeQueryAndDisplayGUI(9)); // Assuming index 9 for the second new query
            adventureWorksPanel.add(queryButton2);

            // Adding button for AdventureWorks2017 Complex Query 5
            JButton complexQueryButton5 = new JButton("Complex Query 5");
            complexQueryButton5.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton5.setBackground(Color.GREEN); // Set background color for Complex Query buttons
            complexQueryButton5.addActionListener(e -> executeQueryAndDisplayGUI(17)); // Assuming index 17 for AdventureWorks2017ComplexQuery1
            adventureWorksPanel.add(complexQueryButton5);

            mediumPanel.add(adventureWorksPanel);

            // Adding AdventureWorksDW2017 section
            JPanel adventureWorksDWPanel = new JPanel();
            adventureWorksDWPanel.setBorder(BorderFactory.createTitledBorder("AdventureWorksDW2017"));

            // Adding query buttons for AdventureWorksDW2017
            JButton queryButtonDW = new JButton("Medium Query 11"); // Only one query for AdventureWorksDW2017
            queryButtonDW.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
            queryButtonDW.setBackground(Color.GREEN); // Set background color for Medium Query buttons
            queryButtonDW.addActionListener(e -> executeQueryAndDisplayGUI(10)); // Assuming index 10 for the new AdventureWorksDW2017 query
            adventureWorksDWPanel.add(queryButtonDW);

            // Adding button for AdventureWorksDW2017 Complex Query 6
            JButton complexQueryButton6 = new JButton("Complex Query 6");
            complexQueryButton6.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton6.setBackground(Color.LIGHT_GRAY); // Set background color for Complex Query buttons
            complexQueryButton6.addActionListener(e -> executeQueryAndDisplayGUI(18)); // Assuming index 18 for AdventureWorksDW2017ComplexQuery1
            adventureWorksDWPanel.add(complexQueryButton6);

            mediumPanel.add(adventureWorksDWPanel);

            // Adding WideWorldImporters section
            JPanel wideWorldImportersPanel = new JPanel();
            wideWorldImportersPanel.setBorder(BorderFactory.createTitledBorder("WideWorldImporters"));

            JButton queryButtonWWI = new JButton("Medium Query 12"); // Button for WideWorldImporters Query 1
            queryButtonWWI.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
            queryButtonWWI.setBackground(Color.YELLOW); // Set background color for Medium Query buttons
            queryButtonWWI.addActionListener(e -> executeQueryAndDisplayGUI(11)); // Assuming index 11 for WideWorldImporters Query 1
            wideWorldImportersPanel.add(queryButtonWWI);

            // Adding button for WideWorldImporters Complex Query 7
            JButton complexQueryButton7 = new JButton("Complex Query 7");
            complexQueryButton7.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton7.setBackground(Color.LIGHT_GRAY); // Set background color for Complex Query buttons
            complexQueryButton7.addActionListener(e -> executeQueryAndDisplayGUI(19)); // Assuming index 19 for WideWorldImportersComplexQuery1
            wideWorldImportersPanel.add(complexQueryButton7);

            mediumPanel.add(wideWorldImportersPanel);

            JPanel wideWorldImportersDWPanel = new JPanel();
            wideWorldImportersDWPanel.setBorder(BorderFactory.createTitledBorder("WideWorldImportersDW"));

            JButton queryButtonWWIDW = new JButton("Medium Query 13"); // Button for WideWorldImportersDW Query 1
            queryButtonWWIDW.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
            queryButtonWWIDW.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
            queryButtonWWIDW.addActionListener(e -> executeQueryAndDisplayGUI(12)); // Assuming index 12 for WideWorldImportersDW Query 1
            wideWorldImportersDWPanel.add(queryButtonWWIDW);

            mediumPanel.add(wideWorldImportersDWPanel);

            mainPanel.add(mediumPanel);

            frame.add(mainPanel, BorderLayout.CENTER);

            // Set the window size
            frame.setSize(800, 800);  // You can adjust these values to fit your needs

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }



    private static void executeQueryAndDisplayGUI(int queryIndex) {
        JFrame frame = new JFrame("SQL Query Results");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JLabel titleLabel;
        switch (queryIndex) {
            case 0:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Generate a detailed order summary report in the Northwinds2022TSQLV7 database, showcasing total sales amount, average quantity per order, and the number of orders per customer.</div></html>");
                break;
            case 1:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Perform a comprehensive analysis of product categories in Northwinds2022TSQLV7, focusing on the number of products and suppliers, and total sales per category, to evaluate market performance and category health.</div></html>");
                break;
            case 2:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Create a comprehensive report in the Northwinds2022TSQLV7 database that details the total number of orders and total revenue generated by each shipper.</div></html>");
                break;
            case 3:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Conduct an in-depth analysis of customer purchasing behavior in Northwinds2022TSQLV7 by calculating the total number of orders and average order size per customer, to discern spending patterns and assess customer value.</div></html>");
                break;
            case 4:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Develop a report in the Northwinds2022TSQLV7 database that identifies the last order date for each customer along with their total number of orders, organized by the most frequent customers and recent orders.</div></html>");
                break;
            case 5:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Construct a detailed purchase analysis report in the Northwinds2022TSQLV7 database that calculates the total sales amount per customer, utilizing a custom function to compute individual purchase amounts.</div></html>");
                break;
            case 6:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Perform a quarterly sales analysis in the Northwinds2022TSQLV7 database to track sales trends over time, providing a structured view of revenue changes across different quarters of each year.</div></html>");
                break;
            case 7:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Execute a comprehensive sales performance review for employees in Northwinds2022TSQLV7, determining total sales generated by each employee to highlight top performers and assess overall sales productivity.</div></html>");
                break;
            case 8:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Analyze manufacturing costs and pricing in AdventureWorks2017 by category to inform pricing strategies and manufacturing efficiency.</div></html>");
                break;
            case 9:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Analyze 2011 product sales in AdventureWorks2017, focusing on quantity and sales amounts to pinpoint top products and trends.</div></html>");
                break;
            case 10:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Determine the annual sales performance by region and country in the AdventureWorksDW2017 database, categorizing each territory based on total sales thresholds.</div></html>");
                break;
            case 11:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Conduct a detailed analysis of product sales performance and order metrics in the WideWorldImporters database to evaluate product popularity and customer purchasing patterns.</div></html>");
                break;
            case 12:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Evaluate customer performance based on total orders, total order amount, and average order amount in the WideWorldImportersDW database to identify customer behavior trends.</div></html>");
                break;
            // Complex
            case 13:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Construct a detailed purchase analysis report in the Northwinds2022TSQLV7 database that calculates the total sales amount per customer, utilizing a custom function to compute individual purchase amounts.</div></html>");
                break;
            case 14:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Implement a detailed monthly sales growth analysis for each product category in Northwinds2022TSQLV7, utilizing a custom function to calculate growth rates and identify sales trends over time.</div></html>");
                break;
            case 15:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Implement a comprehensive sales and discount analysis in the Northwinds2022TSQLV7 database by calculating the total price after discount for each order and summarizing sales and discounts per customer and order.</div></html>");
                break;
            case 16:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Analyze sales commissions in the Northwinds2022TSQLV7 database by calculating total sales and commissions for each employee.</div></html>");
                break;
            case 17:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Perform a comprehensive annual sales analysis for each product in the AdventureWorks2017 database, accounting for variable discounts based on product ID and order date. The analysis will produce a detailed report showing sales volume, revenue, and the financial impact of discounts on each product over time.</div></html>");
                break;
            case 18:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Conduct a detailed analysis of product category sales performance in the AdventureWorksDW2017 database, specifically focusing on calculating total sales for each product category using a custom scalar function named dbo.fn_GetTotalSales.</div></html>");
                break;
            case 19:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Evaluate top customers based on their total revenue in the WideWorldImporters database to identify key revenue contributors.</div></html>");
                break;

                
            // Add cases for other queries as needed
            default:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Lorem Ipsum for Generic Query</div></html>");
                break;
        }
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Adjust font as needed
        titleLabel.setPreferredSize(new Dimension(800, 80)); // Set preferred size for the label with increased height
        panel.add(titleLabel, BorderLayout.NORTH);


        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.setSize(1000, 400);
        frame.setLocationRelativeTo(null);

        // Set column names based on the selected query
        String[] columnNames;
        switch (queryIndex) {
            case 0:
                columnNames = new String[]{"Order ID", "Customer ID", "Total Amount", "Average Quantity", "Number of Orders"};
                break;
            case 1:
                columnNames = new String[]{"Category ID", "Category Name", "Number of Products", "Number of Suppliers", "Total Sales"};
                break;
            case 2:
                columnNames = new String[]{"Shipper ID", "Shipper Name", "Total Orders", "Total Revenue"};
                break;
            case 3:
                columnNames = new String[]{"Customer ID", "Total Orders", "Average Order Size"};
                break;
            case 4:
                columnNames = new String[]{"Customer ID", "Customer Company Name", "Number of Orders", "Last Order Date"};
                break;
            case 5:
                columnNames = new String[]{"Customer ID", "Customer Company Name", "Average Purchase Amount"};
                break;
            case 6:
                columnNames = new String[]{"Sale Year", "Sale Quarter", "Total Sales"};
                break;
            case 7:
                columnNames = new String[]{"Employee ID", "Employee Name", "Total Sales"};
                break;
            case 8:
                columnNames = new String[]{"CategoryName", "AvgManufacturingCostFormatted", "AvgListPriceFormatted", "ProductCount"};
                break;
            case 9:
                columnNames = new String[]{"ProductID", "ProductName", "OrderYear", "TotalQuantity", "TotalSalesAmount"};
                break;
            case 10: // New case for AdventureWorksDW2017 query
                columnNames = new String[]{"SaleYear", "TerritoryRegion", "TerritoryCountry", "TotalSales", "AvgSalesPerOrder", "NumberOfOrders", "PerformanceLevel"};
                break;
            case 11: // New case for WideWorldImporters Query 1
                columnNames = new String[]{"Stock Item Name", "Total Sales", "Number of Orders", "Average Quantity Per Order"};
                break;
            case 12: // New query for WideWorldImportersDW
                columnNames = new String[]{"Customer", "TotalOrders", "TotalOrderAmount", "AvgOrderAmount"}; // Actual column names
                break;
                case 13: // New case for Northwinds2022TSQLV7 Complex Query 1
                    columnNames = new String[]{"CustomerID", "CustomerCompanyName", "TotalSalesAmount"};
                    break;
                case 14: // New case for Northwinds2022TSQLV7 Complex Query 2
                    columnNames = new String[]{"CategoryID", "CategoryName", "OrderMonth", "OrderYear", "TotalSales", "MonthlySalesGrowth"};
                    break;
                case 15: // New case for Northwinds2022TSQLV7 Complex Query 3
                    columnNames = new String[]{"CustomerID", "CustomerCompanyName", "OrderID", "TotalSales", "TotalDiscount"};
                    break;
                case 16: // New case for Northwinds2022TSQLV7 Complex Query 4
                    columnNames = new String[]{"EmployeeID", "EmployeeName", "NumberOfOrders", "TotalSales", "AverageOrderValue", "TotalCommission"};
                    break;
                case 17: // New case for AdventureWorks2017ComplexQuery1
                    columnNames = new String[]{"SaleYear", "ProductName", "TotalOrders", "TotalQuantity", "AvgUnitPrice", "TotalSales", "TotalSalesWithDiscount"};
                    break;
                case 18: // New case for AdventureWorksDW2017ComplexQuery1
                    columnNames = new String[]{"ProductCategoryKey", "ProductCategory", "TotalSales"};
                    break;
                case 19: // New case for WideWorldImportersComplexQuery1
                    columnNames = new String[]{"CustomerID", "CustomerName", "TotalRevenue"};
                    break;
            // Add cases for other queries as needed
            default:
                columnNames = new String[]{"Column 1", "Column 2"}; // Generic columns, adjust as necessary
                break;
        }

        model.setColumnIdentifiers(columnNames);

        String sqlQuery = getSqlQuery(queryIndex);
        if (sqlQuery.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No SQL query is defined for the selected option.",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            // Populate the table model with data from the result set
            while (resultSet.next()) {
                Object[] row = new Object[columnNames.length];
                for (int i = 0; i < columnNames.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                model.addRow(row);
            }

            frame.setVisible(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    private static String getSqlQuery(int queryIndex) {
        switch (queryIndex) {
            case 0:
                return getOrderSummaryQuery();
            case 1:
                return getCategorySalesQuery();
            case 2:
                return getRevenueByShipperQuery();
            case 3:
                return getAverageOrderSizeQuery();
            case 4:
                return getLastOrderDetailsQuery();
            case 5:
                return getCustomerOrderTotalQuery();
            case 6:
                return getQuarterlySalesQuery();
            case 7:
                return getEmployeeSalesQuery();
            case 8:
                return getAdventureWorksCategoryCostsQuery();
            case 9:
                return getAdventureWorksSalesDataQuery();
            case 10: // New case for AdventureWorksDW2017 query
                return getAdventureWorksDWQuery();
            case 11:
                return getWideWorldImportersQuery1();
            case 12:
                return getWideWorldImportersDWQuery1();
            case 13:
                return getNorthwinds2022TSQLV7ComplexQuery1();
            case 14:
                return getNorthwinds2022TSQLV7ComplexQuery2();
            case 15:
                return getNorthwinds2022TSQLV7ComplexQuery3();
            case 16:
                return getNorthwinds2022TSQLV7ComplexQuery4();
            case 17: // New case for AdventureWorks2017ComplexQuery1
                return getAdventureWorks2017ComplexQuery1();
            case 18: // New case for AdventureWorksDW2017ComplexQuery1
                return getAdventureWorksDW2017ComplexQuery1();
            case 19: // New case for WideWorldImportersComplexQuery1
                return getWideWorldImportersComplexQuery1();
            default:
                return "";
        }
    }

    private static String getOrderSummaryQuery() {
        return "USE Northwinds2022TSQLV7; " +"WITH OrderSummary AS (" +
               "    SELECT " +
               "        OrderID, " +
               "        SUM(UnitPrice * Quantity) AS TotalAmount, " +
               "        AVG(Quantity) AS AverageQuantity " +
               "    FROM Sales.OrderDetail " +
               "    GROUP BY OrderID" +
               ") " +
               "SELECT " +
               "    o.OrderID, " +
               "    o.CustomerID, " +
               "    os.TotalAmount, " +
               "    os.AverageQuantity, " +
               "    COUNT(o.OrderID) AS NumberOfOrders " +
               "FROM Sales.[Order] o " +
               "JOIN OrderSummary os ON o.OrderID = os.OrderID " +
               "GROUP BY o.OrderID, o.CustomerID, os.TotalAmount, os.AverageQuantity " +
               "ORDER BY o.OrderID;";
    }
    private static String getCategorySalesQuery() {
        return "USE Northwinds2022TSQLV7; " +"WITH CategoryProductCounts AS (" +
               "    SELECT CategoryID, " +
               "           COUNT(DISTINCT ProductID) AS NumberOfProducts, " +
               "           COUNT(DISTINCT SupplierID) AS NumberOfSuppliers " +
               "    FROM Production.Product " +
               "    GROUP BY CategoryID" +
               "), " +
               "CategorySales AS (" +
               "    SELECT prod.CategoryID, " +
               "           SUM(od.Quantity * od.UnitPrice) AS TotalSales " +
               "    FROM Production.Product prod " +
               "    JOIN Sales.OrderDetail od ON prod.ProductID = od.ProductID " +
               "    GROUP BY prod.CategoryID" +
               ") " +
               "SELECT cat.CategoryID, " +
               "       cat.CategoryName, " +
               "       pc.NumberOfProducts, " +
               "       pc.NumberOfSuppliers, " +
               "       cs.TotalSales " +
               "FROM Production.Category cat " +
               "JOIN CategoryProductCounts pc ON cat.CategoryID = pc.CategoryID " +
               "JOIN CategorySales cs ON cat.CategoryID = cs.CategoryID " +
               "ORDER BY cs.TotalSales DESC;";
    }
    private static String getRevenueByShipperQuery() {
        return "USE Northwinds2022TSQLV7; " +"WITH OrderCounts AS (" +
               "    SELECT ShipperID, COUNT(OrderID) AS TotalOrders " +
               "    FROM Sales.[Order] " +
               "    GROUP BY ShipperID" +
               "), " +
               "RevenueByShipper AS (" +
               "    SELECT o.ShipperId, SUM(od.UnitPrice * od.Quantity) AS TotalRevenue " +
               "    FROM Sales.[Order] o " +
               "    JOIN Sales.OrderDetail od ON o.OrderID = od.OrderID " +
               "    GROUP BY o.ShipperId" +
               ") " +
               "SELECT s.ShipperID, s.ShipperCompanyName AS ShipperName, oc.TotalOrders, rs.TotalRevenue " +
               "FROM Sales.Shipper s " +
               "LEFT JOIN OrderCounts oc ON s.ShipperID = oc.ShipperID " +
               "LEFT JOIN RevenueByShipper rs ON s.ShipperID = rs.ShipperID " +
               "ORDER BY rs.TotalRevenue DESC;";
    }
    private static String getAverageOrderSizeQuery() {
        return "USE Northwinds2022TSQLV7; " +"WITH OrderSize AS (" +
               "    SELECT o.CustomerID, o.OrderID, SUM(od.Quantity * od.UnitPrice) AS TotalPrice " +
               "    FROM Sales.[Order] o " +
               "    JOIN Sales.OrderDetail od ON o.OrderID = od.OrderID " +
               "    GROUP BY o.CustomerID, o.OrderID" +
               ") " +
               "SELECT c.CustomerID, COUNT(o.OrderID) AS TotalOrders, AVG(os.TotalPrice) AS AverageOrderSize " +
               "FROM Sales.Customer c " +
               "JOIN Sales.[Order] o ON c.CustomerID = o.CustomerID " +
               "JOIN OrderSize os ON o.OrderID = os.OrderID " +
               "GROUP BY c.CustomerID;";
    }
    private static String getLastOrderDetailsQuery() {
        return "USE Northwinds2022TSQLV7; " +"WITH LastOrderDetails AS (" +
               "    SELECT " +
               "        o.CustomerID, " +
               "        o.OrderID, " +
               "        o.OrderDate, " +
               "        ROW_NUMBER() OVER (PARTITION BY o.CustomerID ORDER BY o.OrderDate DESC) AS RowNum " +
               "    FROM Sales.[Order] o " +
               "    JOIN Sales.OrderDetail od ON o.OrderID = od.OrderID" +
               ") " +
               "SELECT " +
               "    c.CustomerID, " +
               "    c.CustomerCompanyName, " +
               "    COUNT(o.OrderID) AS NumberOfOrders, " +
               "    lod.OrderDate AS LastOrderDate " +
               "FROM Sales.Customer c " +
               "JOIN Sales.[Order] o ON c.CustomerID = o.CustomerID " +
               "JOIN LastOrderDetails lod ON c.CustomerID = lod.CustomerID AND lod.RowNum = 1 " +
               "GROUP BY c.CustomerID, c.CustomerCompanyName, lod.OrderDate " +
               "ORDER BY NumberOfOrders DESC, LastOrderDate DESC;";
    }
    private static String getCustomerOrderTotalQuery() {
        return "USE Northwinds2022TSQLV7; " +"WITH CustomerOrderTotal AS (" +
               "    SELECT " +
               "        o.CustomerID, " +
               "        SUM(od.UnitPrice * od.Quantity) AS TotalAmount " +
               "    FROM Sales.[Order] o " +
               "    JOIN Sales.OrderDetail od ON o.OrderID = od.OrderID " +
               "    GROUP BY o.CustomerID" +
               ") " +
               "SELECT " +
               "    c.CustomerID, " +
               "    c.CustomerCompanyName, " +
               "    AVG(cot.TotalAmount) AS AveragePurchaseAmount " +
               "FROM Sales.Customer c " +
               "JOIN CustomerOrderTotal cot ON c.CustomerID = cot.CustomerID " +
               "GROUP BY c.CustomerID, c.CustomerCompanyName;";
    }
    private static String getQuarterlySalesQuery() {
        return "USE Northwinds2022TSQLV7; " + "WITH QuarterlySales AS (" +
               "    SELECT " +
               "        YEAR(o.OrderDate) AS SaleYear, " +
               "        DATEPART(QUARTER, o.OrderDate) AS SaleQuarter, " +
               "        SUM(od.UnitPrice * od.Quantity) AS TotalSales " +
               "    FROM Sales.[Order] o " +
               "    JOIN Sales.OrderDetail od ON o.OrderID = od.OrderID " +
               "    GROUP BY YEAR(o.OrderDate), DATEPART(QUARTER, o.OrderDate)" +
               ") " +
               "SELECT " +
               "    SaleYear, " +
               "    SaleQuarter, " +
               "    TotalSales " +
               "FROM QuarterlySales " +
               "ORDER BY SaleYear, SaleQuarter;";
    }

    private static String getEmployeeSalesQuery() {
        return "USE Northwinds2022TSQLV7; " + "WITH EmployeeSales AS (" +
               "    SELECT " +
               "        e.EmployeeID, " +
               "        e.EmployeeFirstName + ' ' + e.EmployeeLastName AS EmployeeName, " +
               "        SUM(od.Quantity * od.UnitPrice) AS TotalSales " +
               "    FROM HumanResources.Employee e " +
               "    JOIN Sales.[Order] o ON e.EmployeeID = o.EmployeeID " +
               "    JOIN Sales.OrderDetail od ON o.OrderID = od.OrderID " +
               "    GROUP BY e.EmployeeID, e.EmployeeFirstName, e.EmployeeLastName" +
               ") " +
               "SELECT " +
               "    EmployeeID, " +
               "    EmployeeName, " +
               "    TotalSales " +
               "FROM EmployeeSales " +
               "ORDER BY TotalSales DESC;";
    }
    
    private static String getAdventureWorksCategoryCostsQuery() {
        return "USE AdventureWorks2017; " +
               "WITH CategoryCosts AS (" +
               "    SELECT " +
               "        pc.Name AS CategoryName, " +
               "        AVG(p.StandardCost) AS AvgManufacturingCost, " +
               "        AVG(p.ListPrice) AS AvgListPrice, " +
               "        COUNT(p.ProductID) AS ProductCount " +
               "    FROM Production.Product p " +
               "    JOIN Production.ProductSubcategory psc ON p.ProductSubcategoryID = psc.ProductSubcategoryID " +
               "    JOIN Production.ProductCategory pc ON psc.ProductCategoryID = pc.ProductCategoryID " +
               "    GROUP BY pc.Name" +
               ") " +
               "SELECT " +
               "    CategoryName, " +
               "    FORMAT(AvgManufacturingCost, 'C', 'en-us') AS AvgManufacturingCostFormatted, " +
               "    FORMAT(AvgListPrice, 'C', 'en-us') AS AvgListPriceFormatted, " +
               "    ProductCount " +
               "FROM CategoryCosts " +
               "ORDER BY CategoryName;";
    }

    private static String getAdventureWorksSalesDataQuery() {
        return "USE AdventureWorks2017; " +
               "WITH SalesData AS (" +
               "    SELECT " +
               "        p.ProductID, " +
               "        p.Name AS ProductName, " +
               "        YEAR(soh.OrderDate) AS OrderYear, " +
               "        SUM(sod.OrderQty) AS TotalQuantity, " +
               "        SUM(sod.LineTotal) AS TotalSalesAmount " +
               "    FROM " +
               "        Production.Product p " +
               "    JOIN " +
               "        Sales.SalesOrderDetail sod ON p.ProductID = sod.ProductID " +
               "    JOIN " +
               "        Sales.SalesOrderHeader soh ON sod.SalesOrderID = soh.SalesOrderID " +
               "    WHERE " +
               "        soh.OrderDate >= '2011-01-01' AND soh.OrderDate < '2012-01-01' " +
               "    GROUP BY " +
               "        p.ProductID, " +
               "        p.Name, " +
               "        YEAR(soh.OrderDate) " +
               ") " +
               "SELECT " +
               "    ProductID, " +
               "    ProductName, " +
               "    OrderYear, " +
               "    SUM(TotalQuantity) AS TotalQuantity, " +
               "    SUM(TotalSalesAmount) AS TotalSalesAmount " +
               "FROM " +
               "    SalesData " +
               "GROUP BY " +
               "    ProductID, " +
               "    ProductName, " +
               "    OrderYear " +
               "ORDER BY " +
               "    OrderYear DESC, " +
               "    TotalSalesAmount DESC;";
    }
    private static String getAdventureWorksDWQuery() {
        return "USE AdventureWorksDW2017; " +
               "WITH TerritorySales AS (" +
               "    SELECT " +
               "        dst.SalesTerritoryRegion AS TerritoryRegion, " +
               "        dst.SalesTerritoryCountry AS TerritoryCountry, " +
               "        dd.CalendarYear AS SaleYear, " +
               "        SUM(fis.SalesAmount) AS TotalSales, " +
               "        AVG(fis.SalesAmount) AS AvgSalesPerOrder, " +
               "        COUNT(DISTINCT fis.SalesOrderNumber) AS NumberOfOrders " +
               "    FROM " +
               "        FactInternetSales fis " +
               "    JOIN DimSalesTerritory dst ON fis.SalesTerritoryKey = dst.SalesTerritoryKey " +
               "    JOIN DimDate dd ON fis.OrderDateKey = dd.DateKey " +
               "    GROUP BY " +
               "        dst.SalesTerritoryRegion, " +
               "        dst.SalesTerritoryCountry, " +
               "        dd.CalendarYear " +
               ") " +
               "SELECT " +
               "    SaleYear, " +
               "    TerritoryRegion, " +
               "    TerritoryCountry, " +
               "    TotalSales, " +
               "    AvgSalesPerOrder, " +
               "    NumberOfOrders, " +
               "    CASE " +
               "        WHEN TotalSales > 1000000 THEN 'High Performance' " +
               "        WHEN TotalSales BETWEEN 500000 AND 1000000 THEN 'Medium Performance' " +
               "        ELSE 'Low Performance' " +
               "    END AS PerformanceLevel " +
               "FROM " +
               "    TerritorySales " +
               "ORDER BY " +
               "    SaleYear, " +
               "    TotalSales DESC;";
    }
    private static String getWideWorldImportersQuery1() {
        return "USE WideWorldImporters; " +
               "SELECT " +
               "    si.StockItemName, " +
               "    SUM(il.LineProfit) AS TotalSales, " +
               "    COUNT(DISTINCT i.InvoiceID) AS NumberOfOrders, " +
               "    (SELECT AVG(Quantity) " +
               "     FROM Sales.InvoiceLines subIL " +
               "     JOIN Sales.Invoices subI ON subIL.InvoiceID = subI.InvoiceID " +
               "     WHERE subIL.StockItemID = il.StockItemID) AS AvgQuantityPerOrder " +
               "FROM " +
               "    Sales.InvoiceLines il " +
               "JOIN Sales.Invoices i ON il.InvoiceID = i.InvoiceID " +
               "JOIN Warehouse.StockItems si ON il.StockItemID = si.StockItemID " +
               "GROUP BY " +
               "    si.StockItemName, il.StockItemID " +
               "ORDER BY " +
               "    TotalSales DESC;";
    }
    private static String getWideWorldImportersDWQuery1() {
    	  return "USE WideWorldImportersDW; " +"WITH CustomerPerformance AS (" +
    	           "    SELECT " +
    	           "        c.Customer, " +
    	           "        COUNT(o.[Order Key]) AS TotalOrders, " +
    	           "        SUM(o.[Total Including Tax]) AS TotalOrderAmount, " +
    	           "        SUM(o.[Total Including Tax]) / COUNT(o.[Order Key]) AS AvgOrderAmount " +
    	           "    FROM " +
    	           "        Dimension.Customer c " +
    	           "    LEFT JOIN " +
    	           "        Fact.[Order] o ON o.[Customer Key] = c.[Customer Key] " +
    	           "    GROUP BY " +
    	           "        c.Customer" +
    	           ") " +
    	           "SELECT TOP 15 " +
    	           "    Customer, " +
    	           "    TotalOrders, " +
    	           "    TotalOrderAmount, " +
    	           "    AvgOrderAmount " +
    	           "FROM " +
    	           "    CustomerPerformance " +
    	           "ORDER BY " +
    	           "    AvgOrderAmount ASC;";
    }

    private static String getNorthwinds2022TSQLV7ComplexQuery1() {
        return "USE Northwinds2022TSQLV7; " +
               "DECLARE @sql NVARCHAR(MAX); " +
               "SET @sql = 'CREATE OR ALTER FUNCTION dbo.CalculateTotalPurchaseAmount (@unitPrice MONEY, @quantity INT) ' + " +
               "           'RETURNS MONEY ' + " +
               "           'AS ' + " +
               "           'BEGIN ' + " +
               "           '    RETURN @unitPrice * @quantity; ' + " +
               "           'END;'; " +
               "EXEC sp_executesql @sql; " +
               "WITH CustomerPurchaseSummary AS ( " +
               "    SELECT " +
               "        c.CustomerID, " +
               "        c.CustomerCompanyName, " +
               "        SUM(dbo.CalculateTotalPurchaseAmount(od.UnitPrice, od.Quantity)) AS TotalPurchaseAmount " +
               "    FROM Sales.Customer c " +
               "    JOIN Sales.[Order] o ON c.CustomerID = o.CustomerID " +
               "    JOIN Sales.OrderDetail od ON o.OrderID = od.OrderID " +
               "    GROUP BY c.CustomerID, c.CustomerCompanyName " +
               ") " +
               "SELECT " +
               "    CustomerID, " +
               "    CustomerCompanyName, " +
               "    SUM(TotalPurchaseAmount) AS TotalSalesAmount " +
               "FROM CustomerPurchaseSummary " +
               "GROUP BY CustomerID, CustomerCompanyName;";
    }


    private static String getNorthwinds2022TSQLV7ComplexQuery2() {
        return "USE Northwinds2022TSQLV7; " +
               "DECLARE @sql NVARCHAR(MAX); " +
               "SET @sql = 'CREATE OR ALTER FUNCTION dbo.CalculateMonthlySalesGrowth (@currentSales MONEY, @previousSales MONEY) ' + " +
               "           'RETURNS FLOAT ' + " +
               "           'AS ' + " +
               "           'BEGIN ' + " +
               "           '    DECLARE @growth FLOAT; ' + " +
               "           '    IF @previousSales = 0 ' + " +
               "           '        SET @growth = NULL; ' + " +
               "           '    ELSE ' + " +
               "           '        SET @growth = (@currentSales - @previousSales) / @previousSales; ' + " +
               "           '    RETURN @growth; ' + " +
               "           'END;'; " +
               "EXEC sp_executesql @sql; " +
               "WITH CategoryMonthlySales AS ( " +
               "    SELECT " +
               "        c.CategoryID, " +
               "        c.CategoryName, " +
               "        MONTH(o.OrderDate) AS OrderMonth, " +
               "        YEAR(o.OrderDate) AS OrderYear, " +
               "        SUM(od.UnitPrice * od.Quantity) AS TotalSales " +
               "    FROM Production.Category c " +
               "    JOIN Production.Product p ON c.CategoryID = p.CategoryID " +
               "    JOIN Sales.OrderDetail od ON p.ProductID = od.ProductID " +
               "    JOIN Sales.[Order] o ON od.OrderID = o.OrderID " +
               "    GROUP BY c.CategoryID, c.CategoryName, YEAR(o.OrderDate), MONTH(o.OrderDate) " +
               ") " +
               "SELECT " +
               "    c.CategoryID, " +
               "    c.CategoryName, " +
               "    MONTH(o1.OrderDate) AS OrderMonth, " +
               "    YEAR(o1.OrderDate) AS OrderYear, " +
               "    SUM(od1.UnitPrice * od1.Quantity) AS TotalSales, " +
               "    dbo.CalculateMonthlySalesGrowth(SUM(od1.UnitPrice * od1.Quantity), SUM(od2.UnitPrice * od2.Quantity)) AS MonthlySalesGrowth " +
               "FROM Production.Category c " +
               "JOIN Production.Product p ON c.CategoryID = p.CategoryID " +
               "JOIN Sales.OrderDetail od1 ON p.ProductID = od1.ProductID " +
               "JOIN Sales.[Order] o1 ON od1.OrderID = o1.OrderID " +
               "JOIN Sales.OrderDetail od2 ON p.ProductID = od2.ProductID " +
               "JOIN Sales.[Order] o2 ON od2.OrderID = o2.OrderID AND MONTH(o2.OrderDate) = MONTH(o1.OrderDate) - 1 AND YEAR(o2.OrderDate) = YEAR(o1.OrderDate) " +
               "GROUP BY c.CategoryID, c.CategoryName, YEAR(o1.OrderDate), MONTH(o1.OrderDate) " +
               "ORDER BY c.CategoryID, YEAR(o1.OrderDate), MONTH(o1.OrderDate);";
    }

    private static String getNorthwinds2022TSQLV7ComplexQuery3() {
        return "USE Northwinds2022TSQLV7; " +
               "DECLARE @sql NVARCHAR(MAX); " +
               "SET @sql = 'CREATE OR ALTER FUNCTION dbo.CalculateTotalPriceAfterDiscount (@unitPrice MONEY, @quantity INT, @discount FLOAT) ' + " +
               "           'RETURNS MONEY ' + " +
               "           'AS ' + " +
               "           'BEGIN ' + " +
               "           '    RETURN @unitPrice * @quantity * (1 - @discount); ' + " +
               "           'END;'; " +
               "EXEC sp_executesql @sql; " +
               "WITH OrderDetailsWithTotal AS ( " +
               "    SELECT " +
               "        od.OrderID, " +
               "        od.UnitPrice, " +
               "        od.Quantity, " +
               "        od.DiscountPercentage, " +
               "        dbo.CalculateTotalPriceAfterDiscount(od.UnitPrice, od.Quantity, od.DiscountPercentage) AS TotalPriceAfterDiscount " +
               "    FROM Sales.OrderDetail od " +
               ") " +
               "SELECT " +
               "    c.CustomerID, " +
               "    c.CustomerCompanyName, " +
               "    o.OrderID, " +
               "    SUM(odt.TotalPriceAfterDiscount) AS TotalSales, " +
               "    SUM(odt.UnitPrice * odt.Quantity - odt.TotalPriceAfterDiscount) AS TotalDiscount " +
               "FROM Sales.Customer c " +
               "JOIN Sales.[Order] o ON c.CustomerID = o.CustomerID " +
               "JOIN OrderDetailsWithTotal odt ON o.OrderID = odt.OrderID " +
               "GROUP BY c.CustomerID, c.CustomerCompanyName, o.OrderID " +
               "ORDER BY TotalSales DESC;";
    }



    private static String getNorthwinds2022TSQLV7ComplexQuery4() {
    	return "USE Northwinds2022TSQLV7; " +
    	           "DECLARE @sql NVARCHAR(MAX); " +
    	           "SET @sql = 'CREATE OR ALTER FUNCTION dbo.CalculateCommission (@totalSaleAmount MONEY) ' + " +
    	           "           'RETURNS MONEY ' + " +
    	           "           'AS ' + " +
    	           "           'BEGIN ' + " +
    	           "           '    DECLARE @commissionRate FLOAT = 0.05; ' + " +
    	           "           '    RETURN @totalSaleAmount * @commissionRate; ' + " +
    	           "           'END;'; " +
    	           "EXEC sp_executesql @sql; " +
    	           "WITH TotalSalesPerOrder AS ( " +
    	           "    SELECT " +
    	           "        od.OrderID, " +
    	           "        SUM(od.UnitPrice * od.Quantity) AS TotalSaleAmount " +
    	           "    FROM Sales.OrderDetail od " +
    	           "    GROUP BY od.OrderID " +
    	           "), " +
    	           "TotalCommissionPerEmployee AS ( " +
    	           "    SELECT " +
    	           "        e.EmployeeID, " +
    	           "        SUM(dbo.CalculateCommission(tspo.TotalSaleAmount)) AS TotalCommission " +
    	           "    FROM HumanResources.Employee e " +
    	           "    JOIN Sales.[Order] o ON e.EmployeeID = o.EmployeeID " +
    	           "    JOIN TotalSalesPerOrder tspo ON o.OrderID = tspo.OrderID " +
    	           "    GROUP BY e.EmployeeID " +
    	           ") " +
    	           "SELECT " +
    	           "    e.EmployeeID, " +
    	           "    e.EmployeeFirstName + ' ' + e.EmployeeLastName AS EmployeeName, " +
    	           "    COUNT(o.OrderID) AS NumberOfOrders, " +
    	           "    SUM(tspo.TotalSaleAmount) AS TotalSales, " +
    	           "    AVG(tspo.TotalSaleAmount) AS AverageOrderValue, " +
    	           "    tce.TotalCommission " +
    	           "FROM HumanResources.Employee e " +
    	           "JOIN Sales.[Order] o ON e.EmployeeID = o.EmployeeID " +
    	           "JOIN TotalSalesPerOrder tspo ON o.OrderID = tspo.OrderID " +
    	           "JOIN TotalCommissionPerEmployee tce ON e.EmployeeID = tce.EmployeeID " +
    	           "GROUP BY e.EmployeeID, e.EmployeeFirstName, e.EmployeeLastName, tce.TotalCommission " +
    	           "ORDER BY TotalSales DESC;";
    }
    private static String getAdventureWorks2017ComplexQuery1() {
        return "USE AdventureWorks2017; " +
               "DECLARE @sql NVARCHAR(MAX); " +
               "SET @sql = 'CREATE OR ALTER FUNCTION dbo.GetProductDiscount(@productID INT, @orderDate DATE) ' + " +
               "           'RETURNS DECIMAL(10, 2) ' + " +
               "           'AS ' + " +
               "           'BEGIN ' + " +
               "           '    DECLARE @discount DECIMAL(10, 2); ' + " +
               "           '    IF @productID % 2 = 0 AND @orderDate >= ''2023-01-01'' ' + " +
               "           '        SET @discount = 0.1; ' + " +
               "           '    ELSE ' + " +
               "           '        SET @discount = 0.05; ' + " +
               "           '    RETURN @discount; ' + " +
               "           'END;'; " +
               "EXEC sp_executesql @sql; " +
               "WITH SalesData AS ( " +
               "    SELECT " +
               "        soh.SalesOrderID, " +
               "        sod.ProductID, " +
               "        p.Name AS ProductName, " +
               "        soh.OrderDate, " +
               "        sod.OrderQty, " +
               "        sod.UnitPrice, " +
               "        dbo.GetProductDiscount(sod.ProductID, soh.OrderDate) AS Discount, " +
               "        sod.LineTotal " +
               "    FROM " +
               "        Sales.SalesOrderHeader AS soh " +
               "    INNER JOIN " +
               "        Sales.SalesOrderDetail AS sod ON soh.SalesOrderID = sod.SalesOrderID " +
               "    INNER JOIN " +
               "        Production.Product AS p ON sod.ProductID = p.ProductID " +
               ") " +
               "SELECT " +
               "    YEAR(OrderDate) AS OrderYear, " +
               "    ProductName, " +
               "    COUNT(*) AS TotalOrders, " +
               "    SUM(OrderQty) AS TotalQuantity, " +
               "    AVG(UnitPrice) AS AvgUnitPrice, " +
               "    SUM(LineTotal) AS TotalSales, " +
               "    SUM(LineTotal * (1 - Discount)) AS TotalSalesWithDiscount " +
               "FROM " +
               "    SalesData " +
               "GROUP BY " +
               "    YEAR(OrderDate), " +
               "    ProductName " +
               "ORDER BY " +
               "    OrderYear, " +
               "    ProductName;";
    }
    private static String getAdventureWorksDW2017ComplexQuery1() {
        return "USE AdventureWorksDW2017; " +
               "DECLARE @sql NVARCHAR(MAX); " +
               "SET @sql = 'CREATE OR ALTER FUNCTION dbo.fn_GetTotalSales (@ProductCategoryKey INT) ' + " +
               "           'RETURNS DECIMAL(18,2) ' + " +
               "           'AS ' + " +
               "           'BEGIN ' + " +
               "           '    DECLARE @TotalSales DECIMAL(18,2); ' + " +
               "           '    SELECT @TotalSales = SUM(FactInternetSales.SalesAmount) ' + " +
               "           '    FROM FactInternetSales ' + " +
               "           '    JOIN DimProduct ON FactInternetSales.ProductKey = DimProduct.ProductKey ' + " +
               "           '    JOIN DimProductSubcategory ON DimProduct.ProductSubcategoryKey = DimProductSubcategory.ProductSubcategoryKey ' + " +
               "           '    WHERE DimProductSubcategory.ProductCategoryKey = @ProductCategoryKey; ' + " +
               "           '    RETURN @TotalSales; ' + " +
               "           'END;'; " +
               "EXEC sp_executesql @sql; " +
               "WITH CategorySales AS ( " +
               "    SELECT " +
               "        DimProductCategory.ProductCategoryKey, " +
               "        DimProductCategory.EnglishProductCategoryName AS ProductCategory, " +
               "        dbo.fn_GetTotalSales(DimProductCategory.ProductCategoryKey) AS TotalSales " +
               "    FROM " +
               "        DimProductCategory " +
               ") " +
               "SELECT " +
               "    cs.ProductCategoryKey, " +
               "    cs.ProductCategory, " +
               "    cs.TotalSales " +
               "FROM " +
               "    CategorySales cs " +
               "ORDER BY " +
               "    cs.TotalSales DESC;";
    }
    private static String getWideWorldImportersComplexQuery1() {
        return "USE WideWorldImporters; " +
               "DECLARE @sql NVARCHAR(MAX); " +
               "SET @sql = 'CREATE OR ALTER FUNCTION dbo.fn_GetTotalRevenue (@CustomerID INT) ' + " +
               "           'RETURNS DECIMAL(18,2) ' + " +
               "           'AS ' + " +
               "           'BEGIN ' + " +
               "           '    DECLARE @TotalRevenue DECIMAL(18,2); ' + " +
               "           '    SELECT @TotalRevenue = SUM(InvoiceLines.Quantity * InvoiceLines.UnitPrice) ' + " +
               "           '    FROM Sales.InvoiceLines ' + " +
               "           '    JOIN Sales.Invoices ON InvoiceLines.InvoiceID = Invoices.InvoiceID ' + " +
               "           '    WHERE Invoices.CustomerID = @CustomerID; ' + " +
               "           '    RETURN @TotalRevenue; ' + " +
               "           'END;'; " +
               "EXEC sp_executesql @sql; " +
               "WITH CustomerRevenue AS ( " +
               "    SELECT " +
               "        Customers.CustomerID, " +
               "        Customers.CustomerName, " +
               "        dbo.fn_GetTotalRevenue(Customers.CustomerID) AS TotalRevenue " +
               "    FROM " +
               "        Sales.Customers " +
               "    JOIN " +
               "        Sales.Invoices ON Customers.CustomerID = Invoices.CustomerID " +
               "    JOIN " +
               "        Sales.InvoiceLines ON Invoices.InvoiceID = InvoiceLines.InvoiceID " +
               "    GROUP BY " +
               "        Customers.CustomerID, " +
               "        Customers.CustomerName " +
               ") " +
               "SELECT " +
               "    CR.CustomerID, " +
               "    CR.CustomerName, " +
               "    CR.TotalRevenue " +
               "FROM " +
               "    CustomerRevenue CR " +
               "ORDER BY " +
               "    CR.TotalRevenue DESC;";
    }

}


