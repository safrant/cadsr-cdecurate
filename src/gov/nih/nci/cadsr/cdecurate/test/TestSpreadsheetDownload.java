// Copyright ScenPro, Inc 2007

// $Header: /cvsshare/content/cvsroot/cdecurate/src/gov/nih/nci/cadsr/cdecurate/test/testdec.java,v 1.11 2008-03-13 17:57:59 chickerura Exp $
// $Name: not supported by cvs2svn $

package gov.nih.nci.cadsr.cdecurate.test;

import gov.nih.nci.cadsr.cdecurate.util.AdministeredItemUtil;
import gov.nih.nci.cadsr.common.TestUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Struct;
//import oracle.sql.STRUCT;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//import oracle.sql.STRUCT;

import oracle.sql.Datum;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author shegde
 * 
 */
public class TestSpreadsheetDownload {
	public static final Logger logger = Logger
			.getLogger(TestSpreadsheetDownload.class.getName());

	protected static Connection m_conn = null;
	private static final int GRID_MAX_DISPLAY = 10;
	FileOutputStream fileOutputStream = null;
	ArrayList<String> columnHeaders = new ArrayList<String>();
	ArrayList<String> columnTypes = new ArrayList<String>();
	HashMap<String, ArrayList<String[]>> typeMap = new HashMap<String, ArrayList<String[]>>();
	HashMap<String, String> arrayColumnTypes = new HashMap<String, String>();
	ArrayList<String> allExpandedColumnHeaders = new ArrayList<String>();
	ArrayList<HashMap<String, List<String[]>>> arrayData = new ArrayList<HashMap<String, List<String[]>>>();

	//=======================================================================
	
