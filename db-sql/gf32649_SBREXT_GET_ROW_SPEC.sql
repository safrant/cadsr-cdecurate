--------------------------------------------------------
--  File created - Thursday-April-04-2013   
--------------------------------------------------------
--------------------------------------------------------
--  Ref Constraints for Table AC_ATT_CSCSI_EXT
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package SBREXT_GET_ROW
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "SBREXT"."SBREXT_GET_ROW" IS
/******************************************************************************
   NAME:       SBREXT_GET_ROW
   PURPOSE:    This Package holds all the API's to get a single row of data
               from a table

   REVISIONS:
   Ver        Date        Author           Description
   ---------  ----------  ---------------  ------------------------------------
   1.0        10/18/2001  Prerna Aggarwal      1. Created this package



******************************************************************************/

 PROCEDURE GET_VD(
 P_RETURN_CODE	            OUT VARCHAR2
,P_VD_VD_IDSEQ	            IN OUT VARCHAR2
,P_VD_PREFERRED_NAME	    IN OUT VARCHAR2
,P_VD_CONTE_IDSEQ	        IN OUT VARCHAR2
,P_VD_VERSION	            IN OUT NUMBER
,P_VD_PREFERRED_DEFINITION  OUT VARCHAR2
,P_VD_LONG_NAME	            OUT VARCHAR2
,P_VD_ASL_NAME	            OUT VARCHAR2
,P_VD_CD_IDSEQ	            OUT	VARCHAR2
,P_VD_LATEST_VERSION_IND    OUT	VARCHAR2
,P_VD_TYPE_FLAG	            OUT	VARCHAR
,P_VD_DTL_NAME	            OUT	VARCHAR2
,P_VD_FORML_NAME 	        OUT VARCHAR2
,P_VD_UOML_NAME             OUT	VARCHAR2
,P_VD_LOW_VALUE_NUM	    	OUT	VARCHAR2
,P_VD_HIGH_VALUE_NUM	    OUT	VARCHAR2
,P_VD_MIN_LENGTH_NUM	    OUT	NUMBER
,P_VD_MAX_LENGTH_NUM    	OUT	NUMBER
,P_VD_DECIMAL_PLACE 	    OUT	NUMBER
,P_VD_CHAR_SET_NAME	        OUT	VARCHAR2
,P_VD_BEGIN_DATE	        OUT	VARCHAR2
,P_VD_END_DATE	            OUT	VARCHAR2
,P_VD_CHANGE_NOTE	        OUT	VARCHAR2
,P_VD_CREATED_BY	        OUT	VARCHAR2
,P_VD_DATE_CREATED	        OUT	VARCHAR2
,P_VD_MODIFIED_BY	        OUT	VARCHAR2
,P_VD_DATE_MODIFIED	        OUT	VARCHAR2
,P_VD_DELETED_IND	        OUT	VARCHAR2)	;

PROCEDURE GET_VD(
P_RETURN_CODE	            OUT VARCHAR2
,P_VD_VD_IDSEQ	            IN OUT VARCHAR2
,P_VD_PREFERRED_NAME	    IN OUT VARCHAR2
,P_VD_CONTE_IDSEQ	        IN OUT VARCHAR2
,P_VD_VERSION	            IN OUT NUMBER
,P_VD_PREFERRED_DEFINITION  OUT VARCHAR2
,P_VD_LONG_NAME	            OUT VARCHAR2
,P_VD_ASL_NAME	            OUT VARCHAR2
,P_VD_CD_IDSEQ	            OUT	VARCHAR2
,P_VD_LATEST_VERSION_IND    OUT	VARCHAR2
,P_VD_TYPE_FLAG	            OUT	VARCHAR
,P_VD_DTL_NAME	            OUT	VARCHAR2
,P_VD_FORML_NAME 	        OUT VARCHAR2
,P_VD_UOML_NAME             OUT	VARCHAR2
,P_VD_LOW_VALUE_NUM	    	OUT	VARCHAR2
,P_VD_HIGH_VALUE_NUM	    OUT	VARCHAR2
,P_VD_MIN_LENGTH_NUM	    OUT	NUMBER
,P_VD_MAX_LENGTH_NUM    	OUT	NUMBER
,P_VD_DECIMAL_PLACE 	    OUT	NUMBER
,P_VD_CHAR_SET_NAME	        OUT	VARCHAR2
,P_VD_BEGIN_DATE	        OUT	VARCHAR2
,P_VD_END_DATE	            OUT	VARCHAR2
,P_VD_CHANGE_NOTE	        OUT	VARCHAR2
,P_VD_CREATED_BY	        OUT	VARCHAR2
,P_VD_DATE_CREATED	        OUT	VARCHAR2
,P_VD_MODIFIED_BY	        OUT	VARCHAR2
,P_VD_DATE_MODIFIED	        OUT	VARCHAR2
,P_VD_DELETED_IND	        OUT	VARCHAR2
,P_VD_REP_IDSEQ        OUT	VARCHAR2
,P_VD_QUALIFIER	        OUT	VARCHAR2
,P_VD_ORIGIN	        OUT	VARCHAR2
,P_VD_CONDR_IDSEQ	        OUT	VARCHAR2);

