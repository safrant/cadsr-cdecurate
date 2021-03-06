/*L
  Copyright ScenPro Inc, SAIC-F

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cadsr-cdecurate/LICENSE.txt for details.
L*/

--Please run with account SBR
--ACs for KEARNSD
select '************** FOR KEARNSD **************' from dual;
select dec.dec_id, c.name, dec.date_created from sbr.data_element_concepts_view dec, sbr.contexts c
--select 'DEC_affected_for_KEARNSD = ' || count(*) from sbr.data_element_concepts_view dec, sbr.contexts c
where dec.conte_idseq = c.conte_idseq
and dec.CONTE_IDSEQ = (select CONTE_IDSEQ from SBR.CONTEXTS where name = 'SWOG')
and (upper(dec.created_by) = 'KEARNSD' or lower(dec.created_by) = 'KEARNSD')
and trunc(dec.date_created) >= to_date('2011-12-01', 'YYYY-MM-DD')
and trunc(dec.date_created) <= to_date('2013-06-28', 'YYYY-MM-DD');

select de.cde_id, c.name, de.date_created from sbr.data_elements de, sbr.contexts c
--select 'DE_affected_for_KEARNSD = ' || count(*) from sbr.data_elements de, sbr.contexts c
where de.conte_idseq = c.conte_idseq
and de.CONTE_IDSEQ = (select CONTE_IDSEQ from SBR.CONTEXTS where name = 'SWOG')
and (upper(de.created_by) = 'KEARNSD' or lower(de.created_by) = 'KEARNSD')
and trunc(de.date_created) >= to_date('2011-12-01', 'YYYY-MM-DD')
and trunc(de.date_created) <= to_date('2013-06-28', 'YYYY-MM-DD');

select vd.vd_id, c.name, vd.date_created from sbr.value_domains vd, sbr.contexts c
--select 'VD_affected_for_KEARNSD = ' || count(*) from sbr.value_domains vd, sbr.contexts c
where vd.conte_idseq = c.conte_idseq
and vd.CONTE_IDSEQ = (select CONTE_IDSEQ from SBR.CONTEXTS where name = 'SWOG')
and (upper(vd.created_by) = 'KEARNSD' or lower(vd.created_by) = 'KEARNSD')
and trunc(vd.date_created) >= to_date('2011-12-01', 'YYYY-MM-DD')
and trunc(vd.date_created) <= to_date('2013-06-28', 'YYYY-MM-DD');

--ACs for SMITHA
select '************** FOR SMITHA **************' from dual;
select dec.dec_id, c.name, dec.date_created from sbr.data_element_concepts_view dec, sbr.contexts c
--select 'DEC_affected_for_SMITHA = ' || count(*) from sbr.data_element_concepts_view dec, sbr.contexts c
where dec.conte_idseq = c.conte_idseq
and dec.CONTE_IDSEQ = (select CONTE_IDSEQ from SBR.CONTEXTS where name = 'SWOG')
and (upper(dec.created_by) = 'SMITHA' or lower(dec.created_by) = 'SMITHA')
and trunc(dec.date_created) >= to_date('2011-12-01', 'YYYY-MM-DD')
and trunc(dec.date_created) <= to_date('2013-06-28', 'YYYY-MM-DD');

select de.cde_id, c.name, de.date_created from sbr.data_elements de, sbr.contexts c
--select 'DE_affected_for_SMITHA = ' || count(*) from sbr.data_elements de, sbr.contexts c
where de.conte_idseq = c.conte_idseq
and de.CONTE_IDSEQ = (select CONTE_IDSEQ from SBR.CONTEXTS where name = 'SWOG')
and (upper(de.created_by) = 'SMITHA' or lower(de.created_by) = 'SMITHA')
and trunc(de.date_created) >= to_date('2011-12-01', 'YYYY-MM-DD')
and trunc(de.date_created) <= to_date('2013-06-28', 'YYYY-MM-DD');

select vd.vd_id, c.name, vd.date_created from sbr.value_domains vd, sbr.contexts c
--select 'VD_affected_for_SMITHA = ' || count(*) from sbr.value_domains vd, sbr.contexts c
where vd.conte_idseq = c.conte_idseq
and vd.CONTE_IDSEQ = (select CONTE_IDSEQ from SBR.CONTEXTS where name = 'SWOG')
and (upper(vd.created_by) = 'SMITHA' or lower(vd.created_by) = 'SMITHA')
and trunc(vd.date_created) >= to_date('2011-12-01', 'YYYY-MM-DD')
and trunc(vd.date_created) <= to_date('2013-06-28', 'YYYY-MM-DD');

--ACs for SCOTTM
select '************** FOR SCOTTM **************' from dual;
select dec.dec_id, c.name, dec.date_created from sbr.data_element_concepts_view dec, sbr.contexts c
--select 'DEC_affected_for_SCOTTM = ' || count(*) from sbr.data_element_concepts_view dec, sbr.contexts c
where dec.conte_idseq = c.conte_idseq
and dec.CONTE_IDSEQ = (select CONTE_IDSEQ from SBR.CONTEXTS where name = 'SWOG')
and (upper(dec.created_by) = 'SCOTTM' or lower(dec.created_by) = 'SCOTTM')
and trunc(dec.date_created) >= to_date('2011-12-01', 'YYYY-MM-DD')
and trunc(dec.date_created) <= to_date('2013-06-28', 'YYYY-MM-DD');

select de.cde_id, c.name, de.date_created from sbr.data_elements de, sbr.contexts c
--select 'DE_affected_for_SCOTTM = ' || count(*) from sbr.data_elements de, sbr.contexts c
where de.conte_idseq = c.conte_idseq
and de.CONTE_IDSEQ = (select CONTE_IDSEQ from SBR.CONTEXTS where name = 'SWOG')
and (upper(de.created_by) = 'SCOTTM' or lower(de.created_by) = 'SCOTTM')
and trunc(de.date_created) >= to_date('2011-12-01', 'YYYY-MM-DD')
and trunc(de.date_created) <= to_date('2013-06-28', 'YYYY-MM-DD');

select vd.vd_id, c.name, vd.date_created from sbr.value_domains vd, sbr.contexts c
--select 'VD_affected_for_SCOTTM = ' || count(*) from sbr.value_domains vd, sbr.contexts c
where vd.conte_idseq = c.conte_idseq
and vd.CONTE_IDSEQ = (select CONTE_IDSEQ from SBR.CONTEXTS where name = 'SWOG')
and (upper(vd.created_by) = 'SCOTTM' or lower(vd.created_by) = 'SCOTTM')
and trunc(vd.date_created) >= to_date('2011-12-01', 'YYYY-MM-DD')
and trunc(vd.date_created) <= to_date('2013-06-28', 'YYYY-MM-DD');
