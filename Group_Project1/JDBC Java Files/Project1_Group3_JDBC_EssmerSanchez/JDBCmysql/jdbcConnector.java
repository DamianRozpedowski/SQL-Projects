import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class jdbcConnector {
    private static final String serverName = "localhost";
    private static final String port = "13001";
    private static final String username = "sa";
    private static final String password = "EZ@15309699";

    private static final String connectionUrl = String.format(
            "jdbc:sqlserver://%s:%s;user=%s;password=%s",
            serverName, port, username, password
    );

    public static void main(String[] args) {
        SwingUtilities.invokeLater(jdbcConnector::initializeGUI);
    }

        private static void initializeGUI() {
            JFrame frame = new JFrame("SQL Query Selector");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel mainPanel = new JPanel(new GridLayout(0, 1)); // Main layout for sections

            JPanel mediumPanel = new JPanel(new GridLayout(0, 1));
            mediumPanel.setBorder(BorderFactory.createTitledBorder("Project 1 Queries - Essmer Sanchez"));

            // Add subsections for each database under Medium
            //Adding Northwinds Section
            JPanel northwindsPanel = new JPanel();
            northwindsPanel.setBorder(BorderFactory.createTitledBorder("Northwinds2022TSQLV7"));
            mediumPanel.add(northwindsPanel);
            
            // Adding query button for Northwinds2022TSQLV7
            JButton northWqueryButton = new JButton("Medium Query " + 1);
            northWqueryButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
            northWqueryButton.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
            northWqueryButton.addActionListener(e -> executeQueryAndDisplayGUI(1));
            northwindsPanel.add(northWqueryButton);
            
            //Adding AdventureWorks Section
            JPanel adventureWorksPanel = new JPanel();
            adventureWorksPanel.setBorder(BorderFactory.createTitledBorder("AdventureWorks2017"));
            mediumPanel.add(adventureWorksPanel);
            
			for (int i = 1; i <= 4; i++) {
				int adventureWorksqueryIndex = i;
				JButton queryButton = new JButton("Medium Query " + (i + 1));
				queryButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
				queryButton.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
				queryButton.addActionListener(e -> executeQueryAndDisplayGUI(adventureWorksqueryIndex + 1));
				adventureWorksPanel.add(queryButton);
			}

//            // Adding query buttons for AdventureWorks2017

            // Adding buttons for AdventureWorks2017 Complex Queries
			for (int i = 0; i < 3; i++) {
				int adventureWorksqueryComplexIndex = i;
				JButton queryButton = new JButton("Complex Query " + (i + 1));
				queryButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
				queryButton.setBackground(Color.CYAN); // Set background color for Medium Query buttons
				queryButton.addActionListener(e -> executeQueryAndDisplayGUI(adventureWorksqueryComplexIndex + 14));
				adventureWorksPanel.add(queryButton);
			}


            // Adding AdventureWorksDW2017 section
            JPanel adventureWorksDWPanel = new JPanel();
            adventureWorksDWPanel.setBorder(BorderFactory.createTitledBorder("AdventureWorksDW2017"));
            
			// Adding button for AdventureWorksDW2017 Medium Queries
			for (int i = 5; i <= 7; i++) {
				int adventureWorksDWqueryIndex = i;
				JButton queryButton = new JButton("Medium Query " + (i + 1));
				queryButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
				queryButton.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
				queryButton.addActionListener(e -> executeQueryAndDisplayGUI(adventureWorksDWqueryIndex + 1));
				adventureWorksDWPanel.add(queryButton);
			}

			// Adding button for AdventureWorksDW2017 Complex Queries
			for (int i = 3; i < 5; i++) {
				int adventureWorksDWqueryComplexIndex = i;
				JButton queryButton = new JButton("Complex Query " + (i + 1));
				queryButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
				queryButton.setBackground(Color.CYAN); // Set background color for Medium Query buttons
				queryButton.addActionListener(e -> executeQueryAndDisplayGUI(adventureWorksDWqueryComplexIndex + 14)); //=17
				adventureWorksDWPanel.add(queryButton);
			}

            mediumPanel.add(adventureWorksDWPanel);

            // Adding WideWorldImporters section
            JPanel wideWorldImportersPanel = new JPanel();
            wideWorldImportersPanel.setBorder(BorderFactory.createTitledBorder("WideWorldImporters"));
            
			for (int i = 8; i <= 11; i++) {
				int wideWorldImportersqueryIndex = i;
				JButton queryButton = new JButton("Medium Query " + (i + 1));
				queryButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
				queryButton.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
				queryButton.addActionListener(e -> executeQueryAndDisplayGUI(wideWorldImportersqueryIndex + 1));
				wideWorldImportersPanel.add(queryButton);
			}

            // Adding button for WideWorldImporters Complex Queries
			for (int i = 5; i < 7; i++) {
				int wideWorldImportersqueryComplexIndex = i;
				JButton queryButton = new JButton("Complex Query " + (i + 1));
				queryButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
				queryButton.setBackground(Color.CYAN); // Set background color for Medium Query buttons
				queryButton.addActionListener(e -> executeQueryAndDisplayGUI(wideWorldImportersqueryComplexIndex + 14)); //=19
				wideWorldImportersPanel.add(queryButton);
			}

            mediumPanel.add(wideWorldImportersPanel);
            
            // Adding WideWorldImportersDW section
            JPanel wideWorldImportersDWPanel = new JPanel();
            wideWorldImportersDWPanel.setBorder(BorderFactory.createTitledBorder("WideWorldImportersDW"));

            JButton queryButtonWWIDW = new JButton("Medium Query 13"); // Button for WideWorldImportersDW Query 1
            queryButtonWWIDW.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase button font size
            queryButtonWWIDW.setBackground(Color.LIGHT_GRAY); // Set background color for Medium Query buttons
            queryButtonWWIDW.addActionListener(e -> executeQueryAndDisplayGUI(13));
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
            case 1: 
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 1</div></html>");
                break;
            case 2:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 2</div></html>");
                break;
            case 3:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 3</div></html>");
                break;
            case 4:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 4</div></html>");
                break;
            case 5:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 5</div></html>");
                break;
            case 6:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 6</div></html>");
                break;
            case 7:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 7</div></html>");
                break;
            case 8:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 8</div></html>");
                break;
            case 9:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 9</div></html>");
                break;
            case 10:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 10</div></html>");
                break;
            case 11:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 11</div></html>");
                break;
            case 12:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 12</div></html>");
                break;
            case 13:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Query 13</div></html>");
                break;
            case 14: //complex 1
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Complex Query 1</div></html>");
                break;
            case 15: //complex 2
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Complex Query 2</div></html>");
                break;
            case 16: //complex 3
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Complex Query 3</div></html>");
                break;
            case 17: //complex 4
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Complex Query 4</div></html>");
                break;
            case 18: //complex 5
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Complex Query 5</div></html>");
                break;
            case 19: //complex 6
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Complex Query 6</div></html>");
                break;
            case 20: //complex 7
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Complex Query 7</div></html>");
                break;
            default:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center; padding: 10px;'>Proposition: Lorem Ipsum for Generic Query</div></html>");
                break;
        }
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Adjust font as needed
        titleLabel.setPreferredSize(new Dimension(800, 80)); // Set preferred size for the label with increased height
        panel.add(titleLabel, BorderLayout.NORTH);

        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

        // Set column names based on the selected query
        String[] columnNames;
        switch (queryIndex) {
            case 1: //Northwinds Q20 Azure
                columnNames = new String[]{"EmployeeCountry", "EmployeeRegion", "EmployeeCity"};
                break;
            case 2: //AdventureWorks Q7 Azure
                columnNames = new String[]{"CustomerID", "CompanyName"};
                break;
            case 3: //AdventureWorks Q8 Azure
                columnNames = new String[]{"CustomerID", "CompanyName", "TotalOrders"};
                break;
            case 4: //AdventureWorks Q9 Azure
                columnNames = new String[]{"CustomerID", "FirstName", "LastName"};
                break;
            case 5: //AdventureWorks Q11 Azure
                columnNames = new String[]{"NationalIDNumber", "JobTitle", "HireDate", "FirstName", "LastName", "TenureYears"};
                break;
            case 6: //AdventureWorksDW Q12?? Azure
                columnNames = new String[]{""};
                break;
            case 7: //AdventureWorksDW Q13 Azure
                columnNames = new String[]{"ProductKey", "EnglishProductName", "TotalSalesAmount", "CategoryAvgSales", "ProductSubCategoryKey"};
                break;
            case 8: //AdventureWorksDW Q14 Azure
                columnNames = new String[]{"CustomerKey", "FirstName", "LastName", "TotalSalesAmount", "TotalOrderCount"};
                break;
            case 9: //WideWorldImports Q6 Azure
                columnNames = new String[]{"StockItemID", "StockItemName", "StockGroupName", "AvgPurchasePrice", "OrderCount"};
                break;
            case 10: //WideWorldImports Q16 Azure
                columnNames = new String[]{"OrderID", "OrderDate", "CustomerName", "TotalOrders", "LastOrdeDate"};
                break;
            case 11: //WideWorldImports Q18 Azure
                columnNames = new String[]{"SupplierID", "SupplierName", "TotalOrderValue", "UniqueProducts", "AvgQuantityPerOrder", "LastPurchasDate"};
                break;
            case 12: //WideWorldImports Q19 Azure
                columnNames = new String[]{"StockItemID", "StockItemName", "StockGroupName", "AvgPurchasePrice", "OrderCount"};
                break;
            case 13: //WideWorldImportsDW Q4 Azure
                columnNames = new String[]{"Year", "City", "TotalSalesAmount", "TotalOrders"}; // Actual column names
                break;
			case 14: // complex 1 AdventureWorks Q1
				columnNames = new String[] { "BusinessEntityID", "TerritoryID", "TotalSales", "OrderCount" };
				break;
			case 15: // complex 2 AdventureWorks Q2
				columnNames = new String[] { "CategoryName", "Sales2013", "Sales2014", "GrowthRate"};
				break;
			case 16: // complex 3 AdvetureWorks Q10
				columnNames = new String[] { "CustomerID", "FirstName", "LastName", "OrderDate", "OrderYear", "OrderMonth", "OrderQty", "TotalSum"};
				break;
			case 17: // complex 4 AdventureWorksDW Q3
				columnNames = new String[] { "CustomerKey", "FirstName", "LastName", "SalesYear", "TotalSalesAmount", "MaxSalesYear", "OrderCountInSalesYear" };
				break;
			case 18: // complex 5 AdventureWorksDW Q15
				columnNames = new String[] { "CustomerKey", "SalesOrderNumber", "ProductKey", "SalesAmount", "TotalWithTax", "SalesTerritoryRegion" };
				break;
			case 19: // complex 6 WideWorldImporters Q5
				columnNames = new String[] { "CityName", "TotalCustomers", "TotalSuppliers", "CombinedCount", "CityRank"};
				break;
			case 20: // complex 7 WideWorldImporters Q17
				columnNames = new String[] { "CustomerID", "CustomerName", "TotalSalesAmount", "LastOrderDate", "AvgQuantityPerOrder"};
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
            case 1:
                return getMedium1();
            case 2:
                return getMedium2();
            case 3:
                return getMedium3();
            case 4:
                return getMedium4();
            case 5:
                return getMedium5();
            case 6:
                return getMedium6();
            case 7:
                return getMedium7();
            case 8:
                return getMedium8();
            case 9:
                return getMedium9();
            case 10: // New case for AdventureWorksDW2017 query
                return getMedium10();
            case 11:
                return getMedium11();
            case 12:
                return getMedium12();
            case 13:
                return getMedium13();
            case 14:
                return getComplex1();
            case 15:
                return getComplex2();
            case 16:
                return getComplex3();
            case 17: // New case for AdventureWorks2017ComplexQuery1
                return getComplex4();
            case 18: // New case for AdventureWorksDW2017ComplexQuery1
                return getComplex5();
            case 19: // New case for WideWorldImportersComplexQuery1
                return getComplex6();
            case 20:
                return getComplex7();
            default:
                return "";
        }
    }

    private static String getMedium1() {
        return "USE Northwinds2022TSQLV7;" +
        		" WITH INTERSECT_ALL AS ( "
                + "SELECT "
                + "ROW_NUMBER() OVER(PARTITION BY EmployeeCountry, EmployeeRegion, EmployeeCity ORDER BY (SELECT 0)) AS RowNum, "
                + "EmployeeCountry, EmployeeRegion, EmployeeCity "
                + "FROM HumanResources.Employee "
                + "INTERSECT "
                + "SELECT "
                + "ROW_NUMBER() OVER(PARTITION BY CustomerCountry, CustomerRegion, CustomerCity ORDER BY (SELECT 0)), "
                + "CustomerCountry, CustomerRegion, CustomerCity "
                + "FROM Sales.Customer "
                + ") "
                + "SELECT EmployeeCountry, EmployeeRegion, EmployeeCity "
                + "FROM INTERSECT_ALL;";
    }
    private static String getMedium2() {
        return "USE AdventureWorks2017; " +
                "SELECT C.CustomerID, C.AccountNumber AS CompanyName " +
                "FROM Sales.Customer AS C " +
                "WHERE EXISTS (SELECT 1 " +
                "              FROM Sales.SalesOrderHeader AS SOH " +
                "              WHERE SOH.CustomerID = C.CustomerID " +
                "              AND SOH.OrderDate >= '20120101' " +
                "              AND SOH.OrderDate < '20130101') " +
                "AND NOT EXISTS (SELECT 1 " +
                "                FROM Sales.SalesOrderHeader AS SOH " +
                "                WHERE SOH.CustomerID = C.CustomerID " +
                "                AND SOH.OrderDate >= '20130101' " +
                "                AND SOH.OrderDate < '20140101') " +
                "ORDER BY C.CustomerID;";
    }
    private static String getMedium3() {
        return "USE AdventureWorks2017; "
        		+ "SELECT TOP 5 C.CustomerID, C.AccountNumber AS CompanyName, "
                + "COUNT(SOH.SalesOrderID) AS TotalOrders "
                + "FROM Sales.Customer AS C "
                + "JOIN Sales.SalesOrderHeader AS SOH ON C.CustomerID = SOH.CustomerID "
                + "WHERE SOH.OrderDate >= '20120101' "
                + "AND SOH.OrderDate < '20130101' "
                + "GROUP BY C.CustomerID, C.AccountNumber "
                + "ORDER BY TotalOrders DESC;";
    }
    private static String getMedium4() {
        return "USE AdventureWorks2017; "
                + "SELECT C.CustomerID, P.FirstName, P.LastName "
                + "FROM Sales.Customer AS C "
                + "JOIN Person.Person AS P ON C.PersonID = P.BusinessEntityID "
                + "WHERE EXISTS (SELECT 1 "
                + "              FROM Sales.SalesOrderHeader AS SOH "
                + "              WHERE SOH.CustomerID = C.CustomerID "
                + "              AND EXISTS (SELECT 1 "
                + "                          FROM Sales.SalesOrderDetail AS SOD "
                + "                          WHERE SOD.SalesOrderID = SOH.SalesOrderID "
                + "                          AND SOD.ProductID = 776)) "
                + "ORDER BY C.CustomerID;";
    }
    private static String getMedium5() {
        return "USE AdventureWorks2017; "
                + "SELECT E.NationalIDNumber, E.JobTitle, E.HireDate, P.FirstName, P.LastName, "
                + "DATEDIFF(YEAR, E.HireDate, GETDATE()) AS TenureYears "
                + "FROM HumanResources.Employee AS E "
                + "INNER JOIN Person.Person AS P ON E.BusinessEntityID = P.BusinessEntityID "
                + "WHERE DATEDIFF(YEAR, E.HireDate, GETDATE()) > (SELECT AVG(DATEDIFF(YEAR, HireDate, GETDATE())) "
                + "                                               FROM HumanResources.Employee) "
                + "AND E.NationalIDNumber IN (SELECT NationalIDNumber "
                + "                           FROM HumanResources.Employee "
                + "                           WHERE JobTitle LIKE N'%Manager%') "
                + "ORDER BY TenureYears DESC;";
    }
    private static String getMedium6() {
    	return "";
    }
    private static String getMedium7() {
        return "USE AdventureWorksDW2017; "
                + "WITH ProductSales2014 AS ( "
                + "    SELECT F.ProductKey, "
                + "        SUM(F.SalesAmount) AS TotalSalesAmount "
                + "    FROM dbo.FactInternetSales AS F "
                + "    WHERE YEAR(F.OrderDate) = 2014 "
                + "    GROUP BY F.ProductKey "
                + "), "
                + "CategoryAvgSales2014 AS ( "
                + "    SELECT P.ProductSubcategoryKey, "
                + "        AVG(PS.TotalSalesAmount) AS AvgSalesAmount "
                + "    FROM ProductSales2014 AS PS "
                + "    JOIN dbo.DimProduct AS P "
                + "        ON PS.ProductKey = P.ProductKey "
                + "    GROUP BY P.ProductSubcategoryKey "
                + ") "
                + "SELECT P.ProductKey, P.EnglishProductName, PS.TotalSalesAmount, "
                + "    C.AvgSalesAmount AS CategoryAvgSales, P.ProductSubcategoryKey "
                + "FROM ProductSales2014 AS PS "
                + "JOIN dbo.DimProduct AS P "
                + "    ON PS.ProductKey = P.ProductKey "
                + "JOIN CategoryAvgSales2014 AS C "
                + "    ON P.ProductSubcategoryKey = C.ProductSubcategoryKey "
                + "WHERE PS.TotalSalesAmount > C.AvgSalesAmount "
                + "ORDER BY P.ProductSubcategoryKey, PS.TotalSalesAmount DESC;";
    }
    private static String getMedium8() {
        return "USE AdventureWorksDW2017; "
                + "WITH CustomerSalesSummary AS ( "
                + "    SELECT FIS.CustomerKey, SUM(FIS.SalesAmount) AS TotalSalesAmount, "
                + "    COUNT(DISTINCT FIS.SalesOrderNumber) AS TotalOrderCount "
                + "    FROM dbo.FactInternetSales AS FIS "
                + "    JOIN dbo.DimDate AS DD ON FIS.OrderDateKey = DD.DateKey "
                + "    WHERE DD.CalendarYear = 2014 "
                + "    GROUP BY FIS.CustomerKey "
                + ") "
                + "SELECT TM.CustomerKey, C.FirstName, C.LastName, CSS.TotalSalesAmount, CSS.TotalOrderCount "
                + "FROM dbo.vTargetMail AS TM "
                + "JOIN CustomerSalesSummary CSS ON TM.CustomerKey = CSS.CustomerKey "
                + "JOIN dbo.DimCustomer AS C ON TM.CustomerKey = C.CustomerKey "
                + "ORDER BY CSS.TotalSalesAmount DESC;";
    }

    private static String getMedium9() {
        return "USE WideWorldImporters; "
                + "SELECT SI.StockItemID, SI.StockItemName, SG.StockGroupName, "
                + "    (SELECT AVG(TransactionAmount) "
                + "     FROM Purchasing.SupplierTransactions "
                + "     WHERE SupplierID = SI.SupplierID) AS AvgPurchasePrice, "
                + "    (SELECT COUNT(*) "
                + "     FROM Purchasing.PurchaseOrderLines "
                + "     WHERE StockItemID = SI.StockItemID) AS OrderCount "
                + "FROM Warehouse.StockItems SI "
                + "INNER JOIN Warehouse.StockItemStockGroups AS SISG "
                + "    ON SI.StockItemID = SISG.StockItemID "
                + "INNER JOIN Warehouse.StockGroups SG "
                + "    ON SISG.StockGroupID = SG.StockGroupID "
                + "ORDER BY SI.StockItemID;";
    }
    
    private static String getMedium10() {
        return "USE WideWorldImporters; "
                + "WITH RecentOrderDate AS ( "
                + "    SELECT CustomerID, MAX(OrderDate) AS LastOrderDate "
                + "    FROM Sales.Orders "
                + "    GROUP BY CustomerID "
                + ") "
                + "SELECT O.OrderID, O.OrderDate, C.CustomerName, "
                + "    (SELECT COUNT(*) "
                + "     FROM Sales.Orders AS O2 "
                + "     WHERE O2.CustomerID = C.CustomerID) AS TotalOrders, "
                + "     R.LastOrderDate "
                + "FROM Sales.Customers AS C "
                + "LEFT OUTER JOIN Sales.Orders AS O "
                + "    ON C.CustomerID = O.CustomerID "
                + "LEFT JOIN RecentOrderDate AS R "
                + "    ON C.CustomerID = R.CustomerID "
                + "ORDER BY C.CustomerID, O.OrderDate;";
    }

    private static String getMedium11() {
        return "USE WideWorldImporters; "
                + "WITH PurchaseSummary AS ( "
                + "    SELECT PO.SupplierID, SUM(POL.OrderedOuters * POL.ExpectedUnitPricePerOuter) AS TotalOrderValue, "
                + "        COUNT(DISTINCT POL.StockItemID) AS UniqueProducts, "
                + "        AVG(POL.OrderedOuters) AS AvgQuantityPerOrder "
                + "    FROM Purchasing.PurchaseOrders AS PO "
                + "    JOIN Purchasing.PurchaseOrderLines AS POL "
                + "        ON PO.PurchaseOrderID = POL.PurchaseOrderID "
                + "    WHERE YEAR(PO.ExpectedDeliveryDate) = 2016 "
                + "    GROUP BY PO.SupplierID "
                + ") "
                + "SELECT S.SupplierID, S.SupplierName, PS.TotalOrderValue, PS.UniqueProducts, PS.AvgQuantityPerOrder, "
                + "    (SELECT MAX(ExpectedDeliveryDate) "
                + "     FROM Purchasing.PurchaseOrders "
                + "     WHERE SupplierID = S.SupplierID "
                + "        AND YEAR(ExpectedDeliveryDate) = 2016) AS LastPurchaseDate "
                + "FROM Purchasing.Suppliers AS S "
                + "JOIN PurchaseSummary AS PS "
                + "    ON S.SupplierID = PS.SupplierID "
                + "ORDER BY PS.TotalOrderValue DESC;";
    }

    private static String getMedium12() {
        return "USE WideWorldImporters; "
                + "SELECT SI.StockItemID, SI.StockItemName, SG.StockGroupName, "
                + "    (SELECT AVG(TransactionAmount) "
                + "     FROM Purchasing.SupplierTransactions "
                + "     WHERE SupplierID = SI.SupplierID) AS AvgPurchasePrice, "
                + "    (SELECT COUNT(*) "
                + "     FROM Purchasing.PurchaseOrderLines "
                + "     WHERE StockItemID = SI.StockItemID) AS OrderCount "
                + "FROM Warehouse.StockItems SI "
                + "INNER JOIN Warehouse.StockItemStockGroups AS SISG "
                + "    ON SI.StockItemID = SISG.StockItemID "
                + "INNER JOIN Warehouse.StockGroups SG "
                + "    ON SISG.StockGroupID = SG.StockGroupID "
                + "ORDER BY SI.StockItemID;";
    }
    private static String getMedium13() {
    	  return "USE WideWorldImportersDW; "
    		         + "WITH CitySales AS ( "
    		         + "    SELECT DC.City, DD.[Calendar Year], SUM(FS.[Total Excluding Tax]) AS TotalSalesAmount, "
    		         + "    COUNT(FS.[Sale Key]) AS TotalOrders "
    		         + "    FROM Fact.Sale AS FS "
    		         + "    JOIN Dimension.Date AS DD "
    		         + "        ON FS.[Delivery Date Key] = DD.Date "
    		         + "    JOIN Dimension.City AS DC "
    		         + "        ON FS.[City Key] = DC.[City Key] "
    		         + "    WHERE DD.[Calendar Year] IN (2015,2016) "
    		         + "    GROUP BY DC.City, DD.[Calendar Year] "
    		         + ") "
    		         + "SELECT '2015' AS Year, City, TotalSalesAmount, TotalOrders "
    		         + "FROM CitySales "
    		         + "WHERE [Calendar Year] = 2015 "
    		         + "UNION ALL "
    		         + "SELECT '2016' AS Year, City, TotalSalesAmount, TotalOrders "
    		         + "FROM CitySales "
    		         + "WHERE [Calendar Year] = 2016 "
    		         + "ORDER BY City, Year;";
    }

    private static String getComplex1() {
        return "USE AdventureWorks2017; "
                + "WITH SalesSummary AS ( "
                + "    SELECT SP.BusinessEntityID, SP.TerritoryID, "
                + "        SUM(SOH.SubTotal) AS TotalSales, "
                + "        COUNT(SOH.SalesOrderID) AS OrderCount "
                + "    FROM Sales.SalesPerson AS SP "
                + "    JOIN Sales.SalesOrderHeader AS SOH ON SP.BusinessEntityID = SOH.SalesPersonID "
                + "    WHERE YEAR(SOH.OrderDate) = 2014 "
                + "    GROUP BY SP.BusinessEntityID, SP.TerritoryID "
                + "), "
                + "TopTerritories AS ( "
                + "    SELECT TOP 3 TerritoryID "
                + "    FROM SalesSummary "
                + "    GROUP BY TerritoryID "
                + "    ORDER BY SUM(TotalSales) DESC "
                + ") "
                + "SELECT SP.BusinessEntityID, SP.TerritoryID, SS.TotalSales, SS.OrderCount "
                + "FROM SalesSummary AS SS "
                + "JOIN Sales.SalesPerson AS SP ON SS.BusinessEntityID = SP.BusinessEntityID "
                + "WHERE SP.TerritoryID IN (SELECT TerritoryID FROM TopTerritories) "
                + "UNION ALL "
                + "SELECT SP.BusinessEntityID, SP.TerritoryID, SS.TotalSales, SS.OrderCount "
                + "FROM SalesSummary SS "
                + "JOIN Sales.SalesPerson AS SP ON SS.BusinessEntityID = SP.BusinessEntityID "
                + "WHERE SP.TerritoryID NOT IN (SELECT TerritoryID FROM TopTerritories) "
                + "ORDER BY SP.TerritoryID, TotalSales DESC;";
    }

    private static String getComplex2() {
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



    private static String getComplex3() {
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
    private static String getComplex4() {
        return "USE AdventureWorksDW2017; "
                + "GO "
                + "CREATE OR ALTER VIEW CustomerYearlySales AS "
                + "SELECT FIS.CustomerKey, YEAR(D.FullDateAlternateKey) AS SalesYear, "
                + "       SUM(FIS.SalesAmount) AS TotalSalesAmount "
                + "FROM dbo.FactInternetSales AS FIS "
                + "JOIN dbo.DimDate AS D ON FIS.OrderDateKey = D.DateKey "
                + "GROUP BY FIS.CustomerKey, YEAR(D.FullDateAlternateKey); "
                + "GO "
                + "SELECT TOP 20 DC.CustomerKey, DC.FirstName, DC.LastName, CYS.SalesYear, "
                + "       CYS.TotalSalesAmount, "
                + "       (SELECT MAX(SalesYear) "
                + "        FROM CustomerYearlySales "
                + "        WHERE CustomerKey = DC.CustomerKey) AS MaxSalesYear, "
                + "       (SELECT COUNT(*) "
                + "        FROM dbo.FactInternetSales AS FIS "
                + "        JOIN dbo.DimDate AS DD ON FIS.OrderDateKey = DD.DateKey "
                + "        WHERE FIS.CustomerKey = DC.CustomerKey AND YEAR(DD.FullDateAlternateKey) = CYS.SalesYear) AS OrderCountInSalesYear "
                + "FROM dbo.DimCustomer AS DC "
                + "JOIN CustomerYearlySales CYS ON DC.CustomerKey = CYS.CustomerKey "
                + "WHERE CYS.SalesYear = (SELECT MAX(SalesYear) "
                + "                       FROM CustomerYearlySales "
                + "                       WHERE CustomerKey = DC.CustomerKey) "
                + "ORDER BY CYS.TotalSalesAmount DESC;";
    }
    private static String getComplex5() {
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
    private static String getComplex6() {
        return "USE WideWorldImporters; "
                + "WITH CityTotal AS ( "
                + "    SELECT CityName, 'Customer' AS EntityType, COUNT(*) AS EntityCount "
                + "    FROM Website.Customers "
                + "    GROUP BY CityName "
                + "    UNION ALL "
                + "    SELECT CityName, 'Supplier' AS EntityType, COUNT(*) AS EntityCount "
                + "    FROM Website.Suppliers "
                + "    GROUP BY CityName "
                + ") "
                + "SELECT CityName, SUM(CASE "
                + "                        WHEN EntityType = 'Customer' THEN EntityCount "
                + "                        ELSE 0 "
                + "                    END) AS TotalCustomers, "
                + "                 SUM(CASE "
                + "                        WHEN EntityType = 'Supplier' THEN EntityCount "
                + "                        ELSE 0 "
                + "                    END) AS TotalSuppliers, "
                + "                 SUM(EntityCount) AS CombinedCount, "
                + "                 RANK() OVER (ORDER BY SUM(EntityCount) DESC) AS CityRank "
                + "FROM CityTotal "
                + "GROUP BY CityName "
                + "ORDER BY CombinedCount DESC;";
    }
    
    private static String getComplex7() {
        return "USE WideWorldImporters; "
                + "WITH CustomerSales AS ( "
                + "    SELECT O.CustomerID, SUM(OL.Quantity * OL.UnitPrice) AS TotalSalesAmount, "
                + "    MAX(O.OrderDate) AS LastOrderDate "
                + "    FROM Sales.Orders AS O "
                + "    JOIN Sales.OrderLines AS OL "
                + "        ON O.OrderID = OL.OrderID "
                + "    WHERE YEAR(O.OrderDate) = 2016 "
                + "    GROUP BY O.CustomerID "
                + ") "
                + "SELECT C.CustomerID, C.CustomerName, CS.TotalSalesAmount, CS.LastOrderDate, "
                + "    (SELECT AVG(Quantity) "
                + "     FROM Sales.OrderLines AS OL "
                + "     JOIN Sales.Orders AS O "
                + "         ON OL.OrderID = O.OrderID "
                + "     WHERE O.CustomerID = C.CustomerID "
                + "     AND YEAR(O.OrderDate) = 2016) AS AvgQuantityPerOrder "
                + "FROM Sales.Customers AS C "
                + "JOIN CustomerSales AS CS "
                + "    ON C.CustomerID = CS.CustomerID "
                + "ORDER BY CS.TotalSalesAmount DESC;";
    }

}