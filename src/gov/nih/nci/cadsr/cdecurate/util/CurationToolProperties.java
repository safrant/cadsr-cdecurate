package gov.nih.nci.cadsr.cdecurate.util;

import java.io.IOException;  
import java.io.InputStream;  
import java.io.Serializable;
import java.util.Properties;  
  
/** 
 * class to read a properties file 
 * @author hveerla
 */  
public class CurationToolProperties implements Serializable {  
  
   
	InputStream is = null;
    private Properties props = null;
    private static CurationToolProperties CurationToolProperties = null;
    
    static {
        try {
            if (CurationToolProperties.CurationToolProperties == null) {
                CurationToolProperties.CurationToolProperties = new CurationToolProperties();
            }
        } catch (Exception e) {
            System.out.println("Error in getFactory() : " + e);
        }
    }
    
   public CurationToolProperties() throws IOException {
        props = loadProps();
    }
    
    public static CurationToolProperties getFactory() {        
        return CurationToolProperties.CurationToolProperties;
    }
    
   private Properties loadProps() throws IOException {
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            is = cl.getResourceAsStream("curationtool.properties");
            props = new Properties();
            props.load(is);
        } catch (Exception e) {
            throw new IOException("Unable to get properties in loadProps() : " + e);
        } finally {
            if (is != null) is.close();
        }
        
        return props;
    }
   public String getProperty(String key) {
        return props.getProperty(key);
    }
}  