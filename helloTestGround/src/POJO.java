import java.util.List;

public class POJO {


	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public List<String> getLb() {
		return lb;
	}
	public void setLb(List<String> lb) {
		this.lb = lb;
	}
	
	@Override
	public String toString() {
		return "POJO [a=" + a + ", lb=" + lb + "]";
	}
	private String a;
	private List<String> lb;
	
}
