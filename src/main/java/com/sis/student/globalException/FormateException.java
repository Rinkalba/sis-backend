package com.sis.student.globalException;


public class FormateException {
	
		private String exceptionManager;
		private String errorMsg;
		
		
		public String getExceptionManager() {
			return exceptionManager;
		}


		public void setExceptionManager(String exceptionManager) {
			this.exceptionManager = exceptionManager;
		}


		public String getErrorMsg() {
			return errorMsg;
		}


		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}


		public FormateException() {
			super();
			// TODO Auto-generated constructor stub
		}


		public FormateException(String exe,String errorMsg) {
			// TODO Auto-generated constructor stub
			this.exceptionManager=exe;
			this.errorMsg=errorMsg;
			
		}
}