	public static void main(String[] args) {
		try {
			//DO NOT HARD CODE the user/password and check in SVN/Git please!
			setConn(TestUtil.getConnection(args[0], args[1]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		TestSpreadsheetDownload download = new TestSpreadsheetDownload();
		download.setColHeadersAndTypes("CDE");
		ArrayList<String[]> downloadRows = download.getRecords(false, false);
		download.createDownloadColumns(downloadRows);
	}

	//=======================================================================
	
	public void get_m_conn() {
		// get the connection
		if (m_conn == null) {
			m_conn = connectDB();
			setConn(m_conn);
		}
	}

	//=======================================================================
	
	/**
	 * @param ub_
	 * @return Connection
	 */
	public Connection connectDB() {
		Connection SBRDb_conn = null;
		try {
			try {
				SBRDb_conn = this.getConnFromDS();
			} catch (Exception e) {
				logger.error("Servlet error: no pool connection.", e);
			}
		} catch (Exception e) {
			logger.error("Servlet connectDB : " + e.toString(), e);
		}
		return SBRDb_conn;
	}

	//=======================================================================
	
	/**
	 * Start in the /conf/template.cdecurate-oracle-ds.xml file. Notice the
	 * <jndi-name>. This name is used by JBoss to create and identify the
	 * connection pool. We copied this name to the /conf/template.web.xml file
	 * in the <param-value> element. The <param-name> for this initialization
	 * value appears in the code NCICurationServlet.initOracleConnect() method.
	 * The data source pool name is then saved in a class variable
	 * "_dataSourceName". * The variable is used by the
	 * CurationServlet.getConnFromDS() method which is used by the
	 * CurationServlet.connectDB() method.
	 * 
	 * @return
	 */
	public Connection getConnFromDS() {
		// Use tool database pool.
		Context envContext = null;
		DataSource ds = null;
		String user_;
		String pswd_;
		try {
			envContext = new InitialContext();
			ds = (DataSource) envContext.lookup("jdbc/CDECurateDS");
			user_ = "cdebrowser";
			pswd_ = "cdeuser";
		} catch (Exception e) {
			String stErr = "Error creating database pool[" + e.getMessage()
					+ "].";
			logger.fatal(stErr, e);
			return null;
		}
		// Open connection
		Connection con = null;
		try {
			con = ds.getConnection(user_, pswd_);
		} catch (Exception e) {
			logger.fatal("Could not open database connection.", e);
			return null;
		}
		return con;
	}

	//=======================================================================
	
	/**
	 * @return the m_conn
	 */
	public Connection getConn() {
		return m_conn;

	}

	//=======================================================================
	
	/**
	 * @param m_conn
	 *            the m_conn to set
	 */
	public static void setConn(Connection conn) {
		m_conn = conn;
	}

	//=======================================================================
	
	private void setColHeadersAndTypes(String type) {
		String sList = new String();
		PreparedStatement ps = null;
		ResultSet rs = null;

		if (sList == "")
			sList = "CDE_IDSEQ,DEC_IDSEQ,VD_IDSEQ,Conceptual Domain Public ID,Conceptual Domain Short Name,Conceptual Domain Version,Conceptual Domain Context Name";

		ArrayList<String> excluded = new ArrayList<String>();

		for (String col : sList.split(",")) {
			excluded.add(col);
		}

		try {
			String qry = "SELECT * FROM " + type
					+ "_EXCEL_GENERATOR_VIEW where 1=2";
			ps = getConn().prepareStatement(qry);
//			Object[] inputValues = new Object[columnNames.length];
//		    inputValues[0] = new java.math.BigDecimal(100);
//		    inputValues[1] = new String("String Value");
//		    inputValues[2] = new String("This is my resume.");
//		    inputValues[3] = new Timestamp((new java.util.Date()).getTime());
//
//		    // prepare blob object from an existing binary column
//		    String insert = "insert into resume (id, name, content, date_created ) values(?, ?, ?, ?)";
//		    pstmt = conn.prepareStatement(insert);
//
//		    pstmt.setObject(1, inputValues[0]);
//		    pstmt.setObject(2, inputValues[1]);
//		    pstmt.setObject(3, inputValues[2]);
//		    pstmt.setObject(4, inputValues[3]);
		    
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			int numColumns = rsmd.getColumnCount();
			// Get the column names and types; column indices start from 1
			for (int i = 1; i < numColumns + 1; i++) {
				String columnName = rsmd.getColumnName(i);
				columnName = prettyName(columnName);
				columnHeaders.add(columnName);

				String columnType = rsmd.getColumnTypeName(i);

				if (columnType.endsWith("_T")
						&& !typeMap.containsKey(columnType)) {
					String typeKey = i + ":" + columnType;

					columnTypes.add(typeKey);
					ArrayList<String[]> typeBreakdown = getType(typeKey,
							columnName, type);
					typeMap.put(i + ":" + columnType, typeBreakdown);

					if (typeBreakdown.size() > 0) {
						String[] typeColNames = typeBreakdown.get(0);

						String[] orderedTypeColNames = getOrderedTypeNames(
								typeKey, columnName, type);
						for (int c = 0; c < orderedTypeColNames.length; c++) {
							arrayColumnTypes.put(typeColNames[c], typeKey); 	//TBD - need to fix this mapping
							allExpandedColumnHeaders
									.add(orderedTypeColNames[c]); 
						}
					} else
						allExpandedColumnHeaders.add(columnName);

				} else {
					columnTypes.add(columnType);
					allExpandedColumnHeaders.add(columnName);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (Exception e) {
				}
		}
	}

	//=======================================================================
	
	private String prettyName(String name) {

		if (name.startsWith("DE "))
			return name.replace("DE ", "Data Element ");
		else if (name.startsWith("DEC "))
			return name.replace("DEC ", "Data Element Concept ");
		else if (name.startsWith("VD "))
			return name.replace("VD ", "Value Domain ");
		else if (name.startsWith("OC "))
			return name.replace("OC ", "Object Class ");
		else if (name.startsWith("CD "))
			return name.replace("CD ", "Conceptual Domain ");

		return name;
	}

	//=======================================================================
	
	private ArrayList<String[]> getType(String type, String name,
			String download) {

		ArrayList<String[]> colNamesAndTypes = new ArrayList<String[]>();

		ArrayList<String> attrName = new ArrayList<String>();
		ArrayList<String> attrTypeName = new ArrayList<String>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlStmt = "select * from sbrext.custom_download_types c where UPPER(c.type_name) = ? order by c.column_index";
		String[] splitType = type.split("\\.");
		
		type = splitType[1];

		try {
			ps = getConn().prepareStatement(sqlStmt);
			ps.setString(1, type);
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				i++;
				String col = rs.getString("DISPLAY_NAME");
				String ctype = rs.getString("DISPLAY_TYPE");
				if (type.toUpperCase().contains("CONCEPT")) {
					if (name.toUpperCase().startsWith("REP"))
						name = "Representation Concept";
					else if (name.toUpperCase().startsWith("VD"))
						name = "Value Domain Concept";
					else if (name.toUpperCase().startsWith("OC"))
						name = "Object Class Concept";
					else if (name.startsWith("PROP"))
						name = "Property Concept";

					col = name + " " + col;
				}
				if (type.toUpperCase().contains("DESIGNATION")) {
					if (download.equals("CDE"))
						download = "Data Element";
					else if (download.equals("VD"))
						download = "Value Domain";
					else if (download.equals("DEC"))
						download = "Data Element Concept";

					col = download + " " + col;
				}

				attrName.add(col);
				attrTypeName.add(ctype);
			}
			// System.out.println(type + " "+i);
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] attrNames = new String[attrName.size()];
		String[] attrTypeNames = new String[attrTypeName.size()];

		for (int i = 0; i < attrName.size(); i++) {
			attrNames[i] = attrName.get(i);
			attrTypeNames[i] = attrTypeName.get(i);
		}
		colNamesAndTypes.add(attrNames);
		colNamesAndTypes.add(attrTypeNames);

		return colNamesAndTypes;
	}

	//=======================================================================
	
	private String[] getOrderedTypeNames(String type, String name,
			String download) {

		ArrayList<String> attrName = new ArrayList<String>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlStmt = "select * from sbrext.custom_download_types c where UPPER(c.type_name) = ? order by c.display_column_index";
		String[] splitType = type.split("\\.");

		type = splitType[1];

		try {
			ps = getConn().prepareStatement(sqlStmt);
			ps.setString(1, type);
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				i++;
				String col = rs.getString("DISPLAY_NAME");
				String ctype = rs.getString("DISPLAY_TYPE");
				if (type.toUpperCase().contains("CONCEPT")) {
					if (name.toUpperCase().startsWith("REP"))
						name = "Representation Concept";
					else if (name.toUpperCase().startsWith("VD"))
						name = "Value Domain Concept";
					else if (name.toUpperCase().startsWith("OC"))
						name = "Object Class Concept";
					else if (name.startsWith("PROP"))
						name = "Property Concept";

					col = name + " " + col;
				}
				if (type.toUpperCase().contains("DESIGNATION")) {
					if (download.equals("CDE"))
						download = "Data Element";
					else if (download.equals("VD"))
						download = "Value Domain";
					else if (download.equals("DEC"))
						download = "Data Element Concept";

					col = download + " " + col;
				}

				attrName.add(col);
			}
			// System.out.println(type + " "+i);
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] attrNames = new String[attrName.size()];

		for (int i = 0; i < attrName.size(); i++) {
			attrNames[i] = attrName.get(i);

		}
		return attrNames;

	}

	//=======================================================================
	
	private ArrayList<String[]> getRecords(boolean full, boolean restrict) {

		ArrayList<String[]> rows = new ArrayList<String[]>();

		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			if (getConn() == null) {
				logger.error("Cannot get DB Connection");
			} else {
				int rowNum = 0;
				List<String> sqlStmts = getSQLStatements(full, restrict);
				for (String sqlStmt : sqlStmts) {
					ps = getConn().prepareStatement(sqlStmt);
					rs = ps.executeQuery();

					ResultSetMetaData rsmd = rs.getMetaData();
					int numColumns = rsmd.getColumnCount();

					while (rs.next()) {
						String[] row = new String[numColumns];
						HashMap<String, List<String[]>> typeArrayData = null;

						for (int i = 0; i < numColumns; i++) {
							if (columnTypes.get(i).endsWith("_T")) {
								List<String[]> rowArrayData = getRowArrayData(
										rs, columnTypes.get(i), i);

								if (typeArrayData == null) {
									typeArrayData = new HashMap<String, List<String[]>>();
								}
								typeArrayData.put(columnTypes.get(i),
										rowArrayData);
							} else {
								 //truncate timestamp
								if (columnTypes.get(i).equalsIgnoreCase("Date")) {
									row[i] = AdministeredItemUtil
											.truncateTime(rs.getString(i + 1));
								} else {
									row[i] = rs.getString(i + 1);	//??? getString() even for STRUCT ???
								}
								// System.out.println("rs.getString(i+1) = " +
								// rs.getString(i+1));
							}
						}
						// If there were no arrayData added, add null to keep
						// parity with rows.
						if (typeArrayData == null) {
							arrayData.add(null);
						} else {
							arrayData.add(rowNum, typeArrayData);
						}

						rows.add(row);
						rowNum++;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (Exception e) {
				}
		}

		return rows;
	}

	//=======================================================================
	
	private Sheet fillInBump(Sheet sheet, int originalRow, int rownum,
			int bump, ArrayList<String[]> allRows, ArrayList<String> allTypes,
			int[] colIndices) {
		String temp = null;
		for (int a = rownum; a < rownum + bump; a++) {
			Row row = sheet.getRow(a);

			for (int j = 0; j < colIndices.length; j++) {

				String currentType = allTypes.get(colIndices[j]);
				if (currentType.endsWith("_T")) {
					// Do nothing
				} else {
					Cell cell = row.createCell(j);
					temp = allRows.get(originalRow)[colIndices[j]];
					logger.debug("at line 481 of TestSpreadsheetDownload.java*****"
							+ temp + currentType);
					if (currentType.equalsIgnoreCase("Date")) { 
						temp = AdministeredItemUtil.truncateTime(temp);
					}
					cell.setCellValue(temp);
				}

			}
		}
		return sheet;
	}

	//=======================================================================
	
	private List<String[]> getRowArrayData(ResultSet rs, String columnType,
			int index) throws Exception {
		List<String[]> rowArrayData = new ArrayList<String[]>();
		Array array = null;
		// Special case: first row has info on derivation, others on data
		// elements
		if (columnType.indexOf("DERIVED") > 0) {
			Object derivedObject = rs.getObject(index + 1);
			Struct struct = (Struct) derivedObject;
//			STRUCT struct = (STRUCT) derivedObject;
			Object[] valueStruct = struct.getAttributes();
			// Fifth entry is the array with DE's
			array = (Array) valueStruct[5];

			if (array != null) {
				String[] derivationInfo = new String[5];
				for (int z = 0; z < 5; z++)
					derivationInfo[z] = (valueStruct[z] != null) ? valueStruct[z]
							.toString() : "";

				rowArrayData.add(derivationInfo);

				ResultSet nestedRs = array.getResultSet();

				while (nestedRs.next()) {
					Struct deStruct = (Struct) nestedRs.getObject(2);
//					STRUCT deStruct = (STRUCT) nestedRs.getObject(2);
					Object[] valueDatum = deStruct.getAttributes();
//					Datum[] valueDatum = ((oracle.sql.STRUCT)nestedRs).getOracleAttributes();
					
					String[] values = new String[valueDatum.length];

					for (int a = 0; a < valueDatum.length; a++) {
						if (valueDatum[a] != null) {
							Class c = valueDatum[a].getClass();
							String s = c.getName();
							values[a] = valueDatum[a].toString();
						}
					}
					rowArrayData.add(values);
				}
			}
		} else {
			array = rs.getArray(index + 1);
			if (array != null) {
				ResultSet nestedRs = array.getResultSet();

				while (nestedRs.next()) {
					try {
						//=== just for debugging
						//					if(rs.getObject(1) instanceof java.sql.Struct) {
						oracle.sql.STRUCT type = (oracle.sql.STRUCT) rs
								.getObject(1);
						System.out.println("Number of attributes "
								+ type.getAttributes().length);
						System.out
								.println("col1 is " + type.getAttributes()[0]);
						System.out
								.println("col2 is " + type.getAttributes()[1]);
						System.out
								.println("col3 is " + type.getAttributes()[2]);
						//					}
						//=== just for debugging
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					Struct valueStruct = (Struct) nestedRs.getObject(2);
//					STRUCT valueStruct = (STRUCT) nestedRs.getObject(1);
					Object[] valueDatum = valueStruct.getAttributes();
					System.out.println("TYPE types = [" + Arrays.asList(valueDatum) + "]");
					String[] values = new String[valueDatum.length];
					int slide = 0;
					for (int a = 0; a < valueDatum.length; a++) {
						if (valueDatum[a] != null) {
							Class c = valueDatum[a].getClass();
							String s = c.getName();
							String truncatedTimeStamp = null; // GF30799

							if (c.getName().toUpperCase().contains("STRUCT")) {
								Struct str = (Struct) valueDatum[a];	//this points ???
//								STRUCT str = (STRUCT) valueDatum[a];	//this points ???
								Object[] strValues = str.getAttributes();
//								logger.info("TYPE = [" + Arrays.asList(strValues) + "]");
								System.out.println("TYPE values = [" + Arrays.asList(strValues) + "]");
								
								values = new String[valueDatum.length
										+ strValues.length - 1];
								slide = -1;
								for (int b = 0; b < strValues.length; b++) {
									truncatedTimeStamp = strValues[b]
											.toString(); // begin GF30779
									logger.debug("At line 559 of TestSpreadsheetDownload.java"
											+ truncatedTimeStamp);
									truncatedTimeStamp = AdministeredItemUtil
											.toASCIICode(truncatedTimeStamp); 
									logger.debug("At line 563 of TestSpreadsheetDownload.java"
											+ truncatedTimeStamp
											+ s
											+ valueDatum[a] + strValues[b]);
									if (columnType.contains("VALID_VALUE")
											&& truncatedTimeStamp != null
											&& truncatedTimeStamp.contains(":")) {
										truncatedTimeStamp = AdministeredItemUtil
												.truncateTime(truncatedTimeStamp);
										logger.debug("At line 572 of TestSpreadsheetDownload.java"
												+ truncatedTimeStamp);
									} // end GF30779
									values[b] = truncatedTimeStamp;
									slide++;
								}
							} else {
								truncatedTimeStamp = valueDatum[a].toString(); 
								logger.debug("At line 580 of TestSpreadsheetDownload.java"
										+ truncatedTimeStamp);
								truncatedTimeStamp = AdministeredItemUtil
										.toASCIICode(truncatedTimeStamp);
								logger.debug("At line 584 of TestSpreadsheetDownload.java"
										+ truncatedTimeStamp
										+ s
										+ valueDatum[a]);
								if (columnType.contains("VALID_VALUE")
										&& truncatedTimeStamp != null
										&& truncatedTimeStamp.contains(":")) {
									truncatedTimeStamp = AdministeredItemUtil
											.truncateTime(truncatedTimeStamp);
									logger.debug("At line 593 of TestSpreadsheetDownload.java"
											+ truncatedTimeStamp);
								} // end GF30779
								values[a + slide] = truncatedTimeStamp;
							}

						} else {
							values[a] = "";
						}
					}
					rowArrayData.add(values);
				}
			}
		}
		return rowArrayData;
	}

	//=======================================================================
	
	private List<String> getSQLStatements(boolean full, boolean restrict) {
		List<String> sqlStmts = new ArrayList<String>();
		ArrayList<String> downloadIDs = new ArrayList<String>();
		downloadIDs.add("C67194F6-BFC9-53D6-E034-0003BA12F5E7");// IDSEQ of DE with Long name is DNA Index Value and Public ID is 64516
		String type = "CDE";

		String sqlStmt = null;
		if (!full) {
			StringBuffer[] whereBuffers = getWhereBuffers(downloadIDs);
			for (StringBuffer wBuffer : whereBuffers) {
				sqlStmt = "SELECT * FROM " + type + "_EXCEL_GENERATOR_VIEW "
						+ "WHERE " + type + "_IDSEQ IN " + " ( "
						+ wBuffer.toString() + " )  ";
				if (restrict) {
					sqlStmt += " and ROWNUM <= " + GRID_MAX_DISPLAY;
					sqlStmts.add(sqlStmt);
					break;
				} else {
					sqlStmts.add(sqlStmt);
				}
			}
		} else {
			sqlStmt = "SELECT * FROM " + type + "_EXCEL_GENERATOR_VIEW";
			if (restrict)
				sqlStmt += " where ROWNUM <= " + GRID_MAX_DISPLAY;

			sqlStmts.add(sqlStmt);
		}

		return sqlStmts;
	}

	//=======================================================================
	
	private StringBuffer[] getWhereBuffers(ArrayList<String> downloadIds) {
		StringBuffer whereBuffer = null;
		List<StringBuffer> whereBuffers = null;

		if (downloadIds.size() <= 1000) { // make sure that there are no more
											// than 1000 ids in each 'IN' clause
			whereBuffer = new StringBuffer();
			for (String id : downloadIds) {
				whereBuffer.append("'" + id + "',");
			}
			whereBuffer.deleteCharAt(whereBuffer.length() - 1);
		} else {
			whereBuffers = new ArrayList<StringBuffer>();
			int counter = 0;
			whereBuffer = new StringBuffer();

			for (String id : downloadIds) {
				whereBuffer.append("'" + id + "',");

				counter++;

				if (counter % 1000 == 0) {
					whereBuffer.deleteCharAt(whereBuffer.length() - 1);
					whereBuffers.add(whereBuffer);
					whereBuffer = new StringBuffer();
				}
			}

			// add the final chunk to the list
			if (whereBuffer.length() > 0) {
				whereBuffer.deleteCharAt(whereBuffer.length() - 1);
				whereBuffers.add(whereBuffer);
			}
		}

		if (whereBuffers == null) {
			whereBuffers = new ArrayList<StringBuffer>(1);
			whereBuffers.add(whereBuffer);
		}

		return whereBuffers.toArray(new StringBuffer[0]);
	}

	//=======================================================================
	
	private void createDownloadColumns(ArrayList<String[]> allRows) {
		final int MAX_ROWS = 65000;

		String sheetName = "Custom Download";
		int sheetNum = 1;
		String fillIn = "true";
		String[] columns = null;
		
			ArrayList<String> defaultHeaders = new ArrayList<String>();

			for (String cName : allExpandedColumnHeaders) {
				if (cName.endsWith("IDSEQ") || cName.startsWith("CD ")
						|| cName.startsWith("Conceptual Domain")) { /* skip */
				} else {
					System.out.println("cName = " + cName);
					defaultHeaders.add(cName);
				}
			}
			columns = defaultHeaders.toArray(new String[defaultHeaders.size()]);

		int[] colIndices = new int[columns.length];
		for (int i = 0; i < columns.length; i++) {
			String colName = columns[i];
			if (columnHeaders.indexOf(colName) < 0) {
				String tempType = arrayColumnTypes.get(colName);
				int temp = columnTypes.indexOf(tempType);
				colIndices[i] = temp;
			} else {
				int temp = columnHeaders.indexOf(colName);
				colIndices[i] = temp;
			}
		}

		Workbook wb = new HSSFWorkbook();

		Sheet sheet = wb.createSheet(sheetName);
		Font font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		CellStyle boldCellStyle = wb.createCellStyle();
		boldCellStyle.setFont(font);
		boldCellStyle.setAlignment(CellStyle.ALIGN_GENERAL);

		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(12.75f);
		String temp;
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			temp = columns[i];
			cell.setCellValue(temp);
			cell.setCellStyle(boldCellStyle);
		}

		// freeze the first row
		sheet.createFreezePane(0, 1);

		Row row = null;
		Cell cell;
		int rownum = 1;
		int bump = 0;
		int i = 0;
		try {
			System.out.println("Total CDEs to download [" + allRows.size()
					+ "]");
			for (i = 0; i < allRows.size(); i++, rownum++) {
				// Check if row already exists
				int maxBump = 0;
				if (sheet.getRow(rownum + bump) == null) {
					row = sheet.createRow(rownum + bump);
				}

				if (allRows.get(i) == null)
					continue;

				for (int j = 0; j < colIndices.length; j++) {

					cell = row.createCell(j);
					String currentType = columnTypes.get(colIndices[j]);
					if (currentType.endsWith("_T")) {
						// Deal with CS/CSI
						String[] originalArrColNames = typeMap.get(currentType)
								.get(0);

						// Find current column in original data

						int originalColumnIndex = -1;
						for (int a = 0; a < originalArrColNames.length; a++) {
							if (columns[j].equals(originalArrColNames[a])) {
								originalColumnIndex = a;
								break;
							}
						}
						// ArrayList<HashMap<String,ArrayList<String[]>>>
						// arrayData1 =
						// (ArrayList<HashMap<String,ArrayList<String[]>>>)arrayData;
						HashMap<String, List<String[]>> typeArrayData = arrayData
								.get(i);
						ArrayList<String[]> rowArrayData = (ArrayList<String[]>) typeArrayData
								.get(currentType);

						if (rowArrayData != null) {
							int tempBump = 0;
							for (int nestedRowIndex = 0; nestedRowIndex < rowArrayData
									.size(); nestedRowIndex++) {

								String[] nestedData = rowArrayData
										.get(nestedRowIndex);
								String data = "";
								if (currentType.contains("DERIVED")) {
									// Derived data element is special double
									// nested, needs to be modified to be more
									// general.

									// General DDE information is in the first 4
									// columns, but contained in the first row
									// of the Row Array Data
									if (originalColumnIndex < 5) {
										if (nestedRowIndex == 0)
											data = (originalColumnIndex > 0) ? nestedData[originalColumnIndex]
													: nestedData[originalColumnIndex + 1]; 
									} else {
										if (nestedRowIndex + 1 < rowArrayData
												.size()) {
											data = rowArrayData
													.get(nestedRowIndex + 1)[originalColumnIndex - 5];
										}
									}

								} else
									data = nestedData[originalColumnIndex];
								logger.debug("at line 828 of TestSpreadsheetDownload.java*****"
										+ data + currentType);
								if (currentType.contains("VALID_VALUE")) { 
									data = AdministeredItemUtil
											.truncateTime(data);
								}
								cell.setCellValue(data);

								tempBump++;

								if (nestedRowIndex < rowArrayData.size() - 1) {
									row = sheet
											.getRow(rownum + bump + tempBump);
									if (row == null) {
										if (rownum + bump + tempBump >= MAX_ROWS) {
											sheet = wb.createSheet(sheetName
													+ "_" + sheetNum);
											sheetNum++;
											rownum = 1;
											bump = 0;
											tempBump = 0;
										}
										row = sheet.createRow(rownum + bump
												+ tempBump);
									}

									cell = row.createCell(j);

								} else {
									// Go back to top row
									row = sheet.getRow(rownum + bump);
									if (tempBump > maxBump)
										maxBump = tempBump;
								}
							}
						}
					} else {
						temp = allRows.get(i)[colIndices[j]];
						logger.debug("at line 866 of TestSpreadsheetDownload.java*****"
								+ temp + currentType);
						if (currentType.equalsIgnoreCase("Date")) {
							temp = AdministeredItemUtil.truncateTime(temp);
						}
						cell.setCellValue(temp);
					}

				}

				bump = bump + maxBump;

				if (fillIn != null
						&& (fillIn.equals("true") || fillIn.equals("yes")
								&& bump > 0)) {
					sheet = fillInBump(sheet, i, rownum, bump, allRows,
							columnTypes, colIndices);
					rownum = rownum + bump;
					bump = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// Please specify the path below if needed, otherwise it will create in the root/dir where this test class is run
			fileOutputStream = new FileOutputStream("Test_Excel.xls");
			wb.write(fileOutputStream);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/**
			 * Close the fileOutputStream.
			 */
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	//=======================================================================
}