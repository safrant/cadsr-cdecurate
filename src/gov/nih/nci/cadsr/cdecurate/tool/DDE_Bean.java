// Copyright (c) 2006 ScenPro, Inc.

// $Header: /cvsshare/content/cvsroot/cdecurate/src/gov/nih/nci/cadsr/cdecurate/tool/DDE_Bean.java,v 1.17 2006-11-16 05:55:00 hegdes Exp $
// $Name: not supported by cvs2svn $

package gov.nih.nci.cadsr.cdecurate.tool;
import java.io.Serializable;
import java.util.Vector;
/*
The CaCORE Software License, Version 3.0 Copyright 2002-2005 ScenPro, Inc. (�ScenPro�)  
Copyright Notice.  The software subject to this notice and license includes both
human readable source code form and machine readable, binary, object code form
(�the CaCORE Software�).  The CaCORE Software was developed in conjunction with
the National Cancer Institute (�NCI�) by NCI employees and employees of SCENPRO.
To the extent government employees are authors, any rights in such works shall
be subject to Title 17 of the United States Code, section 105.    
This CaCORE Software License (the �License�) is between NCI and You.  �You (or �Your�)
shall mean a person or an entity, and all other entities that control, are 
controlled by, or are under common control with the entity.  �Control� for purposes
of this definition means (i) the direct or indirect power to cause the direction
or management of such entity, whether by contract or otherwise, or (ii) ownership
of fifty percent (50%) or more of the outstanding shares, or (iii) beneficial 
ownership of such entity.  
This License is granted provided that You agree to the conditions described below.
NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up, no-charge,
irrevocable, transferable and royalty-free right and license in its rights in the
CaCORE Software to (i) use, install, access, operate, execute, copy, modify, 
translate, market, publicly display, publicly perform, and prepare derivative 
works of the CaCORE Software; (ii) distribute and have distributed to and by 
third parties the CaCORE Software and any modifications and derivative works 
thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to 
third parties, including the right to license such rights to further third parties.
For sake of clarity, and not by way of limitation, NCI shall have no right of 
accounting or right of payment from You or Your sublicensees for the rights 
granted under this License.  This License is granted at no charge to You.
1.  Your redistributions of the source code for the Software must retain the above
copyright notice, this list of conditions and the disclaimer and limitation of
liability of Article 6, below.  Your redistributions in object code form must
reproduce the above copyright notice, this list of conditions and the disclaimer
of Article 6 in the documentation and/or other materials provided with the 
distribution, if any.
2.  Your end-user documentation included with the redistribution, if any, must 
include the following acknowledgment: �This product includes software developed 
by SCENPRO and the National Cancer Institute.�  If You do not include such end-user
documentation, You shall include this acknowledgment in the Software itself, 
wherever such third-party acknowledgments normally appear.
3.  You may not use the names "The National Cancer Institute", "NCI" �ScenPro, Inc.�
and "SCENPRO" to endorse or promote products derived from this Software.  
This License does not authorize You to use any trademarks, service marks, trade names,
logos or product names of either NCI or SCENPRO, except as required to comply with
the terms of this License.
4.  For sake of clarity, and not by way of limitation, You may incorporate this
Software into Your proprietary programs and into any third party proprietary 
programs.  However, if You incorporate the Software into third party proprietary
programs, You agree that You are solely responsible for obtaining any permission
from such third parties required to incorporate the Software into such third party
proprietary programs and for informing Your sublicensees, including without 
limitation Your end-users, of their obligation to secure any required permissions
from such third parties before incorporating the Software into such third party
proprietary software programs.  In the event that You fail to obtain such permissions,
You agree to indemnify NCI for any claims against NCI by such third parties, 
except to the extent prohibited by law, resulting from Your failure to obtain
such permissions.
5.  For sake of clarity, and not by way of limitation, You may add Your own 
copyright statement to Your modifications and to the derivative works, and You 
may provide additional or different license terms and conditions in Your sublicenses
of modifications of the Software, or any derivative works of the Software as a 
whole, provided Your use, reproduction, and distribution of the Work otherwise 
complies with the conditions stated in this License.
6.  THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES,
(INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, 
NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  
IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SCENPRO, OR THEIR AFFILIATES 
BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT 
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/**
 * @author shegde
 *
 */