PROCEDURE GET_CONTEXT(
 P_RETURN_CODE	            OUT VARCHAR2
,P_CT_CONTE_IDSEQ 			IN OUT VARCHAR2
,P_CT_NAME					IN OUT VARCHAR2
,P_CT_VERSION				IN OUT NUMBER
,P_CT_LL_NAME				OUT VARCHAR2
,P_CT_PAL_NAME				OUT VARCHAR2
,P_CT_DESCRIPTION			OUT VARCHAR2
,P_CT_LANGUAGE				OUT VARCHAR2
,P_CT_CREATED_BY			OUT VARCHAR2
,P_CT_DATE_CREATED			OUT VARCHAR2
,P_CT_MODIFIED_BY			OUT VARCHAR2
,P_CT_DATE_MODIFIED			OUT VARCHAR2)	;


PROCEDURE GET_CD(
 P_RETURN_CODE	            OUT VARCHAR2
,P_CD_CD_IDSEQ	            IN OUT VARCHAR2
,P_CD_PREFERRED_NAME	    IN OUT VARCHAR2
,P_CD_CT_IDSEQ	       		IN OUT VARCHAR2
,P_CD_VERSION	            IN OUT NUMBER
,P_CD_PREFERRED_DEFINITION  OUT VARCHAR2
,P_CD_LONG_NAME	            OUT VARCHAR2
,P_CD_ASL_NAME	            OUT VARCHAR2
,P_CD_DIMENSIONALITY        OUT	VARCHAR2
,P_CD_LATEST_VERSION_IND    OUT	VARCHAR2
,P_CD_BEGIN_DATE            OUT	VARCHAR2
,P_CD_END_DATE				OUT	VARCHAR2
,P_CD_CHANGE_NOTE	        OUT VARCHAR2
,P_CD_CREATED_BY	        OUT	VARCHAR2
,P_CD_DATE_CREATED	        OUT	VARCHAR2
,P_CD_MODIFIED_BY	        OUT	VARCHAR2
,P_CD_DATE_MODIFIED	        OUT	VARCHAR2
,P_CD_DELETED_IND	        OUT	VARCHAR2)	;


PROCEDURE GET_DEC(
 P_RETURN_CODE	               OUT VARCHAR2
,P_DEC_DEC_IDSEQ	       	   IN OUT VARCHAR2
,P_DEC_PREFERRED_NAME	       IN OUT VARCHAR2
,P_DEC_CONTE_IDSEQ	           IN OUT VARCHAR2
,P_DEC_VERSION	               IN OUT NUMBER
,P_DEC_PREFERRED_DEFINITION    OUT VARCHAR2
,P_DEC_LONG_NAME	           OUT VARCHAR2
,P_DEC_ASL_NAME	               OUT VARCHAR2
,P_DEC_CD_IDSEQ	               OUT	VARCHAR2
,P_DEC_LATEST_VERSION_IND      OUT	VARCHAR2
,P_DEC_PROPL_NAME              OUT	VARCHAR
,P_DEC_OCL_NAME	               OUT	VARCHAR2
,P_DEC_PROPERTY_QUALIFIER  	   OUT  VARCHAR2
,P_DEC_OBJ_CLASS_QUALIFIER 	   OUT	VARCHAR2
,P_DEC_BEGIN_DATE	           OUT	VARCHAR2
,P_DEC_END_DATE	               OUT	VARCHAR2
,P_DEC_CHANGE_NOTE	           OUT	VARCHAR2
,P_DEC_CREATED_BY	           OUT	VARCHAR2
,P_DEC_DATE_CREATED	           OUT	VARCHAR2
,P_DEC_MODIFIED_BY	           OUT	VARCHAR2
,P_DEC_DATE_MODIFIED	        OUT	VARCHAR2
,P_DEC_DELETED_IND	        	OUT	VARCHAR2)	;


