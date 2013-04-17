package gov.nih.nci.cadsr.cdecurate.dto;

import org.apache.commons.lang3.StringUtils;

public class AdministeredComponentBean {
	private String longName;
	private String publicId;
	private String version;
	private String workflowStatus;
	private String publicIdVersion;

	public AdministeredComponentBean() {
	}

	public AdministeredComponentBean(String longName, String publicId,
			String version, String workflowStatus) {
		super();
		this.longName = longName;
		this.publicId = publicId;
		this.version = version;
		this.workflowStatus = workflowStatus;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	public String getPublicIdVersion() {
		if (StringUtils.isEmpty(getPublicId())
				&& StringUtils.isEmpty(getVersion())) {
			return "";
		}
		publicIdVersion = getPublicId() + "v" + getVersion();
		return publicIdVersion;
	}
}
