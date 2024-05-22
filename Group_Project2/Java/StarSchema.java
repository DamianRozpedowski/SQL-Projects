package starSchema;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StarSchema {

    private static JFrame frame;
    private static JPanel buttonPanel;
    private static String CONNECTION_URL;
    private static JComboBox<String> queryComboBox;
    private static JTable resultTable;
    
    private static String serverName = "";
    private static String port = "";
    private static String username = "";
    private static String password = "";

    public static void main(String[] args) {
//    	initializeGUI();
        SwingUtilities.invokeLater(() -> {
            CONNECTION_URL = showLoginDialog();
            if (CONNECTION_URL != null) {
                initializeGUI();
            } else {
                System.exit(0);
            }
        });
    }
    
	public static String showLoginDialog() {
		JTextField serverField = new JTextField(10);
		JTextField portField = new JTextField(10);
		JTextField usernameField = new JTextField(10);
		JPasswordField passwordField = new JPasswordField(15);

		JPanel loginPanel = new JPanel(new GridLayout(8, 4));
		loginPanel.add(new JLabel("Server Name:"));
		loginPanel.add(serverField);
		loginPanel.add(new JLabel("Port:"));
		loginPanel.add(portField);
		loginPanel.add(new JLabel("Username:"));
		loginPanel.add(usernameField);
		loginPanel.add(new JLabel("Password:"));
		loginPanel.add(passwordField);

		int result = JOptionPane.showConfirmDialog(null, loginPanel, "Login to Group Database",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			serverName = serverField.getText();
			port = portField.getText();
			username = usernameField.getText();
			password = new String(passwordField.getPassword());
			return String.format("jdbc:sqlserver://%s:%s;databaseName=BIClass;user=%s;password=%s", serverName, port,
					username, password);
		} else {
			return null; // or handle cancel option appropriately
		}
	}
//    private static final String connectionUrl = String.format("jdbc:sqlserver://%s:%s;databaseName=BIClass;user=%s;password=%s", serverName, port,
//			username, password
//    );
    public static void initializeGUI() {
        frame = new JFrame("Group 3, Class Time 9:15am: Star Schema Project Execution");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLayout(new FlowLayout());

        setupButtonPanel();

        JLabel titleLabel = new JLabel("PROJECT 2 RECREATE THE BICLASS DATABASE STAR SCHEMA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 16));
        
        // Create a panel that holds the title and buttons
        JPanel titleAndButtonPanel = new JPanel(new BorderLayout());
        titleAndButtonPanel.add(titleLabel, BorderLayout.NORTH);
        titleAndButtonPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel queryPanel = setupQueryDisplay();
        JPanel groupTablePanel = setupGroupTablePanel();
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(titleAndButtonPanel, BorderLayout.NORTH);
        mainPanel.add(queryPanel, BorderLayout.CENTER);
        mainPanel.add(groupTablePanel, BorderLayout.SOUTH);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static JPanel setupGroupTablePanel() {
        String[] columnNames = {"Group Member Last Name", "Group Member First Name", "Authorization Number"};
        Object[][] data = {
            {"Rozpedowski", "Damian", 2},
            {"Sanchez", "Essmer", 3},
            {"Kurian", "Hannah", 4},
            {"Hosna", "Hasnatul", 5},
            {"GROUP", "THREE", 1}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable groupTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(groupTable);
        groupTable.setFillsViewportHeight(true);

        JPanel groupTablePanel = new JPanel(new BorderLayout());
        groupTablePanel.add(new JLabel("Group Members", SwingConstants.CENTER), BorderLayout.NORTH);
        groupTablePanel.add(scrollPane, BorderLayout.CENTER);
        return groupTablePanel;
    }

    private static void setupButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loadSchemaButton = new JButton("Load Star Schema");
        loadSchemaButton.addActionListener(e -> executeStoredProcedure("{call Project2.LoadStarSchemaData}", "Loading Star Schema..."));
        JButton showWorkflowButton = new JButton("Show Workflow Steps");
        showWorkflowButton.addActionListener(e -> executeStoredProcedure("{call Process.usp_ShowWorkflowSteps}", "Showing Workflow Steps..."));
        buttonPanel.add(loadSchemaButton);
        buttonPanel.add(showWorkflowButton);
    }

    private static JPanel setupQueryDisplay() {
        JPanel queryPanel = new JPanel();
        queryPanel.setLayout(new BorderLayout());

        String[] queries = getQueries(); // This should return the string array of queries.
        queryComboBox = new JComboBox<>(queries);
        
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(JLabel.CENTER); // Center-align the text
        queryComboBox.setRenderer(listRenderer);
        
        queryComboBox.setMaximumRowCount(queries.length);        
        
        queryPanel.add(queryComboBox, BorderLayout.NORTH);

        JButton executeButton = new JButton("Execute Query");
        executeButton.addActionListener(e -> {
            int selectedIndex = queryComboBox.getSelectedIndex(); // Get the selected index
            executeQueryAndDisplayGUI(selectedIndex); // Pass the selected index to the method
        });
        queryPanel.add(executeButton, BorderLayout.SOUTH);

        resultTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultTable);
        queryPanel.add(scrollPane, BorderLayout.CENTER);

        return queryPanel;
    }

    private static String[] getQueries() {
        return new String[] {
        		"DimCustomer",
        		"DimGender",
        		"DimMaritalStatus",
        		"DimOccupation",
        		"DimOrderDate",
        		"DimProduct",
        		"DimProductCategory",
        		"DimProductSubCategory",
        		"DimTerritory",
        		"SalesManagers",
        		"Fact.Data",
        		"UserAuthorization",
        		"WorkflowSteps"
            // Add more queries as required
        };
    }

	private static void executeStoredProcedure(String storedProcedure, String statusMessage) {
		JFrame procedureFrame = new JFrame(statusMessage);
		procedureFrame.setLayout(new BorderLayout());

		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		procedureFrame.add(scrollPane, BorderLayout.CENTER);

		try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
				CallableStatement stmt = conn.prepareCall(storedProcedure)) {

			boolean hasResults = stmt.execute();
			do {
				if (hasResults) {
					try (ResultSet rs = stmt.getResultSet()) {
						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						String[] columnNames = new String[columnCount];
						for (int i = 1; i <= columnCount; i++) {
							columnNames[i - 1] = metaData.getColumnLabel(i);
						}
						model.setColumnIdentifiers(columnNames);

						while (rs.next()) {
							Object[] row = new Object[columnCount];
							for (int i = 0; i < columnCount; i++) {
								row[i] = rs.getObject(i + 1);
							}
							model.addRow(row);
						}
					}
				}
				hasResults = stmt.getMoreResults();
			} while (hasResults || stmt.getUpdateCount() != -1);

			procedureFrame.pack();
			procedureFrame.setLocationRelativeTo(null);
			procedureFrame.setVisible(true);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage(), "Database Error",
					JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
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
            case 0: //DimCustomer
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>DimCustomer</div></html>");
                break;
            case 1: //DimGender
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>DimGender</div></html>");
                break;
            case 2: //DimMaritalStatus
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>DimMaritalStatus</div></html>");
                break;
            case 3: //DimOccupation
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>DimOccupation</div></html>");
                break;
            case 4:	//DimOrderDate
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>DimOrderDate</div></html>");
                break;
            case 5: //DimProduct
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>DimProduct</div></html>");
                break;
            case 6: //DimProductCategory
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>DimProductCategory</div></html>");
                break;
            case 7:	//DimProductSubCategory
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>DimProductSubCategory</div></html>");
                break;
            case 8:	//DimTerritory
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>DimTerritory</div></html>");
                break;
            case 9:	//SalesManagers
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>SalesManagers</div></html>");
                break;
            case 10: //Fact.Data
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Fact Data</div></html>");
                break;
            case 11: //UserAuthorization
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>UserAuthorization</div></html>");
                break;
            case 12: //WorkflowSteps
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>WorkflowSteps</div></html>");
                break;
            default:
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>DEFAULT</div></html>");
                break;
        }
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Adjust font as needed
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
//        titleLabel.setPreferredSize(new Dimension(800, 80)); // Set preferred size for the label with increased height
        panel.add(titleLabel, BorderLayout.NORTH);

        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.pack();
//        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Set column names based on the selected query
        String[] columnNames;
        switch (queryIndex) {
            case 0: //DimCustomer
                columnNames = new String[]{"CustomerKey","CustomerName","UserAuthorizationKey","DateAdded","DateOfLastUpdate"};
                break;
            case 1: //DimGender
                columnNames = new String[]{"Gender","GenderDescription","UserAuthorizationKey","DateAdded","DateOfLastUpdate"};
                break;
            case 2: //DimMaritalStatus
                columnNames = new String[]{"MaritalStatus","MaritalStatusDescription","UserAuthorizationKey","DateAdded","DateOfLastUpdate"};
                break;
            case 3: //DimOccupation
                columnNames = new String[]{"OccupationKey","Occupation","UserAuthorizationKey","DateAdded","DateOfLastUpdate"};
                break;
            case 4: //DimOrderDate
                columnNames = new String[]{"OrderDate","MonthName","MonthNumber","Year","UserAuthorizationKey","DateAdded","DateOfLastUpdate"};
                break;
            case 5: //DimProduct
                columnNames = new String[]{"ProductKey","ProductSubcategoryKey","ProductCategory","ProductSubcategory","ProductCode","ProductName","Color","ModelName","UserAuthorizationKey","DateAdded","DateOfLastUpdate"};
                break;
            case 6: //DimProductCategory
                columnNames = new String[]{"ProductCategoryKey", "ProductCategory", "UserAuthorizationKey", "DateAdded","DateOfLastUpdate"};
                break;
            case 7: //DimProductSubcategory
                columnNames = new String[]{"ProductSubcategoryKey","ProductCategoryKey","ProductSubcategory","UserAuthorizationKey","DateAdded","DateOfLastUpdate"};
                break;
            case 8: //DimTerritory
                columnNames = new String[]{"TerritoryKey","TerritoryGroup","TerritoryCountry","TerritoryRegion","UserAuthorizationKey","DateAdded","DateOfLastUpdate"};
                break;
            case 9: //SalesManagers
                columnNames = new String[]{"SalesManagerKey","Category","SalesManager","Office","UserAuthorizationKey","DateAdded","DateOfLastUpdate"};
                break;
            case 10: //Fact.Data
                columnNames = new String[]{"SalesKey","SalesManagerKey","OccupationKey","TerritoryKey","ProductKey","CustomerKey","ProductCategory","SalesManager","ProductSubcategory","ProductCode","ProductName","Color","ModelName","OrderQuantity","UnitPrice","ProductStandardCost","SalesAmount","OrderDate","MonthName","MonthNumber","Year","CustomerName","MaritalStatus","Gender","Education","Occupation","TerritoryRegion","TerritoryCountry","TerritoryGroup","UserAuthorizationKey","DateAdded","DateOfLastUpdate"};
                break;
            case 11: //UserAthorization
                columnNames = new String[]{"UserAuthorizationKey","ClassTime","IndividualProject","GroupMemberLastName","GroupMemberFirstName","GroupName","DateAdded"};
                break;
            case 12: //WorkflowSteps
                columnNames = new String[]{"WorkFlowStepKey","WorkFlowStepDescription","WorkFlowStepTableRowCount","StartingDateTime","EndingDateTime","Class Time","UserAuthorizationKey"};
                break;
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

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL);
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
                return getDimCustomer();
            case 1:
                return getDimGender();
            case 2:
                return getDimMaritalStatus();
            case 3:
                return getDimOccupation();
            case 4:
                return getDimOrderDate();
            case 5:
                return getDimProduct();
            case 6:
                return getDimProductCategory();
            case 7:
                return getDimProductSubcategory();
            case 8:
                return getDimTerritory();
            case 9:
                return getSalesManagers();
            case 10:
                return getFactData();
            case 11:
                return getUserAuthorization();
            case 12:
                return getWorkFlowSteps();
            default:
                return "";
        }
    }
    private static String getWorkFlowSteps() {
		return "USE BIClass; "
				+ "SELECT *"
				+ "FROM Process.WorkflowSteps;";
	}

	private static String getUserAuthorization() {
		return "USE BIClass; "
				+ "SELECT *"
				+ "FROM DbSecurity.UserAuthorization;";
	}

	private static String getFactData() {
		return "USE BIClass; "
				+ "SELECT *"
				+ "FROM [CH01-01-Fact].Data;";
	}

	private static String getSalesManagers() {
		return "USE BIClass; "
				+ "SELECT *"
				+ "FROM [CH01-01-Dimension].SalesManagers;";
	}

	private static String getDimTerritory() {
		return "USE BIClass; "
				+ "SELECT *"
				+ "FROM [CH01-01-Dimension].DimTerritory;";
	}

	private static String getDimProductSubcategory() {
		return "USE BIClass; "
				+ "SELECT *"
				+ "FROM [CH01-01-Dimension].DimProductSubcategory;";
	}

	private static String getDimOrderDate() {
		return "USE BIClass; "
				+ "SELECT *"
				+ "FROM [CH01-01-Dimension].DimOrderDate;";
	}

	private static String getDimOccupation() {
		return "USE BIClass; "
				+ "SELECT *"
				+ "FROM [CH01-01-Dimension].DimOccupation;";
	}

	private static String getDimMaritalStatus() {
		return "USE BIClass; "
				+ "SELECT *"
				+ "FROM [CH01-01-Dimension].DimMaritalStatus;";
	}

	private static String getDimGender() {
		return "USE BIClass; "
				+ "SELECT *"
				+ "FROM [CH01-01-Dimension].DimGender;";
	}

	private static String getDimCustomer() {
		return "USE BIClass; "
				+ "SELECT *"
				+ "FROM [CH01-01-Dimension].DimCustomer;";
	}

	private static String getDimProduct() {
    	return "USE BIClass; "
    			+ "SELECT *"
    			+ "FROM [CH01-01-Dimension].DimProduct;";
    }
    private static String getDimProductCategory() {
        return "USE BIClass; "
                + "SELECT *"
                + "FROM [CH01-01-Dimension].DimProductCategory;";
    }
}