PROCEDURE GET_CON(
 P_RETURN_CODE	               OUT VARCHAR2
,P_CON_CON_IDSEQ	       	   IN OUT VARCHAR2
,P_CON_PREFERRED_NAME	       IN OUT VARCHAR2
,P_CON_CONTE_IDSEQ	            OUT VARCHAR2
,P_CON_VERSION	               IN OUT NUMBER
,P_CON_PREFERRED_DEFINITION    OUT VARCHAR2
,P_CON_LONG_NAME	           OUT VARCHAR2
,P_CON_ASL_NAME	               OUT VARCHAR2
,P_CON_DEFINITION_SOURCE	               OUT	VARCHAR2
,P_CON_LATEST_VERSION_IND      OUT	VARCHAR2
,P_CON_EVS_SOURCE              OUT	VARCHAR2
,P_CON_CON_ID  	   OUT  VARCHAR2
,P_CON_ORIGIN 	   OUT	VARCHAR2
,P_CON_BEGIN_DATE	           OUT	VARCHAR2
,P_CON_END_DATE	               OUT	VARCHAR2
,P_CON_CHANGE_NOTE	           OUT	VARCHAR2
,P_CON_CREATED_BY	           OUT	VARCHAR2
,P_CON_DATE_CREATED	           OUT	VARCHAR2
,P_CON_MODIFIED_BY	           OUT	VARCHAR2
,P_CON_DATE_MODIFIED	        OUT	VARCHAR2
,P_CON_DELETED_IND	        	OUT	VARCHAR2)	;

PROCEDURE GET_VM(
P_RETURN_CODE	            OUT VARCHAR2
,P_VM_SHORT_MEANING         IN OUT VARCHAR2
,P_VM_DESCRIPTION			OUT VARCHAR2
,P_VM_COMMENTS   	        OUT	VARCHAR2
,P_VM_BEGIN_DATE	        OUT	VARCHAR2
,P_VM_END_DATE	            OUT	VARCHAR2
,P_VM_CREATED_BY	        OUT	VARCHAR2
,P_VM_DATE_CREATED	        OUT	VARCHAR2
,P_VM_MODIFIED_BY	        OUT	VARCHAR2
,P_VM_DATE_MODIFIED	        OUT	VARCHAR2)	;


PROCEDURE GET_VM(
P_RETURN_CODE	            OUT VARCHAR2
,P_VM_SHORT_MEANING         IN OUT VARCHAR2
,P_VM_DESCRIPTION			OUT VARCHAR2
,P_VM_COMMENTS   	        OUT	VARCHAR2
,P_VM_BEGIN_DATE	        OUT	VARCHAR2
,P_VM_END_DATE	            OUT	VARCHAR2
,P_VM_CREATED_BY	        OUT	VARCHAR2
,P_VM_DATE_CREATED	        OUT	VARCHAR2
,P_VM_MODIFIED_BY	        OUT	VARCHAR2
,P_VM_DATE_MODIFIED	        OUT	VARCHAR2
,P_VM_CONDR_IDSEQ	        OUT	VARCHAR2
,P_VM_VM_IDSEQ              OUT VARCHAR2)	;


-- Added 4/24/2007 (ScenPro)

	PROCEDURE GET_VM(
	 P_RETURN_CODE	            OUT VARCHAR2
	,P_VM_IDSEQ					IN OUT VARCHAR2
	,P_LONG_NAME 				IN OUT VARCHAR2
	,P_VERSION					IN OUT VARCHAR2
	,P_VM_ID					IN OUT VARCHAR2
	,P_PREFERRED_NAME			OUT VARCHAR2
	,P_PREFERRED_DEFINITION		OUT VARCHAR2
	,P_CONTE_IDSEQ				OUT VARCHAR2
	,P_ASL_NAME					OUT VARCHAR2
	,P_LATEST_VERSION_IND	    OUT VARCHAR2
	,P_CONDR_IDSEQ				OUT VARCHAR2
	,P_DEFINITION_SOURCE		OUT VARCHAR2
	,P_ORIGIN					OUT VARCHAR2
	,P_CHANGE_NOTE	        	OUT VARCHAR2
	,P_BEGIN_DATE	        	OUT VARCHAR2
	,P_END_DATE	            	OUT VARCHAR2
	,P_CREATED_BY	        	OUT	VARCHAR2
	,P_DATE_CREATED	        	OUT	VARCHAR2
	,P_MODIFIED_BY	        	OUT	VARCHAR2
	,P_DATE_MODIFIED	        OUT	VARCHAR2
	)	;

	-- from ScenPro 4/24/2007
	PROCEDURE get_vm_condr(
	   p_con_array IN VARCHAR2
      ,p_return_code OUT VARCHAR2
      ,p_long_name IN OUT VARCHAR2
	  ,p_condr_idseq OUT VARCHAR2
	  ,p_preferred_definition OUT VARCHAR2
	  ,p_action OUT VARCHAR2
    ) ;



PROCEDURE GET_VM(
 P_RETURN_CODE	            OUT VARCHAR2
,P_VM_SHORT_MEANING         IN OUT VARCHAR2
,P_VM_DESCRIPTION			OUT VARCHAR2
,P_VM_COMMENTS   	        OUT	VARCHAR2
,P_VM_BEGIN_DATE	        OUT	VARCHAR2
,P_VM_END_DATE	            OUT	VARCHAR2
,P_VM_CREATED_BY	        OUT	VARCHAR2
,P_VM_DATE_CREATED	        OUT	VARCHAR2
,P_VM_MODIFIED_BY	        OUT	VARCHAR2
,P_VM_DATE_MODIFIED	        OUT	VARCHAR2
,P_VM_CONDR_IDSEQ           OUT VARCHAR2)	;