public class DDE_Bean implements Serializable
{
  private static final long serialVersionUID = -3849514050655223334L;
  //attributes
  private String DDE_IDSEQ;
  private String DE_IDSEQ;
  private String DE_NAME;
  private String DE_ID;
  private String DE_VERSION;
  private String CRTL_NAME;
  private String METHOD;
  private String RULE;
  private String CONCAT_CHAR;
  private String SUBMIT_ACTION; 
  private String RULES_ACTION; 
  private Vector DE_COMP_List;
  
  /**
   * construct the bean
   */
  public DDE_Bean()
  {
  }

  /**
   * @return Returns the cONCAT_CHAR.
   */
  public String getCONCAT_CHAR()
  {
    return CONCAT_CHAR;
  }

  /**
   * @param concat_char The cONCAT_CHAR to set.
   */
  public void setCONCAT_CHAR(String concat_char)
  {
    CONCAT_CHAR = concat_char;
  }

  /**
   * @return Returns the cRTL_NAME.
   */
  public String getCRTL_NAME()
  {
    return CRTL_NAME;
  }

  /**
   * @param crtl_name The cRTL_NAME to set.
   */
  public void setCRTL_NAME(String crtl_name)
  {
    CRTL_NAME = crtl_name;
  }

  /**
   * @return Returns the dDE_IDSEQ.
   */
  public String getDDE_IDSEQ()
  {
    return DDE_IDSEQ;
  }

  /**
   * @param dde_idseq The dDE_IDSEQ to set.
   */
  public void setDDE_IDSEQ(String dde_idseq)
  {
    DDE_IDSEQ = dde_idseq;
  }

  /**
   * @return Returns the dE_COMP_List.
   */
  public Vector getDE_COMP_List()
  {
    return DE_COMP_List;
  }

  /**
   * @param list The dE_COMP_List to set.
   */
  public void setDE_COMP_List(Vector list)
  {
    DE_COMP_List = list;
  }

  /**
   * @return Returns the dE_IDSEQ.
   */
  public String getDE_IDSEQ()
  {
    return DE_IDSEQ;
  }

  /**
   * @param de_idseq The dE_IDSEQ to set.
   */
  public void setDE_IDSEQ(String de_idseq)
  {
    DE_IDSEQ = de_idseq;
  }

  /**
   * @return Returns the dE_NAME.
   */
  public String getDE_NAME()
  {
    return DE_NAME;
  }

  /**
   * @param de_name The dE_NAME to set.
   */
  public void setDE_NAME(String de_name)
  {
    DE_NAME = de_name;
  }
  
  /**
   * @return Returns the dE_ID.
   */
  public String getDE_ID()
  {
    return DE_ID;
  }

  /**
   * @param de_id The dE_ID to set.
   */
  public void setDE_ID(String de_id)
  {
    DE_ID = de_id;
  }

  /**
   * @return Returns the dE_VERSION.
   */
  public String getDE_VERSION()
  {
    return DE_VERSION;
  }

  /**
   * @param de_version The dE_VERSION to set.
   */
  public void setDE_VERSION(String de_version)
  {
    DE_VERSION = de_version;
  }

  /**
   * @return Returns the mETHOD.
   */
  public String getMETHOD()
  {
    return METHOD;
  }

  /**
   * @param method The mETHOD to set.
   */
  public void setMETHOD(String method)
  {
    METHOD = method;
  }

  /**
   * @return Returns the rULE.
   */
  public String getRULE()
  {
    return RULE;
  }

  /**
   * @param rule The rULE to set.
   */
  public void setRULE(String rule)
  {
    RULE = rule;
  }

  /**
   * @return Returns the sUBMIT_ACTION.
   */
  public String getSUBMIT_ACTION()
  {
    return SUBMIT_ACTION;
  }

  /**
   * @param submit_action The sUBMIT_ACTION to set.
   */
  public void setSUBMIT_ACTION(String submit_action)
  {
    SUBMIT_ACTION = submit_action;
  }

  /**
   * @return Returns the rULES_ACTION.
   */
  public String getRULES_ACTION()
  {
    return RULES_ACTION;
  }

  /**
   * @param rules_action The rULES_ACTION to set.
   */
  public void setRULES_ACTION(String rules_action)
  {
    RULES_ACTION = rules_action;
  }
  

}
