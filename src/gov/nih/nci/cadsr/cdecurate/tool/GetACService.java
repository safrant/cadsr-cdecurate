// Copyright (c) 2000 ScenPro, Inc.
// $Header: /cvsshare/content/cvsroot/cdecurate/src/gov/nih/nci/cadsr/cdecurate/tool/GetACService.java,v 1.38 2007-01-26 20:17:44 hegdes Exp $
// $Name: not supported by cvs2svn $

package gov.nih.nci.cadsr.cdecurate.tool;

import java.io.Serializable;
import java.util.*;
import java.sql.*;
import oracle.jdbc.driver.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.apache.log4j.*;

/**
 * GetACService class retrieves list from the db and stores them in session vectors
 * <P>
 * 
 * @author Joe Zhou, Sumana Hegde, Tom Phillips
 * 
 */
public class GetACService implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 6486668887681006373L;

    // Connection m_sbr_db_conn = null;
    private NCICurationServlet       m_servlet;

    private UtilService                     m_util           = new UtilService();

    private HttpServletRequest       m_classReq       = null;

    private HttpServletResponse     m_classRes       = null;

    private static final Logger         logger           = Logger.getLogger(GetACService.class.getName());

    /**
     * Constructor
     * 
     * @param req
     *            HttpServletRequest object
     * @param res
     *            HttpServletResponse object
     * @param CurationServlet
     *            NCICurationServlet object
     */
    public GetACService(HttpServletRequest req, HttpServletResponse res, NCICurationServlet CurationServlet)
    {
        m_classReq = req;
        m_classRes = res;
        m_servlet = CurationServlet;
    }

    /**
     * 
     */
    public GetACService()
    {
    }

    /**
     * The getACList method queries the db for various lists the program needs to dropdown selection boxes. The lists
     * are stored as session vectors.
     * 
     * @param req
     *            The HttpServletRequest from the client
     * @param res
     *            The HttpServletResponse back to the client
     * @param sContext
     *            String context.
     * @param bNewContext
     *            Boolean indicating whether context is new.
     * @param sACType
     *            The type of page being called, i.e. de, dec, or vd
     * @throws IOException
     * @throws ServletException
     * 
     */
    public void getACList(HttpServletRequest req, HttpServletResponse res, String sContext, boolean bNewContext,
                    String sACType) throws IOException, ServletException
    {
        Connection m_sbr_db_conn = null;
        try
        {
            // logger.info(m_servlet.getLogMessage(req, "getACList", "started", startDate, startDate));
            HttpSession session = req.getSession();
            m_sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (m_sbr_db_conn != null)
            {
                session.setAttribute("ConnectedToDB", "Yes");
                Vector<String> v;
                Vector<String> vID;
                session.setAttribute("ContextInList", sContext);
                // get cs-csi relationship data
                getCSCSIListBean();
                Vector vCon = (Vector) session.getAttribute("vContext");
                if (vCon == null || vCon.size() < 2)
                {
                    v = new Vector<String>();
                    vID = new Vector<String>();
                    getContextList(vID, v);
                    session.setAttribute("vContext", v);
                    session.setAttribute("vContext_ID", vID);
                }
                if (session.getAttribute("vWriteContextDE") == null)
                {
                    String sUser = (String) session.getAttribute("Username");
                    v = new Vector<String>();
                    vID = new Vector<String>();
                    getWriteContextList(vID, v, sUser, "DATAELEMENT");
                    session.setAttribute("vWriteContextDE", v);
                    session.setAttribute("vWriteContextDE_ID", vID);
                    v = new Vector<String>();
                    vID = new Vector<String>();
                    getWriteContextList(vID, v, sUser, "DE_CONCEPT");
                    session.setAttribute("vWriteContextDEC", v);
                    session.setAttribute("vWriteContextDEC_ID", vID);
                    v = new Vector<String>();
                    vID = new Vector<String>();
                    getWriteContextList(vID, v, sUser, "VALUEDOMAIN");
                    session.setAttribute("vWriteContextVD", v);
                    session.setAttribute("vWriteContextVD_ID", vID);
                }
                if (session.getAttribute("vUsers") == null)
                {
                    v = new Vector<String>();
                    vID = new Vector<String>();
                    getUserList(vID, v);
                    session.setAttribute("vUsers", vID);
                    session.setAttribute("vUsersName", v);
                }
                if (session.getAttribute("vStatusALL") == null)
                {
                    v = new Vector<String>();
                    getStatusList("", v);
                    session.setAttribute("vStatusALL", v);
                }
                if (session.getAttribute("vStatusDE") == null)
                {
                    v = new Vector<String>();
                    getStatusList("DATAELEMENT", v);
                    session.setAttribute("vStatusDE", v);
                }
                if (session.getAttribute("vStatusDEC") == null)
                {
                    v = new Vector<String>();
                    getStatusList("DE_CONCEPT", v);
                    session.setAttribute("vStatusDEC", v);
                }
                if (session.getAttribute("vStatusVD") == null)
                {
                    v = new Vector<String>();
                    getStatusList("VALUEDOMAIN", v);
                    session.setAttribute("vStatusVD", v);
                }
                if (session.getAttribute("vStatusCD") == null)
                {
                    v = new Vector<String>();
                    getStatusList("CONCEPTUALDOMAIN", v);
                    session.setAttribute("vStatusCD", v);
                }
                if (session.getAttribute("vLanguage") == null)
                {
                    v = new Vector<String>();
                    getLanguageList(v);
                    session.setAttribute("vLanguage", v);
                }
                if (session.getAttribute("vSource") == null)
                {
                    v = new Vector<String>();
                    getSourceList(v);
                    session.setAttribute("vSource", v);
                }
                if (session.getAttribute("vRegStatus") == null)
                {
                    v = new Vector<String>();
                    getRegStatusList(v);
                    session.setAttribute("vRegStatus", v);
                }
                // list of ref documents used in the search
                if (session.getAttribute("vRDocType") == null)
                {
                    v = new Vector<String>();
                    this.getRDocTypesList(v);
                    session.setAttribute("vRDocType", v);
                }
                if ((session.getAttribute("vCS") == null && sContext != null) || bNewContext)
                {
                    v = new Vector<String>();
                    vID = new Vector<String>();
                    getCSList(vID, v, sContext);
                    session.setAttribute("vCS", v);
                    session.setAttribute("vCS_ID", vID);
                }
                // these two may not needed
                if (session.getAttribute("vCSI") == null)
                {
                    v = new Vector<String>();
                    vID = new Vector<String>();
                    session.setAttribute("vCSI", v);
                    session.setAttribute("vCSI_ID", vID);
                }
                if (session.getAttribute("vCSCSI_CS") == null)
                {
                    v = new Vector<String>();
                    vID = new Vector<String>();
                    session.setAttribute("vCSCSI_CS", vID);
                    session.setAttribute("vCSCSI_CSI", v);
                }
                // the above two may not needed
                if ((session.getAttribute("vCD") == null && sContext != null) || bNewContext)
                {
                    v = new Vector<String>();
                    vID = new Vector<String>();
                    getConceptualDomainList(vID, v, sContext);
                    session.setAttribute("vCD", v);
                    session.setAttribute("vCD_ID", vID);
                }
                if (session.getAttribute("vDataType") == null)
                {
                    v = new Vector<String>();
                    Vector<String> vDesc = new Vector<String>();
                    Vector<String> vComm = new Vector<String>();
                    getDataTypesList(v, vDesc, vComm);
                    // add emtpy data at the beginning
                    v.insertElementAt("", 0);
                    vDesc.insertElementAt("", 0);
                    vComm.insertElementAt("", 0);
                    session.setAttribute("vDataType", v);
                    session.setAttribute("vDataTypeDesc", vDesc);
                    session.setAttribute("vDataTypeComment", vComm);
                }
                // list of uoml list
                if (session.getAttribute("vUOM") == null)
                {
                    v = new Vector<String>();
                    getUOMList(v);
                    session.setAttribute("vUOM", v);
                }
                // list of uoml format for vd
                if (session.getAttribute("vUOMFormat") == null)
                {
                    v = new Vector<String>();
                    getUOMFormatList(v);
                    session.setAttribute("vUOMFormat", v);
                }
                // list of alternate names for create
                if (session.getAttribute("AltNameTypes") == null)
                    this.getAltNamesList(session);
                // list of reference documents for create
                if (session.getAttribute("RefDocTypes") == null)
                    this.getRefDocsList(session);
                // list of derivation types for create
                if (session.getAttribute("vRepType") == null)
                    this.getDerTypesList(session);
                // list of organizations
                if (session.getAttribute("Organizations") == null)
                    this.getOrganizeList();
                // list of organizations
                if (session.getAttribute("Persons") == null)
                    this.getPersonsList();
                // list of organizations
                if (session.getAttribute("ContactRoles") == null)
                    this.getContactRolesList();
                // list of organizations
                if (session.getAttribute("CommType") == null)
                    this.getCommTypeList();
                // list of organizations
                if (session.getAttribute("AddrType") == null)
                    this.getAddrTypeList();
                // list of NVP concepts
                if (session.getAttribute("NVPConcepts") == null)
                    this.getNVPConcepts(session);
                m_sbr_db_conn.close();
                // logger.info(m_servlet.getLogMessage(req, "getACList", "ended", startDate, new java.util.Date()));
            }
            else
            {
                logger.fatal("getAClist: no db connection");
                session.setAttribute("ConnectedToDB", "No");
            }
        }
        catch (Exception e)
        {
            try
            {
                if (m_sbr_db_conn != null)
                    m_sbr_db_conn.close();
            }
            catch (Exception f)
            {
            }
            // System.err.println("ERROR in GetACService-getACList: " + e);
            logger.fatal("ERROR in GetACService-getACList : " + e.toString(), e);
        }
        try
        {
            if (m_sbr_db_conn != null)
                m_sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            // System.err.println("ERROR in GetACService-getACList close: " + ee);
            logger.fatal("ERROR in GetACService-getACList close: " + ee.toString(), ee);
        }
    }

    /**
     * The verifyConnection method queries the db to test the connection and sets the ConnectedToDB variable.
     * 
     * ConnectedToDB = "Nothing" (default) ConnectedToDB = "Yes" (connection availble) ConnectedToDB = "No" (no
     * connection availble)
     * 
     * @param req
     *            The HttpServletRequest from the client
     * @param res
     *            The HttpServletResponse back to the client
     * 
     */
    public void verifyConnection(HttpServletRequest req, HttpServletResponse res)
    {
        Connection m_sbr_db_conn = null;
        try
        {
            // logger.info(m_servlet.getLogMessage(req, "getACList", "started", startDate, startDate));
            HttpSession session = req.getSession();
            m_sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (m_sbr_db_conn != null)
            {
                session.setAttribute("ConnectedToDB", "Yes");
                m_sbr_db_conn.close();
            }
            else
            {
                logger.fatal("verifyConnection: no db connection");
                session.setAttribute("ConnectedToDB", "No");
            }
        }
        catch (Exception e)
        {
            try
            {
                if (m_sbr_db_conn != null)
                    m_sbr_db_conn.close();
            }
            catch (Exception f)
            {
            }
            logger.fatal("ERROR in GetACService-verifyConnection : " + e.toString(), e);
        }
        try
        {
            if (m_sbr_db_conn != null)
                m_sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            logger.fatal("ERROR in GetACService-verifyConnection close: " + ee.toString(), ee);
        }
    }

    /**
     * The getUserList method gets list of users in the database and Names, which are stored in vectors. Calls the
     * stored procedure: SBREXT_CDE_CURATOR_PKG.GET_USER_LIST(?,?)
     * 
     * @param vUANameList
     *            A Vector of the user login names
     * @param vNameList
     *            A Vector of full names.
     * 
     */
    private void getUserList(Vector<String> vUANameList, Vector<String> vNameList)
    {
        try
        {
            String sAPI = "{call SBREXT_CDE_CURATOR_PKG.GET_USER_LIST(?)}";
            getDataListStoreProcedure(vUANameList, vNameList, null, null, sAPI, "", "", 1);
        }
        catch (Exception e)
        {
            // System.err.println("Problem getUserList: " + e);
            logger.fatal("ERROR in GetACService-getUserList : " + e.toString(), e);
        }
    }

    /**
     * The getContextList method queries the db for Context Name and ID lists, stored in vectors. Calls the stored
     * procedure: SBREXT_SS_API.get_context_list(?)
     * 
     * @param vIDList
     *            A Vector of the ID's.
     * @param vList
     *            A Vector of Context names.
     * 
     */
    private void getContextList(Vector<String> vIDList, Vector<String> vList)
    {
        try
        {
            String sAPI = "{call SBREXT_SS_API.get_context_list(?)}";
            getDataListStoreProcedure(vIDList, vList, null, null, sAPI, "", "", 1);
        }
        catch (Exception e)
        {
            // System.err.println("Problem getContextList: " + e);
            logger.fatal("ERROR in GetACService-getContextList : " + e.toString(), e);
        }
    }

    /**
     * The getWriteContextList method queries the db for Context Name and ID lists, where user has write permission.
     * Lists stored in vectors. Calls the stored procedure: SBREXT_SS_API.get_write_context_list(?, ?, ?)
     * 
     * @param vIDList
     *            A Vector of the ID's.
     * @param vList
     *            A Vector of Context names.
     * @param UserName
     *            Name of user.
     * @param ActlType
     * 
     */
    private void getWriteContextList(Vector<String> vIDList, Vector<String> vList, String UserName, String ActlType)
    {
        try
        {
            UserName = UserName.toUpperCase();
            String sAPI = "{call SBREXT_SS_API.get_write_context_list(?, ?, ?)}";
            getDataListStoreProcedure(vIDList, vList, null, null, sAPI, UserName, ActlType, 3);
        }
        catch (Exception e)
        {
            // System.err.println("Problem getContextList: " + e);
            logger.fatal("ERROR in GetACService-getWriteContextList : " + e.toString(), e);
        }
    }

    /**
     * The getStatusList method queries the db for a list of Statuses, stored in a vector. Calls the stored procedure:
     * SBREXT_SS_API.get_status_list(?)
     * 
     * @param ACType
     *            STring ac type
     * 
     * @param vList
     *            A Vector of Status names.
     * 
     */
    private void getStatusList(String ACType, Vector<String> vList)
    {
        try
        {
            String sAPI = "{call SBREXT_SS_API.get_status_list(?,?)}";
            getDataListStoreProcedure(vList, null, null, null, sAPI, ACType, "", 2);
        }
        catch (Exception e)
        {
            // System.err.println("Problem getStatusList: " + e);
            logger.fatal("ERROR in GetACService-getStatusList : " + e.toString(), e);
        }
    }

    /**
     * The getConceptualDomainList method queries the db for lists of Names and ID's, which are stored in vectors. Calls
     * the stored procedure: SBREXT_CDE_CURATOR_PKG.get_conceptual_domain_list(?,?)
     * 
     * @param vIDList
     *            A Vector of the ID's.
     * @param vList
     *            A Vector of names.
     * @param sContext
     *            The context to search in.
     * 
     */
    private void getConceptualDomainList(Vector<String> vIDList, Vector<String> vList, String sContext)
    {
        try
        {
            Vector<String> vContextList = new Vector<String>();
            Vector<String> vASLlist = new Vector<String>();
            String sAPI = "{call SBREXT_CDE_CURATOR_PKG.get_conceptual_domain_list(?,?)}";
            getDataListStoreProcedure(vIDList, vList, vASLlist, vContextList, sAPI, "", "", 2);
            // add the context to the names
            if ((vList != null) && (vContextList != null))
                for (int i = 0; i < vList.size(); i++)
                {
                    String cdName = (String) vList.elementAt(i);
                    cdName = m_util.removeNewLineChar(cdName);
                    cdName = m_util.parsedStringDoubleQuoteJSP(cdName);
                    String cdContext = (String) vContextList.elementAt(i);
                    vList.setElementAt(cdName + " - " + cdContext, i);
                }
        }
        catch (Exception e)
        {
            // System.err.println("Problem getConceptualDomainList: " + e);
            logger.fatal("ERROR in GetACService-getConceptualDomainList : " + e.toString(), e);
        }
    }

    /**
     * The getCSList method queries the db for lists of Names and ID's, which are stored in vectors. Calls the stored
     * procedure: SBREXT_CDE_CURATOR_PKG.get_conceptual_domain_list(?,?)
     * 
     * @param vIDList
     *            A Vector of the ID's.
     * @param vList
     *            A Vector of names.
     * @param sContext
     *            The context to search in.
     * 
     */
    private void getCSList(Vector<String> vIDList, Vector<String> vList, String sContext)
    {
        Connection sbr_db_conn = null;
        ResultSet rs = null;
        CallableStatement CStmt = null;
        try
        {
            // Create a Callable Statement object.
            sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (sbr_db_conn == null)
                m_servlet.ErrorLogin(m_classReq, m_classRes);
            else
            {
                CStmt = sbr_db_conn.prepareCall("{call SBREXT.SBREXT_CDE_CURATOR_PKG.get_class_scheme_list(?,?)}");
                // Now tie the placeholders for out parameters.
                CStmt.registerOutParameter(2, OracleTypes.CURSOR);
                // Now tie the placeholders for In parameters.
                CStmt.setString(1, sContext);
                // Now we are ready to call the stored procedure
                CStmt.execute();
                // store the output in the resultset
                rs = (ResultSet) CStmt.getObject(2);
                if (rs != null)
                {
                    // loop through the resultSet and add them to the bean
                    while (rs.next())
                    {
                        String csID = rs.getString("cs_idseq");
                        String csName = rs.getString("long_name");
                        String csContext = rs.getString("context_name");
                        if (csContext == null)
                            csContext = "";
                        String csVer = rs.getString("version");
                        if (csVer == null)
                            csVer = "";
                        if (!csVer.equals("") && csVer.indexOf('.') < 0)
                            csVer += ".0";
                        csName = m_util.removeNewLineChar(csName);
                        csName = m_util.parsedStringDoubleQuoteJSP(csName);
                                                                            // names
                        vList.addElement(csName + " - " + csContext + " - v" + csVer);
                        vIDList.addElement(csID);
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.fatal("ERROR - GetACService getCSList for other : " + e.toString(), e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (CStmt != null)
                CStmt.close();
            if (sbr_db_conn != null)
                sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            logger.fatal("ERROR - GetACService getCSList for close : " + ee.toString(), ee);
        }
    }

    /**
     * To get List for Class Scheme Items from database called from ?. Gets the attributes from Cs_csi table adds them
     * to the bean then to vector in the session.
     * 
     * calls oracle stored procedure "{call SBREXT_CDE_CURATOR_PKG.get_cscsi_list(OracleTypes.CURSOR)}"
     * 
     * loop through the ResultSet and add them to bean which is added to the vector to return
     * 
     */
    private void getCSCSIListBean()
    {
        ResultSet rs = null;
        CallableStatement CStmt = null;
        Vector<CSI_Bean> vList = new Vector<CSI_Bean>();
        Connection m_sbr_db_conn = null;
        try
        {
            // Create a Callable Statement object.
            m_sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (m_sbr_db_conn == null)
                m_servlet.ErrorLogin(m_classReq, m_classRes);
            else
            {
                CStmt = m_sbr_db_conn.prepareCall("{call SBREXT.SBREXT_CDE_CURATOR_PKG.GET_CSCSI_LIST(?)}");
                CStmt.registerOutParameter(1, OracleTypes.CURSOR);
                // Now we are ready to call the stored procedure
                CStmt.execute();
                // store the output in the resultset
                rs = (ResultSet) CStmt.getObject(1);
                if (rs != null)
                {
                    // loop through the resultSet and add them to the bean
                    while (rs.next())
                    {
                        CSI_Bean CSIBean = new CSI_Bean();
                        CSIBean.setCSI_CS_IDSEQ(rs.getString("cs_idseq"));
                        CSIBean.setCSI_CSI_IDSEQ(rs.getString("csi_idseq"));
                        CSIBean.setCSI_CSCSI_IDSEQ(rs.getString("cs_csi_idseq"));
                        CSIBean.setP_CSCSI_IDSEQ(rs.getString("p_cs_csi_idseq"));
                        CSIBean.setCSI_DISPLAY_ORDER(rs.getString("display_order"));
                        CSIBean.setCSI_LABEL(rs.getString("label"));
                        String csName = rs.getString("cs_name");
                        csName = m_util.removeNewLineChar(csName);
                        csName = m_util.parsedStringDoubleQuote(csName);
                        CSIBean.setCSI_CS_NAME(csName);
                        CSIBean.setCSI_CS_LONG_NAME(csName);
                        String csiName = rs.getString("csi_name");
                        csiName = m_util.removeNewLineChar(csiName);
                        csiName = m_util.parsedStringDoubleQuote(csiName);
                        CSIBean.setCSI_NAME(csiName);
                        CSIBean.setCSI_LEVEL(rs.getString("lvl"));
                        vList.addElement(CSIBean);
                    }
                }
            }
            HttpSession session = m_classReq.getSession();
            session.setAttribute("CSCSIList", vList);
        }
        catch (Exception e)
        {
            // System.err.println("other problem in GetACService-getCSCSIList: " + e);
            logger.fatal("ERROR - GetACService-getCSCSIListBean for other : " + e.toString(), e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (CStmt != null)
                CStmt.close();
            if (m_sbr_db_conn != null)
                m_sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            // System.err.println("Problem closing in GetACService-getCSCSIList: " + ee);
            logger.fatal("ERROR - GetACService-getCSCSIListBean for close : " + ee.toString(), ee);
        }
    }

    /**
     * The getLanguageList method queries the db for a list of Languages, stored in a vector. Calls the stored
     * procedure: SBREXT_CDE_CURATOR_PKG.get_languages_list(?)
     * 
     * @param vList
     *            A Vector of Language names.
     * 
     */
    private void getLanguageList(Vector<String> vList)
    {
        try
        {
            String sAPI = "{call SBREXT_CDE_CURATOR_PKG.get_languages_list(?)}";
            getDataListStoreProcedure(vList, null, null, null, sAPI, "", "", 1);
        }
        catch (Exception e)
        {
            // System.err.println("Problem getLanguageList: " + e);
            logger.fatal("ERROR in GetACService-getLanguageList : " + e.toString(), e);
        }
    }

    /**
     * The getDataTypesList method queries the db for a list of Data Types, stored in a vector. Calls the stored
     * procedure: SBREXT_CDE_CURATOR_PKG.get_datatypes_list(?)
     * 
     * @param vList
     *            A Vector of Data Types names.
     * @param vDesc
     *            vector of description attributes
     * @param vComment
     *            vector of comment attributes
     * 
     */
    private void getDataTypesList(Vector<String> vList, Vector<String> vDesc, Vector<String> vComment)
    {
        try
        {
            String sAPI = "{call SBREXT_CDE_CURATOR_PKG.get_datatypes_list(?)}";
            getDataListStoreProcedure(vList, vDesc, vComment, null, sAPI, "", "", 1);
        }
        catch (Exception e)
        {
            // System.err.println("Problem getDataTypesList: " + e);
            logger.fatal("ERROR in GetACService-getDataTypesList : " + e.toString(), e);
        }
    }

    /**
     * The getRDocTypesList method queries the db for a list of RDoc Types, stored in a vector. Calls the stored
     * procedure: SBREXT_CDE_CURATOR_PKG.get_doc_types_list(?)
     * 
     * @param vList
     *            A Vector of Rerence Doc Types names.
     * @return vector of document tyeps
     * 
     */
    private Vector<String> getRDocTypesList(Vector<String> vList)
    {
        try
        {
            String sAPI = "{call SBREXT_CDE_CURATOR_PKG.get_doc_types_list(?)}";
            getDataListStoreProcedure(vList, null, null, null, sAPI, "", "", 1);
            // sort the doc types such that long name and hist short cde name is on the top
            if (vList != null)
            {
                // remove Preferred Question Text and historic short cde name from the list
                if (vList.contains("Preferred Question Text"))
                    vList.remove("Preferred Question Text");
                if (vList.contains("HISTORIC SHORT CDE NAME"))
                    vList.remove("HISTORIC SHORT CDE NAME");
                // add Preferred Question Text and historic short cde name on the top
                if (!vList.contains("Preferred Question Text"))
                    vList.insertElementAt("Preferred Question Text", 0);
                if (!vList.contains("HISTORIC SHORT CDE NAME"))
                    vList.insertElementAt("HISTORIC SHORT CDE NAME", 1);
            }
        }
        catch (Exception e)
        {
            // System.err.println("Problem getRDocTypesList: " + e);
            logger.fatal("ERROR in GetACService-getRDocTypesList : " + e.toString(), e);
        }
        return vList;
    }

    /**
     * The getUOMList method queries the db for a list of Units Of Measure, stored in a vector. Calls the stored
     * procedure: SBREXT_CDE_CURATOR_PKG.get_unit_of_measures_list(?)
     * 
     * @param vList
     *            A Vector of UOM names.
     * 
     */
    private void getUOMList(Vector<String> vList)
    {
        try
        {
            String sAPI = "{call SBREXT_CDE_CURATOR_PKG.get_unit_of_measures_list(?)}";
            getDataListStoreProcedure(vList, null, null, null, sAPI, "", "", 1);
        }
        catch (Exception e)
        {
            // System.err.println("Problem getUOMList: " + e);
            logger.fatal("ERROR in GetACService-getUOMList : " + e.toString(), e);
        }
    }

    /**
     * The getUOMFormatList method queries the db for a list of Units Of Measure Formats, stored in a vector. Calls the
     * stored procedure: SBREXT_CDE_CURATOR_PKG.get_formats_list(?)
     * 
     * @param vList
     *            A Vector of UOM Format names.
     * 
     */
    private void getUOMFormatList(Vector<String> vList)
    {
        try
        {
            String sAPI = "{call SBREXT_CDE_CURATOR_PKG.get_formats_list(?)}";
            getDataListStoreProcedure(vList, null, null, null, sAPI, "", "", 1);
        }
        catch (Exception e)
        {
            // System.err.println("Problem getFormatList: " + e);
            logger.fatal("ERROR in GetACService-getFormatList : " + e.toString(), e);
        }
    }

    /**
     * The getSourceList method queries the db for a list of Sources, stored in a vector. Calls the stored procedure:
     * SBREXT_SS_API.get_source_list(?)
     * 
     * @param vList
     *            A Vector of Source names.
     * 
     */
    private void getSourceList(Vector<String> vList)
    {
        try
        {
            String sAPI = "{call SBREXT_SS_API.get_source_list(?)}";
            getDataListStoreProcedure(vList, null, null, null, sAPI, "", "", 1);
            for (int i = 0; i < vList.size(); i++)
            {
                String src = (String) vList.elementAt(i);
                src = m_util.removeNewLineChar(src);
                src = m_util.parsedStringDoubleQuote(src);
                vList.setElementAt(src, i);
            }
        }
        catch (Exception e)
        {
            // System.err.println("Problem getSourceList: " + e);
            logger.fatal("ERROR in GetACService-getSourceList : " + e.toString(), e);
        }
    }

    /**
     * The getRegStatusList method queries the db for a list of Sources, stored in a vector. Calls the stored procedure:
     * SBREXT_SS_API.get_reg_status_list(?)
     * 
     * @param vList
     *            A Vector of Registration Status names.
     * 
     */
    private void getRegStatusList(Vector<String> vList)
    {
        try
        {
            String sAPI = "{call SBREXT_SS_API.get_reg_status_list(?)}";
            getDataListStoreProcedure(vList, null, null, null, sAPI, "", "", 1);
        }
        catch (Exception e)
        {
            // System.err.println("Problem getRegStatusList: " + e);
            logger.fatal("ERROR in GetACService-getRegStatusList : " + e.toString(), e);
        }
    }

    /**
     * The hasPrivilege will check if the user has Database privilege to make changes
     * 
     * @param DBAction
     *            The db action (wrie or update0
     * @param DBUser
     *            The username
     * @param ACType
     *            The type of page being called, i.e. de, dec, or vd
     * @param ContID
     *            The context ID
     * @return string yes no value for privilege
     * 
     */
    public String hasPrivilege(String DBAction, String DBUser, String ACType, String ContID)
    {
        ResultSet rs = null;
        Statement CStmt = null;
        String sPrivilege = "";
        Connection m_sbr_db_conn = null;
        try
        {
            DBUser = DBUser.toUpperCase();
            if (ACType.equals("de"))
                ACType = "DATAELEMENT";
            else if (ACType.equals("dec"))
                ACType = "DE_CONCEPT";
            else if (ACType.equals("vd"))
                ACType = "VALUEDOMAIN";
            String sql;
            if (DBAction.equals("Create") == true)
                sql = "SELECT ADMIN_SECURITY_UTIL.HAS_CREATE_PRIVILEGE('" + DBUser + "', '" + ACType + "', '" + ContID
                                + "') FROM DUAL";
            else
                sql = "SELECT ADMIN_SECURITY_UTIL.HAS_ADMIN_PRIVILEGE('" + DBUser + "', '" + ACType + "', '" + ContID
                                + "') FROM DUAL";
            // Create a Callable Statement object.
            m_sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (m_sbr_db_conn == null)
                m_servlet.ErrorLogin(m_classReq, m_classRes);
            else
            {
                CStmt = m_sbr_db_conn.createStatement();
                rs = CStmt.executeQuery(sql);
                String s;
                // loop through to printout the outstrings
                while (rs.next())
                {
                    s = "";
                    s = rs.getString(1);
                    if (s != null)
                    {
                        sPrivilege = s;
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            // System.err.println("ERROR in GetACService- hasPrivilege: " + e);
            logger.fatal("ERROR in GetACService-hasPrivilege for other : " + e.toString(), e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (CStmt != null)
                CStmt.close();
            if (m_sbr_db_conn != null)
                m_sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            // System.err.println("Problem closing: " + ee);
            logger.fatal("ERROR in GetACService-hasPrivilege for close : " + ee.toString(), ee);
        }
        return sPrivilege;
    }

    /**
     * The getDataListSQL returns a data list, given a sql statement
     * 
     * @param sSQL
     *            The sql statement to execute.
     * 
     */
    private Vector<String> getDataListSQL(String sSQL)
    {
        Vector<String> vList = new Vector<String>();
        ResultSet rs = null;
        Statement CStmt = null;
        Connection m_sbr_db_conn = null;
        try
        {
            m_sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (m_sbr_db_conn == null)
                m_servlet.ErrorLogin(m_classReq, m_classRes);
            else
            {
                CStmt = m_sbr_db_conn.createStatement();
                rs = CStmt.executeQuery(sSQL);
                String sName = "";
                // loop through to printout the outstrings
                while (rs.next())
                {
                    sName = rs.getString(1);
                    if (sName != null)
                    {
                        if (sName.length() > 80)
                            sName = sName.substring(0, 80);
                        vList.addElement(sName);
                    }
                }
            }
        }
        catch (Exception e)
        {
            // System.err.println("Error getDataListSQL: " + e);
            logger.fatal("ERROR in GetACService-getDataListSQL for other : " + e.toString(), e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (CStmt != null)
                CStmt.close();
            if (m_sbr_db_conn != null)
                m_sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            // System.err.println("Problem closing: " + ee);
            logger.fatal("ERROR in GetACService-getDataListSQL for close : " + ee.toString(), ee);
        }
        
        return vList;
    }

    /**
     * The getDataListStoreProcedure returns four data lists.
     * 
     * @param vList1
     *            A Vector of the ID's.
     * @param vList2
     *            A second Vector of names.
     * @param vList3
     *            A third Vector of names.
     * @param vList4
     *            A fourth Vector of names.
     * @param sAPI
     *            The API to call.
     * @param setString1
     *            Sets an API in parameter.
     * @param setString2
     *            Sets an API in parameter.
     * @param iParmIdx
     *            Parameter ID.
     * 
     */
    private void getDataListStoreProcedure(Vector<String> vList1, Vector<String> vList2, Vector<String> vList3,
                    Vector<String> vList4, String sAPI, String setString1, String setString2, int iParmIdx)
    {
        /*
         * Remember: Vector parameter represent the recordset's parameter numbers.
         */
        ResultSet rs = null;
        CallableStatement CStmt = null;
        boolean isReConnect = false;
        Connection m_sbr_db_conn = null;
        try
        {
            // Create a Callable Statement object.
            m_sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (m_sbr_db_conn == null)
                m_servlet.ErrorLogin(m_classReq, m_classRes);
            else
            {
                isReConnect = true;
                CStmt = m_sbr_db_conn.prepareCall(sAPI);
                // Now tie the placeholders with actual parameters.
                if (iParmIdx == 1)
                {
                    CStmt.registerOutParameter(1, OracleTypes.CURSOR);
                    // Now we are ready to call the stored procedure
                    CStmt.execute();
                    rs = (ResultSet) CStmt.getObject(1);
                }
                else if (iParmIdx == 2)
                {
                    CStmt.registerOutParameter(2, OracleTypes.CURSOR);
                    CStmt.setString(1, setString1);
                    // Now we are ready to call the stored procedure
                    CStmt.execute();
                    rs = (ResultSet) CStmt.getObject(2);
                }
                else if (iParmIdx == 3)
                {
                    CStmt.registerOutParameter(3, OracleTypes.CURSOR);
                    CStmt.setString(1, setString1);
                    CStmt.setString(2, setString2);
                    // Now we are ready to call the stored procedure
                    CStmt.execute();
                    rs = (ResultSet) CStmt.getObject(3);
                }
                String sName = "";
                // loop through to printout the outstrings
                if ((vList1 != null) && (vList2 != null) && (vList3 != null) && (vList4 != null))
                {
                    while (rs.next())
                    {
                        vList1.addElement(rs.getString(1));
                        vList2.addElement(rs.getString(2));
                        sName = rs.getString(3);
                        if (sName == null)
                            sName = "";
                        // if(sName.length() > 80)
                        // sName = sName.substring(0, 80);
                        vList3.addElement(sName);
                        vList4.addElement(rs.getString(4));
                    }
                }
                else if ((vList1 != null) && (vList2 != null) && (vList3 != null))
                {
                    while (rs.next())
                    {
                        vList1.addElement(rs.getString(1));
                        vList2.addElement(rs.getString(2));
                        sName = rs.getString(3);
                        if (sName == null)
                            sName = "";
                        // if(sName.length() > 80)
                        // sName = sName.substring(0, 80);
                        vList3.addElement(sName);
                    }
                }
                else if ((vList1 != null) && (vList2 != null))
                {
                    while (rs.next())
                    {
                        vList1.addElement(rs.getString(1));
                        sName = rs.getString(2);
                        if (sName == null)
                            sName = "";
                        // if(sName != null)
                        // {
                        // if(sName.length() > 80)
                        // sName = sName.substring(0, 80);
                        // }
                        // else
                        // sName = "";
                        vList2.addElement(sName);
                    }
                }
                else
                {
                    while (rs.next())
                    {
                        sName = rs.getString(1);
                        if (sName == null)
                            sName = "";
                        vList1.addElement(sName);
                    }
                }
            }
        }
        catch (Exception e)
        {
            // System.err.println("ERROR in GetACService-getDataListStoreProcedure : " + e);
            logger.fatal("ERROR in GetACService-getDataListStoreProcedure for other : " + e.toString(), e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (CStmt != null)
                CStmt.close();
            // close is if reconnected
            if (isReConnect && m_sbr_db_conn != null)
                m_sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            // System.err.println("Problem closing: " + ee);
            logger.fatal("ERROR in GetACService-getDataListStoreProcedure for close : " + ee.toString(), ee);
        }
    }

    /**
     * The doComponentExist method queries the db, checking whether component exists.
     * 
     * @param sSQL
     *            A sql statement
     * 
     * @return Boolean flag indicating whether component exists.
     */
    public boolean doComponentExist(String sSQL)
    {
        ResultSet rs = null;
        Statement CStmt = null;
        boolean isExist = false;
        Connection m_sbr_db_conn = null;
        try
        {
            m_sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (m_sbr_db_conn == null)
                m_servlet.ErrorLogin(m_classReq, m_classRes);
            else
            {
                CStmt = m_sbr_db_conn.createStatement();
                rs = CStmt.executeQuery(sSQL);
                int iCount = 0;
                // loop through to printout the outstrings
                while (rs.next())
                {
                    iCount = rs.getInt(1);
                }
                if (iCount > 0)
                    isExist = true;
            }
        }
        catch (Exception e)
        {
            // System.err.println("ERROR in doComponentExist: " + e);
            logger.fatal("ERROR in doComponentExist : " + e.toString(), e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (CStmt != null)
                CStmt.close();
            if (m_sbr_db_conn != null)
                m_sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            // System.err.println("Problem closing in doComponentExist: " + ee);
            logger.fatal("ERROR in doComponentExist closing : " + ee.toString(), ee);
        }
        return isExist;
    }

    /**
     * The isUniqueInContext method queries the db, checking whether DE is unique in context.
     * 
     * @param sSQL
     *            A sql statement
     * 
     * @return String Long Name.
     */
    public String isUniqueInContext(String sSQL)
    {
        ResultSet rs = null;
        Statement CStmt = null;
        String sName = "";
        Connection m_sbr_db_conn = null;
        try
        {
            m_sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (m_sbr_db_conn == null)
                m_servlet.ErrorLogin(m_classReq, m_classRes);
            else
            {
                CStmt = m_sbr_db_conn.createStatement();
                rs = CStmt.executeQuery(sSQL);
                int i = 0;
                // loop through to printout the outstrings
                while (rs.next())
                {
                    if (i == 0)
                        sName = rs.getString(1);
                    else
                        sName = sName + ", " + rs.getString(1);
                    i++;
                }
            }
        }
        catch (Exception e)
        {
            // System.err.println("ERROR in isUniqueInContext: " + e);
            logger.fatal("ERROR in isUniqueInContext : " + e.toString(), e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (CStmt != null)
                CStmt.close();
            if (m_sbr_db_conn != null)
                m_sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            // System.err.println("Problem closing in isUniqueInContext: " + ee);
            logger.fatal("ERROR in isUniqueInContext closing : " + ee.toString(), ee);
        }
        return sName;
    }

    /**
     * Called to get all the concepts from condr_idseq. Sets in parameters, and registers output parameters and vector
     * of evs bean. Calls oracle stored procedure "{call SBREXT_CDE_CURATOR_PKG.GET_AC_CONCEPTS(?,?)}" to submit
     * 
     * @param condrID
     *            String condr idseq
     * @param vd
     *            VD_Bean object
     * @param bInvertBean
     *            boolean to invert the bean vector or not
     * @return Vector vector of evs bean.
     */
    public Vector<EVS_Bean> getAC_Concepts(String condrID, VD_Bean vd, boolean bInvertBean)
    {
        // System.err.println("in getAC_Concepts condrID: " + condrID);
        Connection sbr_db_conn = null;
        CallableStatement CStmt = null;
        ResultSet rs = null;
        Vector<EVS_Bean> vList = new Vector<EVS_Bean>();
        GetACSearch serAC = new GetACSearch(m_classReq, m_classRes, m_servlet);
        try
        {
            // Create a Callable Statement object.
            sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (sbr_db_conn == null)
                m_servlet.ErrorLogin(m_classReq, m_classRes);
            else
            {
                CStmt = sbr_db_conn.prepareCall("{call SBREXT_CDE_CURATOR_PKG.GET_AC_CONCEPTS(?,?)}");
                // out parameter
                CStmt.registerOutParameter(2, OracleTypes.CURSOR);
                // in parameter
                CStmt.setString(1, condrID);
                // Now we are ready to call the stored procedure
                CStmt.execute();
                // store the output in the resultset
                rs = (ResultSet) CStmt.getObject(2);
                if (rs != null)
                {
                    // loop through the resultSet and add them to the bean
                    while (rs.next())
                    {
                        EVS_Bean eBean = new EVS_Bean();
                        eBean.setIDSEQ(rs.getString("CON_IDSEQ"));
                        // System.err.println("in getAC_Concepts CON_IDSEQ: " + rs.getString("CON_IDSEQ"));
                        eBean.setDISPLAY_ORDER(rs.getString("DISPLAY_ORDER"));
                        eBean.setPRIMARY_FLAG(rs.getString("primary_flag_ind"));
                        eBean.setCONCEPT_IDENTIFIER(rs.getString("preferred_name"));
                        eBean.setLONG_NAME(rs.getString("long_name"));
                        // System.err.println("in getAC_Concepts long name: " + rs.getString("long_name"));
                        // eBean.setDESCRIPTION(rs.getString("preferred_definition"));
                        eBean.setPREFERRED_DEFINITION(rs.getString("preferred_definition"));
                        eBean.setEVS_DATABASE(rs.getString("origin"));
                        // System.err.println("in getAC_Concepts origin: " + rs.getString("origin"));
                        eBean.setEVS_DEF_SOURCE(rs.getString("definition_source"));
                        eBean.setNCI_CC_TYPE(rs.getString("evs_source"));
                        eBean.setNVP_CONCEPT_VALUE(rs.getString("CONCEPT_VALUE"));
                        if (!eBean.getNVP_CONCEPT_VALUE().equals(""))
                        {
                            eBean.setLONG_NAME(eBean.getLONG_NAME() + "::" + eBean.getNVP_CONCEPT_VALUE());
                            eBean.setPREFERRED_DEFINITION(eBean.getPREFERRED_DEFINITION() + "::"
                                            + eBean.getNVP_CONCEPT_VALUE());
                        }
                        eBean.setCONDR_IDSEQ(condrID);
                        eBean.setCON_AC_SUBMIT_ACTION("NONE");
                        eBean.markNVPConcept(eBean, m_classReq.getSession());
                        if (rs.getString("origin") != null && rs.getString("origin").equals("NCI Metathesaurus"))
                        {
                            String sParent = rs.getString("long_name");
                            String sCui = rs.getString("preferred_name");
                            if (sParent == null)
                                sParent = "";
                            String sParentSource = "";
                            sParentSource = serAC.getMetaParentSource(sParent, sCui, vd);
                            if (sParentSource == null)
                                sParentSource = "";
                            eBean.setEVS_CONCEPT_SOURCE(sParentSource);
                        }
                        if (eBean.getIDSEQ() != null && !eBean.getIDSEQ().equals(""))
                            vList.addElement(eBean);
                    }
                }
                if (bInvertBean != false)
                    vList = invertBeanVector(vList);
            }
        }
        catch (Exception e)
        {
            logger.fatal("ERROR in GetACService- getACConcepts for exception : " + e.toString(), e);
            // System.out.println("get ac concept other " + e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (CStmt != null)
                CStmt.close();
            if (sbr_db_conn != null)
                sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            logger.fatal("ERROR in GetACService-getConcept for close : " + ee.toString(), ee);
        }
        return vList;
    }

    /**
     * Puts the primary concept at the top of the list, for internal use
     * 
     * @param vList
     *            Vector of evs bean. *
     * @return vList Vector of evs bean.
     */
    private Vector<EVS_Bean> invertBeanVector(Vector<EVS_Bean> vList)
    {
        Vector<EVS_Bean> newVector = new Vector<EVS_Bean>();
        EVS_Bean evsBean = (EVS_Bean) vList.elementAt(vList.size() - 1);
        newVector.addElement(evsBean);
        for (int i = 0; i < vList.size() - 1; i++)
        {
            evsBean = (EVS_Bean) vList.elementAt(i);
            newVector.addElement(evsBean);
        }
        return newVector;
    }

    /**
     * @param session
     *            HttpSession object
     */
    private void getAltNamesList(HttpSession session)
    {
        try
        {
            String sQuery = "select detl_name from sbr.designation_types_lov_view where detl_name not in ( "
                            + "select value from sbrext.tool_options_view_ext where property like 'EXCLUDE.DESIGNATION_TYPE.%' "
                            + ") order by upper(detl_name)";
            Vector<String> vT = getDataListSQL(sQuery);
            session.setAttribute("AltNameTypes", vT);
        }
        catch (Exception e)
        {
            logger.fatal("Error - getAltNamesList : " + e.toString(), e);
        }
    }

    /**
     * @param session
     *            HttpSession object
     */
    private void getRefDocsList(HttpSession session)
    {
        try
        {
            String sQuery = "select dctl_name from sbr.document_types_lov_view where dctl_name not in ( "
                            + "select value from sbrext.tool_options_view_ext where property like 'EXCLUDE.DOCUMENT_TYPE.%' "
                            + ") order by upper(dctl_name)";
            Vector<String> vT = getDataListSQL(sQuery);
            session.setAttribute("RefDocTypes", vT);
        }
        catch (Exception e)
        {
            logger.fatal("Error - getRefDocsList : " + e.toString(), e);
        }
    }

    /**
     * @param session
     */
    @SuppressWarnings("unchecked")
    private void getDerTypesList(HttpSession session)
    {
        try
        {
            Vector<String> vT = null;
            String select = null;

            vT = (Vector) session.getAttribute("allDerTypes");
            if (vT == null)
            {
                select = "select crtl_name from sbr.complex_rep_type_lov_view "
                    + "order by upper(crtl_name)";
                vT = getDataListSQL(select);
                session.setAttribute("allDerTypes", vT);
            }

            vT = (Vector) session.getAttribute("vRepType");
            if (vT == null)
            {
                select = "select crtl_name from sbr.complex_rep_type_lov_view where crtl_name in ( "
                    + "select value from sbrext.tool_options_view_ext where property like 'INCLUDE.DERIVATION_TYPE.%' "
                    + ") order by upper(crtl_name)";
                vT = getDataListSQL(select);
                session.setAttribute("vRepType", vT);
            }
        }
        catch (Exception e)
        {
            logger.fatal("Error - getDerTypesList : " + e.toString(), e);
        }
    }

    /**
     * @param db_
     * @param tool_
     * @param prop_
     * @param value_
     * @return Vector of tool option bean
     */
    public Vector<TOOL_OPTION_Bean> getToolOptionData(Connection db_, String tool_, String prop_, String value_)
    {
        ResultSet rs = null;
        CallableStatement CStmt = null;
        Vector<TOOL_OPTION_Bean> vList = new Vector<TOOL_OPTION_Bean>();
        try
        {
            CStmt = db_.prepareCall("{call SBREXT_CDE_CURATOR_PKG.SEARCH_TOOL_OPTIONS(?,?,?,?,?)}");
            CStmt.registerOutParameter(4, OracleTypes.CURSOR);
            CStmt.registerOutParameter(5, OracleTypes.VARCHAR);
            CStmt.setString(1, tool_);
            CStmt.setString(2, prop_);
            CStmt.setString(3, value_);
            // Now we are ready to call the stored procedure
            CStmt.execute();
            // store the output in the result set
            rs = (ResultSet) CStmt.getObject(4);
            if (rs != null)
            {
                // loop through the resultSet and add them to the bean
                while (rs.next())
                {
                    TOOL_OPTION_Bean TO_Bean = new TOOL_OPTION_Bean();
                    TO_Bean.setTOOL_OPTION_IDSEQ(rs.getString("TOOL_IDSEQ"));
                    TO_Bean.setTOOL_NAME(rs.getString("TOOL_NAME"));
                    TO_Bean.setPROPERTY(rs.getString("PROPERTY"));
                    TO_Bean.setVALUE(rs.getString("VALUE"));
                    TO_Bean.setLANGUAGE(rs.getString("UA_NAME"));
                    vList.addElement(TO_Bean);
                }
            }
        }
        catch (Exception e)
        {
            // System.err.println("other problem in GetACService-getToolOptionData: " + e);
            logger.fatal("ERROR - GetACService-getToolOptionData for other : " + e.toString(), e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (CStmt != null)
                CStmt.close();
        }
        catch (SQLException ee)
        {
            // System.err.println("Problem closing in GetACService-getToolOptionData: " + ee);
            logger.fatal("ERROR - GetACService-getToolOptionData for close : " + ee.toString(), ee);
        }
        return vList;
    }

    /**
     * to get the data from tool option table
     * 
     * @param toolName
     *            name of the tool to filter
     * @param sProperty
     *            name of the property to filter
     * @param sValue
     *            name of the value to filter
     * @return vector of TOOL_OPTION_Bean objects
     */
    public Vector<TOOL_OPTION_Bean> getToolOptionData(String toolName, String sProperty, String sValue)
    {
        Vector<TOOL_OPTION_Bean> vList = new Vector<TOOL_OPTION_Bean>();
        Connection sbr_db_conn = null;
        sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
        if (sbr_db_conn == null)
            m_servlet.ErrorLogin(m_classReq, m_classRes);
        else
        {
            vList = getToolOptionData(sbr_db_conn, toolName, sProperty, sValue);
            try
            {
                sbr_db_conn.close();
            }
            catch (SQLException ex)
            {
                logger.fatal(ex.toString());
            }
        }
        return vList;
    }

    private Hashtable getHashListFromAPI(String sAPI)
    {
        Hashtable<String, String> hRes = new Hashtable<String, String>();
        Connection sbr_db_conn = null;
        CallableStatement CStmt = null;
        ResultSet rs = null;
        try
        {
            // Create a Callable Statement object.
            sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (sbr_db_conn == null)
                m_servlet.ErrorLogin(m_classReq, m_classRes);
            else
            {
                CStmt = sbr_db_conn.prepareCall(sAPI);
                // out parameter
                CStmt.registerOutParameter(1, OracleTypes.CURSOR);
                // Now we are ready to call the stored procedure
                CStmt.execute();
                // store the output in the resultset
                rs = (ResultSet) CStmt.getObject(1);
                if (rs != null)
                {
                    // loop through the resultSet and add them to the bean
                    while (rs.next())
                    {
                        String sID = rs.getString(1);
                        String sName = rs.getString(2);
                        if (sName == null)
                            sName = sID;
                        hRes.put(sID, sName);
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.fatal("ERROR in GetACService- getHashListFromAPI for exception : " + sAPI + " : " + e.toString(), e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (CStmt != null)
                CStmt.close();
            if (sbr_db_conn != null)
                sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            logger.fatal("ERROR in GetACService-getHashListFromAPI for close : " + ee.toString(), ee);
        }
        return hRes;
    }

    /**
     * gets the organization list and stores it in the session
     */
    private void getOrganizeList()
    {
        String sAPI = "{call SBREXT_CDE_CURATOR_PKG.GET_ORGANIZATION_LIST(?)}";
        Hashtable hOrg = this.getHashListFromAPI(sAPI);
        HttpSession session = (HttpSession) m_classReq.getSession();
        session.setAttribute("Organizations", hOrg);
    }

    /**
     * gets the contact roles list and stores in the session
     */
    private void getContactRolesList()
    {
        String sAPI = "{call SBREXT.SBREXT_CDE_CURATOR_PKG.GET_CONTACT_ROLES_LIST(?)}";
        Hashtable hOrg = this.getHashListFromAPI(sAPI);
        HttpSession session = (HttpSession) m_classReq.getSession();
        session.setAttribute("ContactRoles", hOrg);
        // System.out.println("get contact roles " + hOrg.size());
    }

    /**
     * gets the communication list and stores in the session
     */
    private void getCommTypeList()
    {
        String sAPI = "{call SBREXT_CDE_CURATOR_PKG.GET_COMM_TYPE_LIST(?)}";
        Hashtable hOrg = this.getHashListFromAPI(sAPI);
        HttpSession session = (HttpSession) m_classReq.getSession();
        session.setAttribute("CommType", hOrg);
    }

    /**
     * gets the address types list and stores in the session
     */
    private void getAddrTypeList()
    {
        String sAPI = "{call SBREXT_CDE_CURATOR_PKG.GET_ADDR_TYPE_LIST(?)}";
        Hashtable hOrg = this.getHashListFromAPI(sAPI);
        HttpSession session = (HttpSession) m_classReq.getSession();
        session.setAttribute("AddrType", hOrg);
    }

    /**
     * gets the persons list and stores in the session
     */
    private void getPersonsList()
    {
        Hashtable<String, String> hPer = new Hashtable<String, String>();
        Connection sbr_db_conn = null;
        CallableStatement CStmt = null;
        ResultSet rs = null;
        try
        {
            // Create a Callable Statement object.
            sbr_db_conn = m_servlet.connectDB(m_classReq, m_classRes);
            if (sbr_db_conn == null)
                m_servlet.ErrorLogin(m_classReq, m_classRes);
            else
            {
                CStmt = sbr_db_conn.prepareCall("{call SBREXT_CDE_CURATOR_PKG.GET_PERSONS_LIST(?)}");
                // out parameter
                CStmt.registerOutParameter(1, OracleTypes.CURSOR);
                // Now we are ready to call the stored procedure
                CStmt.execute();
                // store the output in the resultset
                rs = (ResultSet) CStmt.getObject(1);
                if (rs != null)
                {
                    // loop through the resultSet and add them to the bean
                    while (rs.next())
                    {
                        String sID = rs.getString("per_idseq");
                        String lName = rs.getString("lname");
                        String fName = rs.getString("fname");
                        String sName = "";
                        // append the last name and first name together
                        if (lName != null && !lName.equals(""))
                            sName = lName;
                        if (!sName.equals("") && fName != null && !fName.equals(""))
                            sName += ", ";
                        sName += fName;
                        hPer.put(sID, sName);
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.fatal("ERROR in GetACService- getPersonsList for exception : " + e.toString(), e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (CStmt != null)
                CStmt.close();
            if (sbr_db_conn != null)
                sbr_db_conn.close();
        }
        catch (Exception ee)
        {
            logger.fatal("ERROR in GetACService-getPersonsList for close : " + ee.toString(), ee);
        }
        // store it in the session attributes
        HttpSession session = (HttpSession) m_classReq.getSession();
        session.setAttribute("Persons", hPer);
    }

    /**
     * gets NVP concepts from tool options
     * 
     * @param session
     */
    private void getNVPConcepts(HttpSession session)
    {
        Vector<String> vList = new Vector<String>();
        Vector vTypes = this.getToolOptionData("CURATION", "NVPCONCEPT.%", "");
        if (vTypes != null && vTypes.size() > 0)
        {
            for (int i = 0; i < vTypes.size(); i++)
            {
                TOOL_OPTION_Bean tob = (TOOL_OPTION_Bean) vTypes.elementAt(i);
                if (tob != null)
                {
                    String sValue = tob.getVALUE();
                    vList.addElement(sValue);
                }
            }
        }
        session.setAttribute("NVPConcepts", vList);
    }
}