/*PROCEDURE GET_PV(
P_RETURN_CODE	 			OUT VARCHAR2
,P_PV_PV_IDSEQ              IN OUT VARCHAR2
,P_PV_VALUE                 IN OUT VARCHAR2
,P_PV_SHORT_MEANING         IN OUT VARCHAR2
,P_PV_MEANING_DESCRIPTION   OUT VARCHAR2
,P_PV_HIGH_VALUE_NUM        OUT NUMBER
,P_PV_LOW_VALUE_NUM			OUT NUMBER
,P_PV_BEGIN_DATE            OUT VARCHAR2
,P_PV_END_DATE              OUT VARCHAR2
,P_PV_CREATED_BY            OUT VARCHAR2
,P_PV_DATE_CREATED          OUT VARCHAR2
,P_PV_MODIFIED_BY           OUT VARCHAR2
,P_PV_DATE_MODIFIED         OUT VARCHAR2);*/

PROCEDURE GET_VD_PVS(
P_RETURN_CODE 		 		OUT VARCHAR2
,P_VP_VP_IDSEQ       			IN OUT VARCHAR2
,P_VP_VD_IDSEQ       			IN OUT VARCHAR2
,P_VP_PV_IDSEQ       			IN OUT VARCHAR2
,P_VP_CONTE_IDSEQ				OUT VARCHAR2
,P_VP_CREATED_BY     			OUT VARCHAR2
,P_VP_DATE_CREATED   			OUT VARCHAR2
,P_VP_MODIFIED_BY 				OUT VARCHAR2
,P_VP_DATE_MODIFIED				OUT VARCHAR2);


PROCEDURE GET_DE(
P_RETURN_CODE 		 		OUT VARCHAR2
,P_DE_DE_IDSEQ  			IN OUT VARCHAR2
,P_DE_PREFERRED_NAME		IN OUT VARCHAR2
,P_DE_CONTE_IDSEQ           IN OUT VARCHAR2
,P_DE_VERSION    			IN OUT VARCHAR2
,P_DE_PREFERRED_DEFINITION 	OUT VARCHAR2
,P_DE_LONG_NAME  			OUT VARCHAR2
,P_DE_ASL_NAME  			OUT VARCHAR2
,P_DE_DEC_IDSEQ 			OUT VARCHAR2
,P_DE_VD_IDSEQ 				OUT VARCHAR2
,P_DE_LATEST_VERSION_IND  	OUT VARCHAR2
,P_DE_BEGIN_DATE 			OUT VARCHAR2
,P_DE_END_DATE  			OUT VARCHAR2
,P_DE_CHANGE_NOTE 			OUT VARCHAR2
,P_DE_CREATED_BY  			OUT VARCHAR2
,P_DE_DATE_CREATED			OUT VARCHAR2
,P_DE_MODIFIED_BY 			OUT VARCHAR2
,P_DE_DATE_MODIFIED  		OUT VARCHAR2
,P_DE_DELETED_IND			OUT VARCHAR2);


PROCEDURE GET_ASL(
P_RETURN_CODE	            OUT VARCHAR2
,P_ASL_ASL_NAME				IN OUT VARCHAR2
,P_ASL_DESCRIPTION			OUT VARCHAR2
,P_ASL_COMMENTS   	        OUT	VARCHAR2
,P_ASL_CREATED_BY	        OUT	VARCHAR2
,P_ASL_DATE_CREATED	        OUT	VARCHAR2
,P_ASL_MODIFIED_BY	        OUT	VARCHAR2
,P_ASL_DATE_MODIFIED	    OUT	VARCHAR2)	;


PROCEDURE GET_CS(
P_RETURN_CODE	 				OUT VARCHAR2
, P_CS_CS_IDSEQ  				IN OUT VARCHAR2
, P_CS_PREFERRED_NAME 			IN OUT VARCHAR2
, P_CS_CONTE_IDSEQ  			IN OUT VARCHAR2
, P_CS_VERSION					IN OUT NUMBER
, P_CS_PREFERRED_DEFINITION		OUT VARCHAR2
, P_CS_LONG_NAME          		OUT VARCHAR2
, P_CS_ASL_NAME  				OUT VARCHAR2
, P_CS_LATEST_VERSION_IND		OUT VARCHAR2
, P_CS_CSTL_NAME 				OUT VARCHAR2
, P_CS_CMSL_NAME				OUT VARCHAR2
, P_CS_LABEL_TYPE_FLAG			OUT VARCHAR2
, P_CS_BEGIN_DATE				OUT VARCHAR2
, P_CS_END_DATE					OUT VARCHAR2
, P_CS_CHANGE_NOTE				OUT VARCHAR2
, P_CS_CREATED_BY				OUT VARCHAR2
, P_CS_DATE_CREATED				OUT VARCHAR2
, P_CS_MODIFIED_BY				OUT VARCHAR2
, P_CS_DATE_MODIFIED			OUT VARCHAR2
, P_CS_DELETED_IND				OUT VARCHAR2);


