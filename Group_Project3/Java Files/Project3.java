package project3;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Project3 {

    private static JFrame frame;
    private static JPanel buttonPanel;
    private static String CONNECTION_URL;
    private static JComboBox<String> queryComboBox;
    private static JTable resultTable;
    
    private static String serverName = ""; //localhost
    private static String port = "";	//13001
    private static String username = "";	//sa
    private static String password = "";	//EZ@15309699

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
			return String.format("jdbc:sqlserver://%s:%s;databaseName=QueensClassSchedule;user=%s;password=%s", serverName, port,
					username, password);
		} else {
			return null; // or handle cancel option appropriately
		}
	}
    public static void initializeGUI() {
        frame = new JFrame("Group 3, Class Time 9:15am: Queens Class Schedule Project Execution");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLayout(new FlowLayout());

        setupButtonPanel();

        JLabel titleLabel = new JLabel("PROJECT 3 QUEENS COLLEGE SEMESTER COURSE DATABASE", SwingConstants.CENTER);
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
        loadSchemaButton.addActionListener(e -> executeStoredProcedure("{call Project3.LoadStarSchemaData}", "Loading Star Schema..."));
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
        		"Instructors who are teaching in classes in multiple departments.", //CQ1
        		"Total instructors in each department.", //CQ2
        		"Classes taught by course and total enrollment, total class limit and the percentage of enrollment.", //CQ3
        		"Determine the number of in-person, hybrid, and web-enhanced each distinct department offers.",
        		"Find all the Courses that provide 4 credits.",
        		"Determine the number of rooms used in each building location.",
        		"Determine if any rooms are used for multiple classes.",
        		"Find all the Biochemistry Labs available for this semester.",
        		"Write a query to determine the total number of courses per department.",
        		"Determine the coursenames that have less than 20 enrolled students.",
        		"Determine all the classes in the Science buiding \"SB\", with their time.",
        		"Determine all the classes held on Saturday and Sunday.",
        		"Determine the classes that are hybrid and web-enhanced with a limit bigger than 50."
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
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q1</div></html>");
                break;
            case 1: //DimGender
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q2</div></html>");
                break;
            case 2: //DimMaritalStatus
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q3</div></html>");
                break;
            case 3: //DimOccupation
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q4</div></html>");
                break;
            case 4:	//DimOrderDate
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q5</div></html>");
                break;
            case 5: //DimProduct
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q6</div></html>");
                break;
            case 6: //DimProductCategory
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q7</div></html>");
                break;
            case 7:	//DimProductSubCategory
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q8</div></html>");
                break;
            case 8:	//DimTerritory
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q9</div></html>");
                break;
            case 9:	//SalesManagers
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q10</div></html>");
                break;
            case 10: //Fact.Data
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q11</div></html>");
                break;
            case 11: //UserAuthorization
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q12</div></html>");
                break;
            case 12: //WorkflowSteps
                titleLabel = new JLabel("<html><div style='width: 700px; text-align: center;'>Q13</div></html>");
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
            case 0: //CQ1
                columnNames = new String[]{"First Name","Last Name","NumberOfDepartments"};
                break;
            case 1: //CQ2
                columnNames = new String[]{"DepartmentName","NumberOfInstructors"};
                break;
            case 2: //CQ3
                columnNames = new String[]{"Course", "NumberOfClasses", "TotalEnrollment", "TotalClassLimit", "PercentageOfEnrollment"};
                break;
            case 3: //CQ4
                columnNames = new String[]{"DepartmentName", "InPersonClasses","HybridClasses", "WebEnhancedClasses"};
                break;
            case 4: //CQ5
                columnNames = new String[]{"Department", "CourseName"};
                break;
            case 5: //CQ6
                columnNames = new String[]{"Building", "NumberOfRooms"};
                break;
            case 6: //CQ7
                columnNames = new String[]{"BuildingID", "BuildingName", "RoomID", "RoomNumber", "NumberOfClasses"};
                break;
            case 7: //CQ8
                columnNames = new String[]{"CourseName", "Instructor Name",	"Enrollment", "Number",	"Enrollment Limit"};
                break;
            case 8: //CQ9
                columnNames = new String[]{"CourseAbbreviation", "NumberOfCourses"};
                break;
            case 9: //CQ10
                columnNames = new String[]{"CourseName", "Enrolled"};
                break;
            case 10: //CQ11
                columnNames = new String[]{"CourseAbbreviation", "CourseNumber", "CourseName", "RoomNumber", "Time", "Day"};
                break;
            case 11: //CQ12
                columnNames = new String[]{"CourseAbbreviation", "CourseName", "Instructor", "BuildingName", "RoomNumber", "Day", "Time"};
                break;
            case 12: //CQ13
                columnNames = new String[]{"CourseAbbreviation", "CourseName", "Instructor", "BuildingName", "RoomNumber", "Limit"};
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
                return getCQ1();
            case 1:
                return getCQ2();
            case 2:
                return getCQ3();
            case 3:
                return getCQ4();
            case 4:
                return getCQ5();
            case 5:
                return getCQ6();
            case 6:
                return getCQ7();
            case 7:
                return getCQ8();
            case 8:
                return getCQ9();
            case 9:
                return getCQ10();
            case 10:
                return getCQ11();
            case 11:
                return getCQ12();
            case 12:
                return getCQ13();
            default:
                return "";
        }
    }
    private static String getCQ1() {
		return "USE [QueensClassSchedule]; "
				+ "SELECT \r\n"
				+ "    FirstName,\r\n"
				+ "    LastName,\r\n"
				+ "    COUNT(DISTINCT DepartmentName) AS NumberOfDepartments\r\n"
				+ "FROM \r\n"
				+ "    [Departmental].[Instructor]\r\n"
				+ "GROUP BY \r\n"
				+ "    FirstName,\r\n"
				+ "    LastName\r\n"
				+ "HAVING \r\n"
				+ "    COUNT(DISTINCT DepartmentName) > 1\r\n"
				+ "ORDER BY\r\n"
				+ "    LastName ASC,\r\n"
				+ "    FirstName ASC;";
	}
    
	private static String getCQ2() {
		return "SELECT \r\n"
				+ "    DepartmentName,\r\n"
				+ "    COUNT(DISTINCT FirstName + ' ' + LastName) AS NumberOfInstructors\r\n"
				+ "FROM \r\n"
				+ "    [Departmental].[Instructor]\r\n"
				+ "GROUP BY \r\n"
				+ "    DepartmentName\r\n"
				+ "ORDER BY \r\n"
				+ "    DepartmentName;";
	}
	
	private static String getCQ3() {
		return "SELECT \r\n"
				+ "    CourseAbbreviation,\r\n"
				+ "    COUNT(ClassID) AS NumberOfClasses,\r\n"
				+ "    SUM(CAST(Enrolled AS INT)) AS TotalEnrollment,\r\n"
				+ "    SUM(CAST(Limit AS INT)) AS TotalClassLimit,\r\n"
				+ "    CASE \r\n"
				+ "        WHEN SUM(CAST(Limit AS INT)) > 0 \r\n"
				+ "        THEN CONVERT(DECIMAL(5, 2), (SUM(CAST(Enrolled AS INT)) * 100.0) / SUM(CAST(Limit AS INT)))\r\n"
				+ "        ELSE 0 \r\n"
				+ "    END AS PercentageOfEnrollment\r\n"
				+ "FROM \r\n"
				+ "    [CollegeClasses].[Class]\r\n"
				+ "GROUP BY \r\n"
				+ "    CourseAbbreviation\r\n"
				+ "ORDER BY \r\n"
				+ "    CourseAbbreviation;";
	}

	private static String getCQ4() {
		return "  SELECT \r\n"
				+ "    d.DepartmentName,\r\n"
				+ "    SUM(CASE WHEN c.ModeOfInstruction = 'In-Person' THEN 1 ELSE 0 END) AS InPersonClasses,\r\n"
				+ "    SUM(CASE WHEN c.ModeOfInstruction = 'Hybrid' THEN 1 ELSE 0 END) AS HybridClasses,\r\n"
				+ "    SUM(CASE WHEN c.ModeOfInstruction = 'Web-Enhanced' THEN 1 ELSE 0 END) AS WebEnhancedClasses\r\n"
				+ "FROM \r\n"
				+ "    [Departmental].[Department] d\r\n"
				+ "JOIN \r\n"
				+ "    [CollegeClasses].[Class] c ON d.DepartmentName = c.CourseAbbreviation -- Assuming CourseID in Class table maps to DepartmentID in Department table\r\n"
				+ "GROUP BY \r\n"
				+ "    d.DepartmentName\r\n"
				+ "ORDER BY \r\n"
				+ "    d.DepartmentName;";
	}

	private static String getCQ5() {
		return "SELECT \r\n"
				+ "    CourseAbbreviation,\r\n"
				+ "    CourseName\r\n"
				+ "FROM \r\n"
				+ "    [CollegeClasses].[Course]\r\n"
				+ "WHERE \r\n"
				+ "    CourseCredit = 4\r\n"
				+ "ORDER BY \r\n"
				+ "    CourseAbbreviation, \r\n"
				+ "    CourseName;";
	}

	private static String getCQ6() {
		return "  SELECT \r\n"
				+ "    bl.BuildingName,\r\n"
				+ "    COUNT(DISTINCT rl.RoomID) AS NumberOfRooms\r\n"
				+ "FROM \r\n"
				+ "    [Location].[RoomLocation] rl\r\n"
				+ "JOIN \r\n"
				+ "    [Location].[BuildingLocation] bl ON rl.BuildingCode = bl.BuildingID\r\n"
				+ "GROUP BY \r\n"
				+ "    bl.BuildingName\r\n"
				+ "ORDER BY \r\n"
				+ "    bl.BuildingName;";
	}

	private static String getCQ7() {
		return "SELECT \r\n"
				+ "    BuildingID,\r\n"
				+ "    BuildingName,\r\n"
				+ "    RoomID,\r\n"
				+ "    RoomNumber,\r\n"
				+ "    COUNT(ClassID) AS NumberOfClasses\r\n"
				+ "FROM \r\n"
				+ "    [CollegeClasses].[Class]\r\n"
				+ "GROUP BY \r\n"
				+ "    BuildingID,\r\n"
				+ "    BuildingName,\r\n"
				+ "    RoomID,\r\n"
				+ "    RoomNumber\r\n"
				+ "HAVING \r\n"
				+ "    COUNT(ClassID) > 1\r\n"
				+ "ORDER BY \r\n"
				+ "    BuildingName,\r\n"
				+ "    RoomNumber;";
	}

	private static String getCQ8() {
		return "SELECT c.CourseName,\r\n"
				+ "       c.Instructor AS [Instructor Name],\r\n"
				+ "       Enrolled AS [Enrollment Number],\r\n"
				+ "       Limit AS [Enrollment Limit]\r\n"
				+ "FROM CollegeClasses.Course as a\r\n"
				+ "left outer join CollegeClasses.Class as c\r\n"
				+ "on c.CourseID = a.CourseID\r\n"
				+ "WHERE c.CourseName = 'Biochemistry Lab'\r\n"
				+ "ORDER BY c.Enrolled;";
	}

	private static String getCQ9() {
		return "SELECT \r\n"
				+ "    CourseAbbreviation,\r\n"
				+ "    COUNT(*) AS NumberOfCourses\r\n"
				+ "FROM \r\n"
				+ "    [CollegeClasses].[Course]\r\n"
				+ "GROUP BY \r\n"
				+ "    CourseAbbreviation\r\n"
				+ "ORDER BY \r\n"
				+ "    CourseAbbreviation;\r\n"
				+ "";
	}

	private static String getCQ10() {
		return "SELECT \r\n"
				+ "    CourseName,\r\n"
				+ "    Enrolled\r\n"
				+ "FROM \r\n"
				+ "    [CollegeClasses].[Class]\r\n"
				+ "WHERE \r\n"
				+ "    CAST(Enrolled AS INT) < 20\r\n"
				+ "ORDER BY \r\n"
				+ "    CourseName;";
	}

	private static String getCQ11() {
		return "SELECT \r\n"
				+ "    CourseAbbreviation,\r\n"
				+ "    CourseNumber,\r\n"
				+ "    CourseName,\r\n"
				+ "    RoomNumber,\r\n"
				+ "    [Time],\r\n"
				+ "    [Day]\r\n"
				+ "FROM \r\n"
				+ "    [CollegeClasses].[Class]\r\n"
				+ "WHERE \r\n"
				+ "    BuildingName = 'SB'\r\n"
				+ "ORDER BY \r\n"
				+ "    [Time];";
	}

	private static String getCQ12() {
    	return "SELECT \r\n"
    			+ "    CourseAbbreviation,\r\n"
    			+ "    CourseName,\r\n"
    			+ "    Instructor,\r\n"
    			+ "    BuildingName,\r\n"
    			+ "    RoomNumber,\r\n"
    			+ "	[Day],\r\n"
    			+ "    [Time]\r\n"
    			+ "FROM \r\n"
    			+ "    [CollegeClasses].[Class]\r\n"
    			+ "WHERE \r\n"
    			+ "    [Day] IN ('S', 'Su')\r\n"
    			+ "ORDER BY \r\n"
    			+ "    [Day], [Time];";
    }
    private static String getCQ13() {
        return "SELECT \r\n"
        		+ "    CourseAbbreviation,\r\n"
        		+ "    CourseName,\r\n"
        		+ "    Instructor,\r\n"
        		+ "    BuildingName,\r\n"
        		+ "    RoomNumber,\r\n"
        		+ "    [Limit]\r\n"
        		+ "FROM \r\n"
        		+ "    [CollegeClasses].[Class]\r\n"
        		+ "WHERE \r\n"
        		+ "    ModeOfInstruction IN ('Hybrid', 'Web-Enhanced') AND \r\n"
        		+ "    CAST([Limit] AS INT) > 50\r\n"
        		+ "ORDER BY \r\n"
        		+ "    CourseAbbreviation, \r\n"
        		+ "    CourseNumber;";
    }
}
