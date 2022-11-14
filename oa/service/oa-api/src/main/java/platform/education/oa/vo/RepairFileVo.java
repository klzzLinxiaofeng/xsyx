package platform.education.oa.vo;
import platform.education.oa.model.RepairFile;
/**
 * RepairFile
 * @author AutoCreate
 *
 */
public class RepairFileVo extends RepairFile {
	private static final long serialVersionUID = 1L;
	private String realFileName;
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
}