PROCEDURE GET_CSI(
P_RETURN_CODE	  				OUT VARCHAR2
,P_CSI_CSI_IDSEQ				IN OUT VARCHAR2
,P_CSI_NAME       				IN OUT VARCHAR2
,P_CSI_CSITL_NAME     			IN OUT VARCHAR2
,P_CSI_DESCRIPTION    			OUT VARCHAR2
,P_CSI_COMMENTS 				OUT VARCHAR2
,P_CSI_CREATED_BY        		OUT VARCHAR2
,P_CSI_DATE_CREATED   			OUT VARCHAR2
,P_CSI_MODIFIED_BY   			OUT VARCHAR2
,P_CSI_DATE_MODIFIED			OUT VARCHAR2);


PROCEDURE GET_CS_CSI(
P_RETURN_CODE		 			OUT VARCHAR2
,P_CSCSI_CS_CSI_IDSEQ			IN OUT VARCHAR2
,P_CSCSI_CS_IDSEQ		  		IN OUT VARCHAR2
,P_CSCSI_CSI_IDSEQ				IN OUT VARCHAR2
,P_CSCSI_P_CS_CSI_IDSEQ			IN OUT VARCHAR2
,P_CSCSI_LINK_CS_CSI_IDSEQ		OUT VARCHAR2
,P_CSCSI_LABEL					OUT VARCHAR2
,P_CSCSI_DISPLAY_ORDER			OUT NUMBER
,P_CSCSI_CREATED_BY				OUT VARCHAR2
,P_CSCSI_DATE_CREATED			OUT VARCHAR2
,P_CSCSI_MODIFIED_BY			OUT VARCHAR2
,P_CSCSI_DATE_MODIFIED			OUT VARCHAR2);


PROCEDURE GET_AC_CSI(
P_RETURN_CODE		 	 OUT VARCHAR2
,P_ACCSI_AC_CSI_IDSEQ 	 IN OUT VARCHAR2
,P_ACCSI_CS_CSI_IDSEQ 	 IN OUT VARCHAR2
,P_ACCSI_AC_IDSEQ	  	 IN OUT VARCHAR2
,P_ACCSI_CREATED_BY   	 OUT VARCHAR2
,P_ACCSI_DATE_CREATED 	 OUT VARCHAR2
,P_ACCSI_MODIFIED_BY  	 OUT VARCHAR2
,P_ACCSI_DATE_MODIFIED	 OUT VARCHAR2);


PROCEDURE GET_FORML(
P_RETURN_CODE	            OUT VARCHAR2
,P_FORML_FORML_NAME			IN OUT VARCHAR2
,P_FORML_DESCRIPTION		OUT VARCHAR2
,P_FORML_COMMENTS   	    OUT	VARCHAR2
,P_FORML_CREATED_BY	        OUT	VARCHAR2
,P_FORML_DATE_CREATED	    OUT	VARCHAR2
,P_FORML_MODIFIED_BY	    OUT	VARCHAR2
,P_FORML_DATE_MODIFIED	    OUT	VARCHAR2)	;

PROCEDURE GET_UOML(
P_RETURN_CODE	            OUT VARCHAR2
,P_UOML_UOML_NAME			IN OUT VARCHAR2
,P_UOML_DESCRIPTION			OUT VARCHAR2
,P_UOML_COMMENTS   	        OUT	VARCHAR2
,P_UOML_CREATED_BY	        OUT	VARCHAR2
,P_UOML_DATE_CREATED	    OUT	VARCHAR2
,P_UOML_MODIFIED_BY	        OUT	VARCHAR2
,P_UOML_DATE_MODIFIED	    OUT	VARCHAR2)	;

PROCEDURE GET_QCDL(
P_RETURN_CODE	        OUT VARCHAR2
,P_QCDL_QCDL_NAME		IN OUT VARCHAR2
,P_QCDL_DESCRIPTION		OUT VARCHAR2
,P_QCDL_COMMENTS   	    OUT	VARCHAR2
,P_QCDL_CREATED_BY	    OUT	VARCHAR2
,P_QCDL_DATE_CREATED	OUT	VARCHAR2
,P_QCDL_MODIFIED_BY	    OUT	VARCHAR2
,P_QCDL_DATE_MODIFIED	OUT	VARCHAR2);

