package jdbcdemo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class JDBC_HasnatulHosna {
    private static final String serverName = "localhost";
    private static final String port = "13001";
    private static final String username = "sa";
    private static final String password = "PH@123456789";

    private static final String connectionUrl = String.format(
            "jdbc:sqlserver://%s:%s;user=%s;password=%s",
            serverName, port, username, password
    );

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JDBC_HasnatulHosna::initializeGUI);
    }

        private static void initializeGUI() {
            JFrame frame = new JFrame("SQL Query Selector");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel mainPanel = new JPanel(new GridLayout(0, 1)); // Main layout for sections

            JPanel mediumPanel = new JPanel(new GridLayout(0, 1));
            mediumPanel.setBorder(BorderFactory.createTitledBorder("Project 1 Queries - Hasnatul Hosna"));

            
//northwindsPanel           
            
            // Add subsections for each database under Medium
            JPanel northwindsPanel = new JPanel();
            northwindsPanel.setBorder(BorderFactory.createTitledBorder("Northwinds2022TSQLV7"));

            // Adding query buttons for Northwinds2022TSQLV7
            for (int i = 0; i < 4; i++) {
                int queryIndex = i;
                JButton queryButton = new JButton("Medium Query " + (i + 1));
                queryButton.setFont(new Font("Arial", Font.PLAIN, 16)); 
                queryButton.setBackground(Color.LIGHT_GRAY); 
                if(i == 0 || i == 2 || i == 4) queryButton.setBackground(Color.RED);
                if (i == 6)queryButton.setBackground(Color.YELLOW);
                queryButton.addActionListener(e -> executeQueryAndDisplayGUI(queryIndex));
                northwindsPanel.add(queryButton);
            }

            // Adding buttons for Complex Query 17 & 19
             
            JButton complexQueryButton7 = new JButton("Complex Query 7");
            complexQueryButton7.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton7.setBackground(Color.LIGHT_GRAY); 
            complexQueryButton7.addActionListener(e -> executeQueryAndDisplayGUI(4));  //20  
            northwindsPanel.add(complexQueryButton7);
            
            
            JButton complexQueryButton1 = new JButton("Complex Query 1");
            complexQueryButton1.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton1.setBackground(Color.LIGHT_GRAY); 
            complexQueryButton1.addActionListener(e -> executeQueryAndDisplayGUI(5));  //20  
            northwindsPanel.add(complexQueryButton1);

            
            JButton complexQueryButton2 = new JButton("Complex Query 2");
            complexQueryButton2.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton2.setBackground(Color.LIGHT_GRAY); 
            complexQueryButton2.addActionListener(e -> executeQueryAndDisplayGUI(18)); 
            northwindsPanel.add(complexQueryButton2);

            JButton complexQueryButton3 = new JButton("Complex Query 3");
            complexQueryButton3.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton3.setBackground(Color.YELLOW); 
            complexQueryButton3.addActionListener(e -> executeQueryAndDisplayGUI(19)); 
            northwindsPanel.add(complexQueryButton3);

            mediumPanel.add(northwindsPanel);

            
            
// adventureWorksPanel           
            
            
            
            JPanel adventureWorksPanel = new JPanel();
            adventureWorksPanel.setBorder(BorderFactory.createTitledBorder("AdventureWorks2017"));

            // Adding query buttons for AdventureWorks2017
            JButton queryButton1 = new JButton("Medium Query 5");
            queryButton1.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
            queryButton1.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
            queryButton1.addActionListener(e -> executeQueryAndDisplayGUI(6)); // Assuming index 8 for the first new query
            adventureWorksPanel.add(queryButton1);

            JButton queryButton2 = new JButton("Medium Query 6");
            queryButton2.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
            queryButton2.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
            queryButton2.addActionListener(e -> executeQueryAndDisplayGUI(7)); // Assuming index 9 for the second new query
            adventureWorksPanel.add(queryButton2);

            JButton queryButton3 = new JButton("Medium Query 7");
            queryButton3.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
            queryButton3.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
            queryButton3.addActionListener(e -> executeQueryAndDisplayGUI(8)); // Assuming index 9 for the second new query
            adventureWorksPanel.add(queryButton3);
            
            JButton queryButton4 = new JButton("Medium Query 8");
            queryButton4.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
            queryButton4.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
            queryButton4.addActionListener(e -> executeQueryAndDisplayGUI(9)); // Assuming index 9 for the second new query
            adventureWorksPanel.add(queryButton4);

            mediumPanel.add(adventureWorksPanel);

            
// adventureWorksDWPanel            
            
            // Adding AdventureWorksDW2017 section
            JPanel adventureWorksDWPanel = new JPanel();
            adventureWorksDWPanel.setBorder(BorderFactory.createTitledBorder("AdventureWorksDW2017"));

            // Adding query buttons for AdventureWorksDW2017
            JButton queryButtonDW1 = new JButton("Medium Query 9"); 
            queryButtonDW1.setFont(new Font("Arial", Font.PLAIN, 16)); 
            queryButtonDW1.setBackground(Color.GREEN); 
            queryButtonDW1.addActionListener(e -> executeQueryAndDisplayGUI(13)); 
            adventureWorksDWPanel.add(queryButtonDW1);

            JButton queryButtonDW2 = new JButton("Medium Query 10"); 
            queryButtonDW2.setFont(new Font("Arial", Font.PLAIN, 16)); 
            queryButtonDW2.setBackground(Color.GREEN); 
            queryButtonDW2.addActionListener(e -> executeQueryAndDisplayGUI(14)); 
            adventureWorksDWPanel.add(queryButtonDW2);

            JButton queryButtonDW3 = new JButton("Medium Query 11"); 
            queryButtonDW3.setFont(new Font("Arial", Font.PLAIN, 16)); 
            queryButtonDW3.setBackground(Color.GREEN); 
            queryButtonDW3.addActionListener(e -> executeQueryAndDisplayGUI(16)); 
            adventureWorksDWPanel.add(queryButtonDW3);

            
            JButton complexQueryButton4 = new JButton("Complex Query 4");
            complexQueryButton4.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton4.setBackground(Color.LIGHT_GRAY); 
            complexQueryButton4.addActionListener(e -> executeQueryAndDisplayGUI(15)); 
            adventureWorksDWPanel.add(complexQueryButton4);
 
            JButton complexQueryButton5 = new JButton("Complex Query 5");
            complexQueryButton5.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton5.setBackground(Color.LIGHT_GRAY); 
            complexQueryButton5.addActionListener(e -> executeQueryAndDisplayGUI(17)); 
            adventureWorksDWPanel.add(complexQueryButton5);
            mediumPanel.add(adventureWorksDWPanel);

            
//wideWorldImportersPanel            
            
            // Adding WideWorldImporters section
            JPanel wideWorldImportersPanel = new JPanel();
            wideWorldImportersPanel.setBorder(BorderFactory.createTitledBorder("WideWorldImporters"));

            JButton queryButtonWWI = new JButton("Medium Query 12"); 
            queryButtonWWI.setFont(new Font("Arial", Font.PLAIN, 16)); 
            queryButtonWWI.setBackground(Color.YELLOW); 
            queryButtonWWI.addActionListener(e -> executeQueryAndDisplayGUI(10)); 
            wideWorldImportersPanel.add(queryButtonWWI);

            mediumPanel.add(wideWorldImportersPanel);

            
//wideWorldImportersDWPanel           
            
            JPanel wideWorldImportersDWPanel = new JPanel();
            wideWorldImportersDWPanel.setBorder(BorderFactory.createTitledBorder("WideWorldImportersDW"));

         // Adding button for WideWorldImportersDW Medium
            JButton queryButtonWWIDW = new JButton("Medium Query 13"); 
            queryButtonWWIDW.setFont(new Font("Arial", Font.PLAIN, 16));
            queryButtonWWIDW.setBackground(Color.LIGHT_GRAY); 
            queryButtonWWIDW.addActionListener(e -> executeQueryAndDisplayGUI(12)); 
            wideWorldImportersDWPanel.add(queryButtonWWIDW);

         // Adding button for WideWorldImportersDW Complex
            JButton complexQueryButton6 = new JButton("Complex Query 6");
            complexQueryButton6.setFont(new Font("Arial", Font.PLAIN, 16));
            complexQueryButton6.setBackground(Color.LIGHT_GRAY); 
            complexQueryButton6.addActionListener(e -> executeQueryAndDisplayGUI(11)); 
            wideWorldImportersDWPanel.add(complexQueryButton6);

            mediumPanel.add(wideWorldImportersDWPanel);

            mainPanel.add(mediumPanel);

            frame.add(mainPanel, BorderLayout.CENTER);

            // Set the window size
            frame.setSize(800, 800);  // adjustable vales

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
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Proposition: Analyze sales performance by employee,  grouping orders by employee and summarizing total orders and total freight cost to provide insights into each employee's sales activity. Retrieve the employee information such as full name, title, city, and country for a comprehensive analysis..</div></html>");
                break;
            case 1:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Retrieving the total number of orders and total freight cost for each shipper. Summarized result with shipper information, including company name and phone number.</div></html>");
                break;
            case 2:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Retrieves the total number of orders and the total sales amount for each customer company.</div></html>");
                break;
            case 3:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Retrieves order details along with the total revenue generated by each order.</div></html>");
                break;
            case 4:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Analyzing Top Suppliers by Total Revenue and Total Products Supplied, identify the top-performing suppliers based on total revenue.</div></html>");
                break;
            case 5:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Retrieves the latest salary information for each employee along with their current and previous salary details and identifies whether there was an increase, decrease, or no change in salary.</div></html>");
                break;
            case 6:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Retrieves the latest pay rate change date and the corresponding pay rate for each job candidate.</div></html>");
                break;
            case 7:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition:  Retrieves the count of addresses in each state or province along with their respective state/province name, code, and country region code, combines the Person Address and Person StateProvince tables, order by the address count in descending order, providing insight into the distribution of addresses across different states or provinces.</div></html>");
                break;
            case 8:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Retrieves unique business entities of person associated with addresses in the city of Monroe.</div></html>");
                break;
            case 9:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Retrieve business id who has vista credit card which expires in 2008 and show their credit card id and number.</div></html>");
                break;
            case 10:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Analyze sales data by calculating the total value of orders and the total quantity of items ordered for each customer and  identify the top-selling items and their contribution to overall revenue.</div></html>");
                break;
            case 11:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Analyze the sales performance of each customer:\n"
                		+ "\n"
                		+ "This SQL query combines data from the Fact.Sale, Dimension.Customer, and Fact.Order tables to analyze the sales performance of each customer.  Specifically analyzes the sales performance for \"Tailspin Toys (Head Office)\" and \"Tailspin Toys (Peeples Valley, AZ)\" customers. It calculates their total sales value, the number of sales, and the total number of orders.</div></html>");
                break;
            case 12:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Analyze the movement data for customers belonging to the 'Tailspin Toys' buying group. Specifically, identify the top 3 and bottom 3 customers based on their total movements.</div></html>");
                break;
            // Complex
            case 13:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Retrieve Essential Customer Details for Newcastle, New South Wales, Australia. </div></html>");
                break;
            case 14:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Retrieve summarized product information, including safety stock levels, categorized by English product category names.</div></html>");
                break;
            case 15:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Analysis of Average End-of-Day Currency Rates Across Organizations</div></html>");
                break;
            case 16:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Analysis of Currency Rate Fluctuations Over Time\n"
                		+ "This query calculates the daily rate fluctuations for each currency and identifies significant fluctuations based on a predefined threshold. It then aggregates the significant fluctuations by year and month, providing insights into the average fluctuation for each currency over time.</div></html>");
                break;
            case 17:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: perform a comparative analysis of currency utilization across different organizations.\n"
                		+ "This query calculates the percentage of total transactions conducted in the primary currency for each organization. It identifies the primary currency for each organization and calculates the percentage of total transactions conducted in that currency. Finally, it filters the results to include only the primary currency for each organization.</div></html>");
                break;
            case 18:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Analyzing Product Distribution by Category and Supplier Country\n"
                		+ "The main objective is to summarize the data by category and supplier country, presenting the total number of products and the count of unique suppliers for each category-country combination.</div></html>");
                break;
            case 19:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Exploring Employee Performance and Customer Demographics.</div></html>");
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
                columnNames = new String[]{"EmployeeId","FullName","EmployeeTitle","EmployeeCity","EmployeeCountry","TotalOrders","TotalFreight"};
                break;
            case 1:
                columnNames = new String[]{"ShipperId","ShipperCompanyName","PhoneNumber","TotalOrders","TotalFreights"};
                break;
            case 2:
                columnNames = new String[]{"CustomerCompanyName","TotalOrders","TotalSales"};
                break;
            case 3:
                columnNames = new String[]{"OrderId,ProductId","UnitPrice","Quantity","DiscountPercentage","TotalRevenue"};
                break;
            case 4:
                columnNames = new String[]{"SupplierRank", "SupplierCompanyName", "SupplierCountry", "TotalProductsSupplied", "TotalRevenue"}; //18
                break;
            case 5:
                columnNames = new String[]{"EmployeeId","EmployeeLastName","EmployeeFirstName","EmployeeTitle","EmployeeTitleOfCourtesy","BirthDate","HireDate","EmployeeAddress","EmployeeCity","EmployeeRegion","EmployeePostalCode","EmployeeCountry","EmployeePhoneNumber","EmployeeManagerId","EmployeeFullName","Department","CurrentSalary","PreviousSalary","SalaryChangeType"}; //20
                break;
 

            case 6:
                columnNames = new String[]{"JobCandidateID","BusinessEntityID","Resume,RateChangeDate","Rate"};
                break;

                
            case 7:
                columnNames = new String[]{"StateProvinceName", "StateProvinceCode", "CountryRegionCode", "AddressCount"};
                break;
            case 8:
                columnNames = new String[]{"BusinessEntityID", "AddressLine1", "AddressLine2", "City", "StateProvinceID", "PostalCodet"};
                break;
            case 9:
                columnNames = new String[]{"BusinessEntityID", "CreditCardID", "CardNumber", "CardType"};
                break;
            case 10:
                columnNames = new String[]{"StockItemID", "TotalSoldQuantity", "TotalRevenue"};
                break;
            case 11: 
                columnNames = new String[]{"Customer Key", "Customer", "TotalSalesValue", "TotalSalesCount", "TotalOrders"};
                break;
            case 12: 
                columnNames = new String[]{"Customer", "Customer Key", "TotalMovementsForTopCustomer", "TotalMovementsForBottomCustomer"}; // Actual column names
                break;
            case 13: 
                columnNames = new String[]{"CustomerKey", "FirstName", "LastName", "EmailAddress", "YearlyIncome", "TotalChildren", "NumberChildrenAtHome", "EnglishEducation", "EnglishOccupation", "HouseOwnerFlag", "NumberCarsOwned", "AddressLine1", "Phone"};
                break;
            case 14: 
                columnNames = new String[]{"EnglishProductCategoryName", "TotalSafetyStock"};
                break;
            case 15: 
                columnNames = new String[]{"CurrencyName", "OverallAvgEndOfDayRate"};
                break;
            case 16: 
                columnNames = new String[]{"CurrencyName", "Year", "Month", "AverageFluctuation"};
                break;
            case 17: 
                columnNames = new String[]{"OrganizationName", "PrimaryCurrency", "TotalTransactions", "PercentageOfTotalTransactions"};
                break;
            case 18: 
                columnNames = new String[]{"CategoryName", "SupplierCountry", "TotalProducts", "UniqueSuppliers"};
                break;
            case 19: 
                columnNames = new String[]{"EmployeeId", "EmployeeLastName", "EmployeeFirstName", "EmployeeTitle", "EmployeeCountryOfOrigin", "TotalOrders", "TotalFreight", "CustomerCountry", "TotalCustomers"};
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
                return generateEmployeeSalesSummaryQuery();
            case 1:
                return retrieveShipperSummaryQuery();
            case 2:
                return getOrderSummaryStatisticsQuery();
            case 3:
                return fetchOrderDetailsWithRevenue();
            case 4:
                return getTopSuppliersInfo(); //complex //18
            case 5:
                return getEmployeeSalaryChangesQuery(); //complex  //20
            case 6:
                return retrieveEmployeePayHistory();  
            case 7: 
                return getStateProvinceAddressCountQuery();
            case 8: 
                return retrieveAddressesInCityMonroe();            
            case 9:
                return getCreditCardInfo();
            case 10:
                return getTopSellingItemsQuery();
            case 11:
                return getCustomerSalesInfo(); //complex
            case 12: 
                return getCustomerMovementsData();            
            case 13:
                return retrieveCustomerData();
            case 14:
                return getProductSafetyStockByCategory();
            case 15:
                return getOverallAvgEndOfDayRates(); //complex
            case 16:
                return getCurrencyFluctuationsData();
            case 17:
                return getPrimaryCurrencyUtilizationQuery(); //complex        
            case 18:
                return getProductSupplierDetailsQuery(); // complex  //17
            case 19: 
                return getEmployeeSalesSummaryQuery(); // complex //19
            default:
                return "";
        }
    }

  
    private static String generateEmployeeSalesSummaryQuery() { // 1
    return  "USE Northwinds2022TSQLV7; " +"WITH SalesSummary AS (" +
            "    SELECT" +
            "        o.EmployeeId," +
            "        COUNT(o.OrderId) AS TotalOrders," +
            "        SUM(o.Freight) AS TotalFreight" +
            "    FROM" +
            "        Sales.[Order] o" +
            "    GROUP BY" +
            "        o.EmployeeId" +
            ")," +
            "EmployeeInfo AS (" +
            "    SELECT" +
            "        e.EmployeeId," +
            "        CONCAT(e.EmployeeFirstName, ' ', e.EmployeeLastName) AS FullName," +
            "        e.EmployeeTitle," +
            "        e.EmployeeCity," +
            "        e.EmployeeCountry" +
            "    FROM" +
            "        HumanResources.Employee e" +
            ")" +
            "SELECT" +
            "    e.EmployeeId," +
            "    e.FullName," +
            "    e.EmployeeTitle," +
            "    e.EmployeeCity," +
            "    e.EmployeeCountry," +
            "    s.TotalOrders," +
            "    s.TotalFreight" +
            "FROM" +
            "    EmployeeInfo e" +
            "JOIN" +
            "    SalesSummary s ON e.EmployeeId = s.EmployeeId;";
}
  
    
   private static String retrieveShipperSummaryQuery() { //2
    return   "USE Northwinds2022TSQLV7; " +"WITH OrderSummary AS (" +
               "SELECT " +
                   "o.ShipperId, " +
                   "COUNT(o.OrderId) AS TotalOrders, " +
                   "SUM(o.Freight) AS TotalFreight " +
               "FROM " +
                   "Sales.[Order] o " +
               "GROUP BY " +
                   "o.ShipperId " +
           "), " +
           "ShipperInfo AS (" +
               "SELECT " +
                   "s.ShipperId, " +
                   "s.ShipperCompanyName, " +
                   "s.PhoneNumber " +
               "FROM " +
                   "Sales.Shipper s " +
           ") " +
           "SELECT " +
               "s.ShipperId, " +
               "s.ShipperCompanyName, " +
               "s.PhoneNumber, " +
               "os.TotalOrders, " +
               "os.TotalFreight " +
           "FROM " +
               "ShipperInfo s " +
           "JOIN " +
               "OrderSummary os ON s.ShipperId = os.ShipperId;";
}
  
   
     private static String getOrderSummaryStatisticsQuery() { //3
    return  "USE Northwinds2022TSQLV7; " +"WITH OrderSummary AS (" +
            "    SELECT " +
            "        c.CustomerCompanyName, " +
            "        o.OrderId, " +
            "        o.OrderDate, " +
            "        SUM(od.UnitPrice * od.Quantity * (1 - od.DiscountPercentage)) AS TotalAmount " +
            "    FROM " +
            "        Sales.Customer c " +
            "    INNER JOIN " +
            "        Sales.[Order] o ON c.CustomerId = o.CustomerId " +
            "    INNER JOIN " +
            "        Sales.OrderDetail od ON o.OrderId = od.OrderId " +
            "    GROUP BY " +
            "        c.CustomerCompanyName, o.OrderId, o.OrderDate " +
            ") " +
            "SELECT " +
            "    CustomerCompanyName, " +
            "    COUNT(OrderId) AS TotalOrders, " +
            "    SUM(TotalAmount) AS TotalSales " +
            "FROM " +
            "    OrderSummary " +
            "GROUP BY " +
            "    CustomerCompanyName;";
}
  
    
    private static String fetchOrderDetailsWithRevenue() { //4
    return      "USE Northwinds2022TSQLV7; " +"WITH OrderRevenue AS (" +
                "SELECT " +
                "o.OrderId, " +
                "SUM(od.UnitPrice * od.Quantity * (1 - od.DiscountPercentage)) AS TotalRevenue " +
                "FROM " +
                "Sales.[Order] o " +
                "INNER JOIN " +
                "Sales.OrderDetail od ON o.OrderId = od.OrderId " +
                "GROUP BY " +
                "o.OrderId " +
            ") " +
            "SELECT " +
                "o.OrderId, " +
                "od.ProductId, " +
                "od.UnitPrice, " +
                "od.Quantity, " +
                "od.DiscountPercentage, " +
                "OrderRevenue.TotalRevenue " +
            "FROM " +
                "Sales.[Order] o " +
            "INNER JOIN " +
                "Sales.OrderDetail od ON o.OrderId = od.OrderId " +
            "INNER JOIN " +
                "OrderRevenue ON o.OrderId = OrderRevenue.OrderId;";
}
 
    private static String getTopSuppliersInfo() { //18
        return   "USE Northwinds2022TSQLV7; " +"WITH SupplierDetails AS (" +
                "SELECT " +
                "s.SupplierId, " +
                "s.SupplierCompanyName, " +
                "s.SupplierCountry, " +
                "COUNT(p.ProductId) AS TotalProductsSupplied, " +
                "SUM(p.UnitPrice) AS TotalRevenue " +
                "FROM " +
                "Production.Supplier s " +
                "LEFT JOIN " +
                "Production.Product p ON s.SupplierId = p.SupplierId " +
                "GROUP BY " +
                "s.SupplierId, " +
                "s.SupplierCompanyName, " +
                "s.SupplierCountry " +
                "), " +
                "TopSuppliers AS (" +
                "SELECT " +
                "*, " +
                "ROW_NUMBER() OVER (ORDER BY TotalRevenue DESC) AS SupplierRank " +
                "FROM " +
                "SupplierDetails " +
                ") " +
                "SELECT top(5) " +
                "SupplierRank, " +
                "SupplierCompanyName, " +
                "SupplierCountry, " +
                "TotalProductsSupplied, " +
                "TotalRevenue " +
                "FROM " +
                "TopSuppliers";
    }
    
    private static String getEmployeeSalaryChangesQuery() { //20
        return "USE Northwinds2022TSQLV7; " +
                "WITH LatestTriggeredEmployeeHistory AS ( " +
                "   SELECT " +
                "       t1.EmployeeId, " +
                "       t1.EmployeeFullName, " +
                "       t1.Department, " +
                "       t1.Salary AS CurrentSalary, " +
                "       t1.SysEndTime AS CurrentSalaryEndTime, " +
                "       t2.Salary AS PreviousSalary, " +
                "       t2.SysEndTime AS PreviousSalaryEndTime, " +
                "       ROW_NUMBER() OVER (PARTITION BY t1.EmployeeId ORDER BY t1.SysEndTime DESC) AS RowNum " +
                "   FROM " +
                "       Triggered.Employee AS t1 " +
                "   LEFT JOIN " +
                "       Triggered.AuditTriggeredEmployeeHistory AS t2 " +
                "   ON " +
                "       t1.EmployeeId = t2.EmployeeId " +
                "   WHERE " +
                "       t1.IsDeleted = 'N' " +
                "       AND t1.SysEndTime = '9999-12-31T23:59:59' " +
                "), " +
                "EmployeeSalaryChange AS ( " +
                "   SELECT " +
                "       EmployeeId, " +
                "       EmployeeFullName, " +
                "       Department, " +
                "       CurrentSalary, " +
                "       CurrentSalaryEndTime, " +
                "       PreviousSalary, " +
                "       PreviousSalaryEndTime, " +
                "       CASE " +
                "           WHEN CurrentSalary > PreviousSalary THEN 'Increase' " +
                "           WHEN CurrentSalary < PreviousSalary THEN 'Decrease' " +
                "           ELSE 'No Change' " +
                "       END AS SalaryChangeType " +
                "   FROM " +
                "       LatestTriggeredEmployeeHistory " +
                "   WHERE " +
                "       RowNum = 1 " +
                ") " +
                "SELECT " +
                "   e.EmployeeId, " +
                "   e.EmployeeLastName, " +
                "   e.EmployeeFirstName, " +
                "   e.EmployeeTitle, " +
                "   e.EmployeeTitleOfCourtesy, " +
                "   e.BirthDate, " +
                "   e.HireDate, " +
                "   e.EmployeeAddress, " +
                "   e.EmployeeCity, " +
                "   e.EmployeeRegion, " +
                "   e.EmployeePostalCode, " +
                "   e.EmployeeCountry, " +
                "   e.EmployeePhoneNumber, " +
                "   e.EmployeeManagerId, " +
                "   t.EmployeeFullName, " +
                "   t.Department, " +
                "   t.CurrentSalary, " +
                "   t.PreviousSalary, " +
                "   t.SalaryChangeType " +
                "FROM " +
                "   HumanResources.Employee AS e " +
                "JOIN " +
                "   EmployeeSalaryChange AS t " +
                "ON " +
                "   e.EmployeeId = t.EmployeeId;";
    }
    
   private static String retrieveEmployeePayHistory() { //5
    return  "USE AdventureWorks2017; " + "SELECT jc.JobCandidateID, jc.BusinessEntityID, jc.Resume, eph.RateChangeDate, eph.Rate " +
            "FROM HumanResources.JobCandidate jc " +
            "LEFT JOIN ( " +
            "    SELECT BusinessEntityID, MAX(RateChangeDate) AS LatestChangeDate " +
            "    FROM HumanResources.EmployeePayHistory " +
            "    GROUP BY BusinessEntityID " +
            ") AS lprc ON jc.BusinessEntityID = lprc.BusinessEntityID " +
            "LEFT JOIN HumanResources.EmployeePayHistory eph ON lprc.BusinessEntityID = eph.BusinessEntityID " +
            "AND lprc.LatestChangeDate = eph.RateChangeDate;";
}
  
  
   private static String getStateProvinceAddressCountQuery() { //6
    return   "USE AdventureWorks2017; " + "SELECT sp.Name AS StateProvinceName, " +
            "sp.StateProvinceCode, " +
            "sp.CountryRegionCode, " +
            "COUNT(a.AddressID) AS AddressCount " +
            "FROM Person.Address a " +
            "INNER JOIN Person.StateProvince sp ON a.StateProvinceID = sp.StateProvinceID " +
            "GROUP BY sp.Name, sp.StateProvinceCode, sp.CountryRegionCode " +
            "ORDER BY AddressCount DESC;";
}
  
   
    private static String retrieveAddressesInCityMonroe() {  //7
    return   "USE AdventureWorks2017; " +"SELECT DISTINCT bea.BusinessEntityID, a.AddressLine1, a.AddressLine2, a.City, a.StateProvinceID, a.PostalCode " +
            "FROM AdventureWorks2017.Person.BusinessEntityAddress bea " +
            "INNER JOIN AdventureWorks2017.Person.Address a ON bea.AddressID = a.AddressID " +
            "WHERE a.City = 'Monroe'";
}
 

   
  private static String getCreditCardInfo() { //8
    return         "USE AdventureWorks2017; " +"SELECT pc.BusinessEntityID, pc.CreditCardID, c.CardNumber, c.CardType " +
                   "FROM Sales.PersonCreditCard pc " +
                   "INNER JOIN Sales.CreditCard c ON pc.CreditCardID = c.CreditCardID " +
                   "WHERE c.CardType = 'Vista' AND c.ExpYear = 2008";
  
}
  
    
  
 private static String getTopSellingItemsQuery() { //9
    return  "USE WideWorldImporters; " +"WITH TopSellingItems AS (" +
            "SELECT OL.StockItemID, " +
            "SUM(OL.Quantity) AS TotalSoldQuantity, " +
            "SUM(OL.Quantity * OL.UnitPrice) AS TotalRevenue, " +
            "ROW_NUMBER() OVER (ORDER BY SUM(OL.Quantity * OL.UnitPrice) DESC) AS RowNum " +
            "FROM Sales.OrderLines OL " +
            "GROUP BY OL.StockItemID) " +
            "SELECT StockItemID, TotalSoldQuantity, TotalRevenue " +
            "FROM TopSellingItems " +
            "WHERE RowNum <= 5;";
}
 

  private static String getCustomerSalesInfo() { //10
    return "USE WideWorldImportersDW; " +
            "WITH CustomerSales AS ( " +
            "    SELECT " +
            "        C.[Customer Key], " +
            "        C.Customer, " +
            "        SUM(S.[Total Including Tax]) AS TotalSalesValue, " +
            "        COUNT(S.[Sale Key]) AS TotalSalesCount " +
            "    FROM " +
            "        Fact.Sale S " +
            "    INNER JOIN " +
            "        Dimension.Customer C ON S.[Customer Key] = C.[Customer Key] " +
            "    WHERE " +
            "        C.Customer IN ('Tailspin Toys (Head Office)', 'Tailspin Toys (Peeples Valley, AZ)') " +
            "    GROUP BY " +
            "        C.[Customer Key], C.Customer " +
            "), " +
            "CustomerOrders AS ( " +
            "    SELECT " +
            "        C.[Customer Key], " +
            "        COUNT(O.[Order Key]) AS TotalOrders " +
            "    FROM " +
            "        Fact.[Order] O " +
            "    INNER JOIN " +
            "        Dimension.Customer C ON O.[Customer Key] = C.[Customer Key] " +
            "    WHERE " +
            "        C.Customer IN ('Tailspin Toys (Head Office)', 'Tailspin Toys (Peeples Valley, AZ)') " +
            "    GROUP BY " +
            "        C.[Customer Key] " +
            ") " +
            "SELECT " +
            "    CS.[Customer Key], " +
            "    CS.Customer, " +
            "    CS.TotalSalesValue, " +
            "    CS.TotalSalesCount, " +
            "    CO.TotalOrders " +
            "FROM " +
            "    CustomerSales CS " +
            "LEFT JOIN " +
            "    CustomerOrders CO ON CS.[Customer Key] = CO.[Customer Key] " +
            "ORDER BY " +
            "    CS.TotalSalesValue DESC;";
}
  
    
  private static String getCustomerMovementsData() { //11
    return  "USE WideWorldImportersDW; " +"WITH CustomerMovements AS ( " +
            "SELECT " +
            "C.Customer, " +
            "C.[Customer Key], " +
            "SUM(M.Quantity) AS TotalMovements " +
            "FROM " +
            "Dimension.Customer C " +
            "JOIN " +
            "Fact.Movement M ON C.[Customer Key] = M.[Customer Key] " +
            "WHERE " +
            "C.[Buying Group] = 'Tailspin Toys' " +
            "GROUP BY " +
            "C.Customer, C.[Customer Key] " +
            "), " +
            "TopCustomers AS ( " +
            "SELECT " +
            "CM.Customer, " +
            "CM.[Customer Key], " +
            "CM.TotalMovements, " +
            "ROW_NUMBER() OVER (ORDER BY CM.TotalMovements DESC) AS MovementRank " +
            "FROM " +
            "CustomerMovements CM " +
            "), " +
            "BottomCustomers AS ( " +
            "SELECT " +
            "CM.Customer, " +
            "CM.[Customer Key], " +
            "CM.TotalMovements, " +
            "ROW_NUMBER() OVER (ORDER BY CM.TotalMovements ASC) AS MovementRank " +
            "FROM " +
            "CustomerMovements CM " +
            ") " +
            "SELECT " +
            "TC.Customer, " +
            "TC.[Customer Key], " +
            "TC.TotalMovements AS TotalMovementsForTopCustomer, " +
            "BC.TotalMovements AS TotalMovementsForBottomCustomer " +
            "FROM " +
            "TopCustomers TC " +
            "FULL JOIN " +
            "BottomCustomers BC ON TC.MovementRank = BC.MovementRank " +
            "WHERE " +
            "TC.MovementRank <= 3 OR BC.MovementRank <= 3 " +
            "ORDER BY " +
            "TC.MovementRank;";
}
  
   
    private static String retrieveCustomerData() { //12
    return  "USE AdventureWorksDW2017; " +"SELECT " +
            "    dc.CustomerKey, " +
            "    dc.FirstName, " +
            "    dc.LastName, " +
            "    dc.EmailAddress, " +
            "    dc.YearlyIncome, " +
            "    dc.TotalChildren, " +
            "    dc.NumberChildrenAtHome, " +
            "    dc.EnglishEducation, " +
            "    dc.EnglishOccupation, " +
            "    dc.HouseOwnerFlag, " +
            "    dc.NumberCarsOwned, " +
            "    dc.AddressLine1, " +
            "    dc.Phone " +
            "FROM " +
            "    dbo.DimCustomer AS dc " +
            "JOIN " +
            "    dbo.DimGeography AS dg ON dc.GeographyKey = dg.GeographyKey " +
            "WHERE " +
            "    dg.City = 'Newcastle' " +
            "    AND dg.StateProvinceName = 'New South Wales' " +
            "    AND dg.CountryRegionCode = 'AU'";
}
   
   
  private static String getProductSafetyStockByCategory() { //13
    return  "USE AdventureWorksDW2017; " +"WITH ProductDetails AS (" +
            "    SELECT " +
            "        p.ProductKey, " +
            "        p.ProductAlternateKey, " +
            "        p.SafetyStockLevel, " +
            "        ps.EnglishProductSubcategoryName, " +
            "        pc.EnglishProductCategoryName " +
            "    FROM " +
            "        dbo.DimProduct p " +
            "    INNER JOIN " +
            "        dbo.DimProductSubcategory ps ON p.ProductSubcategoryKey = ps.ProductSubcategoryKey " +
            "    INNER JOIN " +
            "        dbo.DimProductCategory pc ON ps.ProductCategoryKey = pc.ProductCategoryKey " +
            "), " +
            "CalcSafetyStock AS ( " +
            "    SELECT " +
            "        EnglishProductCategoryName, " +
            "        SUM(SafetyStockLevel) AS TotalSafetyStock " +
            "    FROM " +
            "        ProductDetails " +
            "    GROUP BY " +
            "        EnglishProductCategoryName " +
            ") " +
            "SELECT " +
            "    EnglishProductCategoryName, " +
            "    TotalSafetyStock " +
            "FROM " +
            "    CalcSafetyStock " +
            "ORDER BY " +
            "    EnglishProductCategoryName;";
}
 
    

 private static String getOverallAvgEndOfDayRates() { //14
    return  "USE AdventureWorksDW2017; " +"WITH AvgEndOfDayRates AS ( " +
            "SELECT c.CurrencyName, o.OrganizationName, AVG(f.EndOfDayRate) AS AvgEndOfDayRate " +
            "FROM AdventureWorksDW2017.dbo.FactCurrencyRate f " +
            "INNER JOIN AdventureWorksDW2017.dbo.DimCurrency c ON f.CurrencyKey = c.CurrencyKey " +
            "INNER JOIN AdventureWorksDW2017.dbo.DimOrganization o ON c.CurrencyKey = o.CurrencyKey " +
            "GROUP BY c.CurrencyName, o.OrganizationName ) " +
            "SELECT CurrencyName, AVG(AvgEndOfDayRate) AS OverallAvgEndOfDayRate " +
            "FROM AvgEndOfDayRates " +
            "GROUP BY CurrencyName;";
}
  
    
 private static String getCurrencyFluctuationsData() { //15
    return   "USE AdventureWorksDW2017; " +"WITH CurrencyFluctuations AS ( " +
            "SELECT DC.CurrencyName, FCR.Date, FCR.EndOfDayRate - LAG(FCR.EndOfDayRate) OVER (PARTITION BY FCR.CurrencyKey ORDER BY FCR.Date) AS RateFluctuation " +
            "FROM dbo.FactCurrencyRate FCR " +
            "JOIN dbo.DimCurrency DC ON FCR.CurrencyKey = DC.CurrencyKey ), " +
            "SignificantFluctuations AS ( " +
            "SELECT CurrencyName, Date, RateFluctuation " +
            "FROM CurrencyFluctuations " +
            "WHERE ABS(RateFluctuation) > 0.05 ) " +
            "SELECT CurrencyName, DATEPART(YEAR, Date) AS Year, DATEPART(MONTH, Date) AS Month, AVG(RateFluctuation) AS AverageFluctuation " +
            "FROM SignificantFluctuations " +
            "GROUP BY CurrencyName, DATEPART(YEAR, Date), DATEPART(MONTH, Date) " +
            "ORDER BY CurrencyName, Year, Month;";
}
 
   

 private static String getPrimaryCurrencyUtilizationQuery() { //16
    return  "USE AdventureWorksDW2017; " +"WITH CurrencyUtilization AS ( " +
            "SELECT " +
            "O.OrganizationName, " +
            "DC.CurrencyName AS PrimaryCurrency, " +
            "COUNT(*) AS TotalTransactions " +
            "FROM " +
            "AdventureWorksDW2017.dbo.DimOrganization O " +
            "JOIN " +
            "AdventureWorksDW2017.dbo.DimCurrency DC ON O.CurrencyKey = DC.CurrencyKey " +
            "GROUP BY " +
            "O.OrganizationName, " +
            "DC.CurrencyName " +
            "), " +
            "PrimaryCurrencyUtilization AS ( " +
            "SELECT " +
            "OrganizationName, " +
            "PrimaryCurrency, " +
            "TotalTransactions, " +
            "ROW_NUMBER() OVER (PARTITION BY OrganizationName ORDER BY TotalTransactions DESC) AS Rank " +
            "FROM " +
            "CurrencyUtilization " +
            ") " +
            "SELECT " +
            "OrganizationName, " +
            "PrimaryCurrency, " +
            "TotalTransactions, " +
            "ROUND((TotalTransactions / SUM(TotalTransactions) OVER (PARTITION BY OrganizationName)) * 100, 2) AS PercentageOfTotalTransactions " +
            "FROM " +
            "PrimaryCurrencyUtilization " +
            "WHERE " +
            "Rank = 1;";
    }
    
  private static String getProductSupplierDetailsQuery() { //17
    return  "USE Northwinds2022TSQLV7; " +"WITH ProductSupplierDetails AS (" +
            "    SELECT" +
            "        p.ProductId," +
            "        p.ProductName," +
            "        c.CategoryName," +
            "        s.SupplierCompanyName," +
            "        s.SupplierCountry" +
            "    FROM" +
            "        Northwinds2022TSQLV7.Production.Product p" +
            "    INNER JOIN" +
            "        Northwinds2022TSQLV7.Production.Category c ON p.CategoryId = c.CategoryId" +
            "    INNER JOIN" +
            "        Northwinds2022TSQLV7.Production.Supplier s ON p.SupplierId = s.SupplierId" +
            ")" +
            "SELECT" +
            "    CategoryName," +
            "    SupplierCountry," +
            "    COUNT(ProductId) AS TotalProducts," +
            "    COUNT(DISTINCT SupplierCompanyName) AS UniqueSuppliers" +
            "FROM" +
            "    ProductSupplierDetails" +
            "GROUP BY" +
            "    CategoryName, SupplierCountry" +
            "ORDER BY" +
            "    CategoryName, SupplierCountry;";
}
  

  
   
  private static String getEmployeeSalesSummaryQuery() { //19
    return "USE Northwinds2022TSQLV7; " +
            "WITH OrderDetails AS (" +
            "    SELECT " +
            "        o.OrderId, " +
            "        o.CustomerId, " +
            "        o.EmployeeId, " +
            "        o.OrderDate, " +
            "        o.Freight, " +
            "        c.CustomerCountry, " +
            "        e.EmployeeTitle, " +
            "        e.EmployeeCountry, " +
            "        ROW_NUMBER() OVER (PARTITION BY o.EmployeeId ORDER BY o.OrderDate DESC) AS OrderRank " +
            "    FROM " +
            "        Sales.[Order] o " +
            "    INNER JOIN " +
            "        Sales.Customer c ON o.CustomerId = c.CustomerId " +
            "    INNER JOIN " +
            "        HumanResources.Employee e ON o.EmployeeId = e.EmployeeId " +
            "), " +
            "TopEmployees AS (" +
            "    SELECT " +
            "        EmployeeId, " +
            "        COUNT(OrderId) AS TotalOrders, " +
            "        SUM(Freight) AS TotalFreight, " +
            "        MAX(OrderRank) AS HighestOrderRank " +
            "    FROM " +
            "        OrderDetails " +
            "    GROUP BY " +
            "        EmployeeId " +
            "), " +
            "CustomerDemographics AS (" +
            "    SELECT " +
            "        CustomerCountry, " +
            "        COUNT(CustomerId) AS TotalCustomers " +
            "    FROM " +
            "        Sales.Customer " +
            "    GROUP BY " +
            "        CustomerCountry " +
            ") " +
            "SELECT " +
            "    e.EmployeeId, " +
            "    e.EmployeeLastName, " +
            "    e.EmployeeFirstName, " +
            "    e.EmployeeTitle, " +
            "    e.EmployeeCountry AS EmployeeCountryOfOrigin, " +
            "    te.TotalOrders, " +
            "    te.TotalFreight, " +
            "    cd.CustomerCountry, " +
            "    cd.TotalCustomers " +
            "FROM " +
            "    HumanResources.Employee e " +
            "LEFT JOIN " +
            "    TopEmployees te ON e.EmployeeId = te.EmployeeId " +
            "LEFT JOIN " +
            "    CustomerDemographics cd ON e.EmployeeCountry = cd.CustomerCountry " +
            "ORDER BY " +
            "    te.TotalOrders DESC;";
}
  
   
  

}


