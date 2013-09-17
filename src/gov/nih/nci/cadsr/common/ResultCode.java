/*L
 * Copyright ScenPro Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-cdecurate/LICENSE.txt for details.
 */

package gov.nih.nci.cadsr.common;

public enum ResultCode {
	INVALID_LOGIN {
		@Override
		public String userMessage() {
			return "Invalid Login ID or Password. Please try again.";
		}
	},
	EXPIRED {
		@Override
		public String userMessage() {
			return "Your account has expired. You must change your password.";
		}
	},
	NOT_EXPIRED {
		@Override
		public String userMessage() {
			return "(Your account has not yet expired.)";
		}
	},
	LOCKED_OUT {
		@Override
		public String userMessage() {
			return "Your account has been temporarily locked due to invalid logins. Please wait one hour and try again.";
		}
	},	
	ACCOUNT_UNLOCKED{		//CADSRPASSW-55
		@Override
		public String userMessage() {
			return "Account unlocked.";
		}
	},
	PASSWORD_CHANGED{
		@Override
		public String userMessage() {
			return "Password changed.";
		}
	},
	PASSWORD_REUSED{
		@Override
		public String userMessage() {
			return "You are trying to reuse a password that you have used recently. Please try another password.";	//CADSRPASSW-26
		}
	},
	INVALID_OLD_PASSWORD{
		@Override
		public String userMessage() {
			return "Invalid Login ID or Password. Please try again.";
		}
	},
	PASSWORD_TOO_LONG{
		@Override
		public String userMessage() {
			return "Password may not be longer than 30 characters.";
		}
	},
	PASSWORD_LENGTH {
		@Override
		public String userMessage() {
			return "Password too short.";
		}
	},
	UNSUPPORTED_CHARACTERS {
		@Override
		public String userMessage() {
			return "Password includes unsupported characters.";
		}
	},
	NOT_ENOUGH_GROUPS {
		@Override
		public String userMessage() {
			return "Password doesn't include characters from enough groups.";
		}
	},
	UNKNOWN_ERROR {
		@Override
		public String userMessage() {
			return "Unknown or system error.";
		}
	},
	TOO_RECENT {
		@Override
		public String userMessage() {
			return "Password changed too recently.";
		}
	},
	START_NOT_LETTER {
		@Override
		public String userMessage() {
			return "Your password must start with letter.";
		}
	},
	MISSING_OR_INVALID {
		@Override
		public String userMessage() {
			return "Missing or invalid password(s).";
		}
	},
	UNKNOWN_CUSTOM{
		@Override
		public String userMessage() {
			return "Unknown or system error.";
		}
	},
	NONE {
		@Override
		public String userMessage() {
			return "";
		}		
	};

	public abstract String userMessage();
 
}