PROCEDURE GET_CHAR_SET(
P_RETURN_CODE	            OUT VARCHAR2
,P_CHAR_SET_CHAR_SET_NAME	IN OUT VARCHAR2
,P_CHAR_SET_DESCRIPTION		OUT VARCHAR2
,P_CHAR_SET_CREATED_BY	    OUT	VARCHAR2
,P_CHAR_SET_DATE_CREATED	OUT	VARCHAR2
,P_CHAR_SET_MODIFIED_BY	    OUT	VARCHAR2
,P_CHAR_SET_DATE_MODIFIED	OUT	VARCHAR2);

PROCEDURE GET_DTL(
P_RETURN_CODE	            OUT VARCHAR2
,P_DTL_DTL_NAME			IN OUT VARCHAR2
,P_DTL_DESCRIPTION			OUT VARCHAR2
,P_DTL_COMMENTS   	        OUT	VARCHAR2);

PROCEDURE GET_DCTL(
P_RETURN_CODE	            OUT VARCHAR2
,P_DCTL_DCTL_NAME		IN OUT VARCHAR2
,P_DCTL_DESCRIPTION			OUT VARCHAR2
,P_DCTL_COMMENTS   	        OUT	VARCHAR2
,P_DCTL_CREATED_BY	        OUT	VARCHAR2
,P_DCTL_DATE_CREATED	    OUT	VARCHAR2
,P_DCTL_MODIFIED_BY	        OUT	VARCHAR2
,P_DCTL_DATE_MODIFIED	    OUT	VARCHAR2);

PROCEDURE GET_DETL(
P_RETURN_CODE	            OUT VARCHAR2
,P_DETL_DETL_NAME			IN OUT VARCHAR2
,P_DETL_DESCRIPTION			OUT VARCHAR2
,P_DETL_COMMENTS   	        OUT	VARCHAR2
,P_DETL_CREATED_BY	        OUT	VARCHAR2
,P_DETL_DATE_CREATED	    OUT	VARCHAR2
,P_DETL_MODIFIED_BY	        OUT	VARCHAR2
,P_DETL_DATE_MODIFIED	    OUT	VARCHAR2);


PROCEDURE GET_RL(
P_RETURN_CODE	        OUT VARCHAR2
,P_RL_RL_NAME				IN OUT VARCHAR2
,P_RL_DESCRIPTION		OUT VARCHAR2
,P_RL_COMMENTS   	    OUT	VARCHAR2
,P_RL_CREATED_BY	    OUT	VARCHAR2
,P_RL_DATE_CREATED	    OUT	VARCHAR2
,P_RL_MODIFIED_BY	    OUT	VARCHAR2
,P_RL_DATE_MODIFIED	OUT	VARCHAR2);

PROCEDURE GET_RD(
P_RETURN_CODE	        OUT VARCHAR2
,P_RD_RD_IDSEQ			IN OUT VARCHAR2
,P_RD_NAME				IN OUT VARCHAR2
,P_RD_DCTL_NAME			OUT VARCHAR2
,P_RD_AC_IDSEQ			OUT VARCHAR2
,P_RD_ACH_IDSEQ			OUT VARCHAR2
,P_RD_AR_IDSEQ			OUT VARCHAR2
,P_RD_ORG_IDSEQ			OUT VARCHAR2
,P_RD_RDTL_NAME			OUT VARCHAR2
,P_RD_DOC_TEXT			OUT VARCHAR2
,P_RD_URL				OUT VARCHAR2
,P_RD_CREATED_BY	    OUT	VARCHAR2
,P_RD_DATE_CREATED	    OUT	VARCHAR2
,P_RD_MODIFIED_BY	    OUT	VARCHAR2
,P_RD_DATE_MODIFIED		OUT	VARCHAR2);


PROCEDURE GET_DES(
P_RETURN_CODE	  	    OUT VARCHAR2
,P_DES_DESIG_IDSEQ			IN OUT VARCHAR2
,P_DES_AC_IDSEQ		   IN OUT VARCHAR2
,P_DES_NAME			   IN OUT VARCHAR2
,P_DES_DETL_NAME	   IN	OUT VARCHAR2
,P_DES_CONTE_IDSEQ	   IN OUT VARCHAR2
,P_DES_LAE_NAME			OUT VARCHAR2
,P_DES_CREATED_BY	    OUT	VARCHAR2
,P_DES_DATE_CREATED		OUT	VARCHAR2
,P_DES_MODIFIED_BY	    OUT	VARCHAR2
,P_DES_DATE_MODIFIED	OUT	VARCHAR2);

PROCEDURE GET_QC(
P_RETURN_CODE	 			OUT VARCHAR2
,P_QC_QC_IDSEQ				IN OUT VARCHAR2
,P_QC_PREFERRED_NAME		IN OUT VARCHAR2
,P_QC_CONTE_IDSEQ			IN OUT VARCHAR2
,P_QC_VERSION				IN OUT NUMBER
,P_QC_PREFERRED_DEFINITION	OUT VARCHAR2
,P_QC_LONG_NAME		 		OUT VARCHAR2
,P_QC_QTL_NAME				OUT VARCHAR2
,P_QC_ASL_NAME				OUT VARCHAR2
,P_QC_PROTO_IDSEQ			OUT VARCHAR2
,P_QC_DE_IDSEQ				OUT VARCHAR2
,P_QC_VP_IDSEQ				OUT VARCHAR2
,P_QC_QC_MATCH_IDSEQ		OUT VARCHAR2
,P_QC_MATCH_IND				OUT VARCHAR2
,P_QC_QC_IDENTIFIER			OUT VARCHAR2
,P_QC_QCDL_NAME             OUT VARCHAR2
,P_QC_LASTEST_VERSION_IND	OUT VARCHAR2
,P_QC_NEW_QC_IND			OUT VARCHAR2
,P_QC_HIGHLIGHT_IND			OUT VARCHAR2
,P_QC_SYSTEM_MSGS			OUT VARCHAR2
,P_QC_REVIEWER_FEEDBACK_EXT	OUT VARCHAR2
,P_QC_REVIEWER_FEEDBACK_ACT OUT VARCHAR2
,P_QC_REVIEWER_FEEDBACK_INT	OUT VARCHAR2
,P_QC_REVIEWED_BY			OUT VARCHAR2
,P_QC_REVIEWED_DATE			OUT VARCHAR2
,P_QC_APPROVED_BY			OUT VARCHAR2
,P_QC_APPROVED_DATE			OUT VARCHAR2
,P_QC_CDE_DICTIONARY_ID		OUT NUMBER
,P_QC_BEGIN_DATE			OUT VARCHAR2
,P_QC_END_DATE				OUT VARCHAR2
,P_QC_CHANGE_NOTE			OUT VARCHAR2
,P_QC_SUB_LONG_NAME			OUT VARCHAR2
,P_QC_GROUP_COMMENTS		OUT VARCHAR2
,P_QC_CREATED_BY			OUT VARCHAR2
,P_QC_DATE_CREATED			OUT VARCHAR2
,P_QC_MODIFIED_BY			OUT VARCHAR2
,P_QC_DATE_MODIFIED			OUT VARCHAR2
,P_QC_DELETED_IND			OUT VARCHAR2);


PROCEDURE GET_SOURCE(
P_RETURN_CODE	            OUT VARCHAR2
,P_SRC_SRC_NAME				IN OUT VARCHAR2
,P_SRC_DESCRIPTION			OUT VARCHAR2
,P_SRC_CREATED_BY	        OUT	VARCHAR2
,P_SRC_DATE_CREATED	    	OUT	VARCHAR2
,P_SRC_MODIFIED_BY	        OUT	VARCHAR2
,P_SRC_DATE_MODIFIED	    OUT	VARCHAR2);

PROCEDURE GET_QTL(
P_RETURN_CODE	            OUT VARCHAR2
,P_QTL_QTL_NAME				IN OUT VARCHAR2
,P_QTL_DESCRIPTION			OUT VARCHAR2
,P_QTL_CREATED_BY	        OUT	VARCHAR2
,P_QTL_DATE_CREATED	    	OUT	VARCHAR2
,P_QTL_MODIFIED_BY	        OUT	VARCHAR2
,P_QTL_DATE_MODIFIED	    OUT	VARCHAR2);


PROCEDURE GET_TSTL(
P_RETURN_CODE	            OUT VARCHAR2
,P_TSTL_TSTL_NAME			IN OUT VARCHAR2
,P_TSTL_DESCRIPTION			OUT VARCHAR2
,P_TSTL_CREATED_BY	        OUT	VARCHAR2
,P_TSTL_DATE_CREATED	    OUT	VARCHAR2
,P_TSTL_MODIFIED_BY	        OUT	VARCHAR2
,P_TSTL_DATE_MODIFIED	    OUT	VARCHAR2);


PROCEDURE GET_TS(
P_RETURN_CODE	            OUT VARCHAR2
,P_TS_TS_IDSEQ				IN OUT VARCHAR2
,P_TS_QC_IDSEQ				OUT VARCHAR2
,P_TS_TSTL_NAME				OUT VARCHAR2
,P_TS_TS_TEXT				OUT VARCHAR2
,P_TS_TS_SEQ				OUT NUMBER
,P_TS_CREATED_BY	        OUT	VARCHAR2
,P_TS_DATE_CREATED	    	OUT	VARCHAR2
,P_TS_MODIFIED_BY	        OUT	VARCHAR2
,P_TS_DATE_MODIFIED	    	OUT	VARCHAR2);

PROCEDURE GET_AC_SRC(
P_RETURN_CODE 		 	OUT VARCHAR2
,P_ACSRC_ACS_IDSEQ	IN OUT VARCHAR2
,P_ACSRC_AC_IDSEQ		IN OUT VARCHAR2
,P_ACSRC_SRC_NAME		IN OUT VARCHAR2
,P_ACSRC_DATE_SUBMITTED	   OUT VARCHAR2
,P_ACSRC_CREATED_BY		   OUT VARCHAR2
,P_ACSRC_DATE_CREATED	   OUT VARCHAR2
,P_ACSRC_MODIFIED_BY	   OUT VARCHAR2
,P_ACSRC_DATE_MODIFIED	   OUT VARCHAR2);

PROCEDURE GET_REL(
P_RETURN_CODE	            OUT VARCHAR2
,P_REL_TABLE	            IN VARCHAR2
,P_REL_REL_IDSEQ	    IN OUT VARCHAR2
,P_REL_P_IDSEQ	            IN OUT VARCHAR2
,P_REL_C_IDSEQ	            IN OUT VARCHAR2
,P_REL_RL_NAME		    IN OUT VARCHAR2
,P_REL_DISPLAY_ORDER	       OUT NUMBER
,P_REL_CREATED_BY	        OUT	VARCHAR2
,P_REL_DATE_CREATED	        OUT	VARCHAR2
,P_REL_MODIFIED_BY	        OUT	VARCHAR2
,P_REL_DATE_MODIFIED	        OUT	VARCHAR2);

PROCEDURE GET_PROTOCOL(
P_RETURN_CODE	 				OUT VARCHAR2
, P_PROTO_PROTO_IDSEQ			IN OUT VARCHAR2
, P_PROTO_PREFERRED_NAME		IN OUT VARCHAR2
, P_PROTO_CONTE_IDSEQ		  	IN OUT VARCHAR2
, P_PROTO_VERSION				IN OUT NUMBER
, P_PROTO_PREFERRED_DEFINITION	OUT VARCHAR2
, P_PROTO_LONG_NAME				OUT VARCHAR2
, P_PROTO_ASL_NAME				OUT VARCHAR2
, P_PROTO_LATEST_VERSION_IND	OUT VARCHAR2
, P_PROTO_PROTOCOL_ID			OUT VARCHAR2
, P_PROTO_TYPE					OUT VARCHAR2
, P_PROTO_PHASE			 		OUT VARCHAR2
, P_PROTO_LEAD_ORG				OUT VARCHAR2
, P_PROTO_CHANGE_TYPE			OUT VARCHAR2
, P_PROTO_CHANGE_NUMBER			OUT NUMBER
, P_PROTO_REVIEWED_BY			OUT VARCHAR2
, P_PROTO_REVIEWED_DATE			OUT VARCHAR2
, P_PROTO_APPROVED_BY			OUT VARCHAR2
, P_PROTO_APPROVED_DATE			OUT VARCHAR2
, P_PROTO_BEGIN_DATE			OUT VARCHAR2
, P_PROTO_END_DATE				OUT VARCHAR2
, P_PROTO_CHANGE_NOTE			OUT VARCHAR2
, P_PROTO_CREATED_BY			OUT VARCHAR2
, P_PROTO_DATE_CREATED			OUT VARCHAR2
, P_PROTO_MODIFIED_BY			OUT VARCHAR2
, P_PROTO_DATE_MODIFIED			OUT VARCHAR2
, P_PROTO_DELETED_IND			OUT VARCHAR2);


PROCEDURE GET_VP_SRC(
P_RETURN_CODE 		 	OUT VARCHAR2
,P_VPSRC_VPS_IDSEQ	IN OUT VARCHAR2
,P_VPSRC_VP_IDSEQ		IN OUT VARCHAR2
,P_VPSRC_SRC_NAME		IN OUT VARCHAR2
,P_VPSRC_DATE_SUBMITTED	   OUT VARCHAR2
,P_VPSRC_CREATED_BY		   OUT VARCHAR2
,P_VPSRC_DATE_CREATED	   OUT VARCHAR2
,P_VPSRC_MODIFIED_BY	   OUT VARCHAR2
,P_VPSRC_DATE_MODIFIED	   OUT VARCHAR2);

PROCEDURE GET_PV(
P_RETURN_CODE	 			OUT VARCHAR2
,P_PV_PV_IDSEQ              IN OUT VARCHAR2
,P_PV_VALUE                 IN OUT VARCHAR2
,P_PV_VM_IDSEQ         IN OUT VARCHAR2
,P_PV_MEANING_DESCRIPTION   OUT VARCHAR2
,P_PV_HIGH_VALUE_NUM        OUT NUMBER
,P_PV_LOW_VALUE_NUM			OUT NUMBER
,P_PV_BEGIN_DATE            OUT VARCHAR2
,P_PV_END_DATE              OUT VARCHAR2
,P_PV_CREATED_BY            OUT VARCHAR2
,P_PV_DATE_CREATED          OUT VARCHAR2
,P_PV_MODIFIED_BY           OUT VARCHAR2
,P_PV_DATE_MODIFIED         OUT VARCHAR2);


END Sbrext_Get_Row;
 
 
/
