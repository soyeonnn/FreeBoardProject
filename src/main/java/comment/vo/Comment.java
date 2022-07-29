package comment.vo;

public class Comment {
	
	private int cid;
	private int pid;
	private String cwriter;
	private String ccontent;
	
	public Comment() {
		
	}

	public Comment(int cid, int pid, String cwriter, String ccontent) {
		super();
		this.cid = cid;
		this.pid = pid;
		this.cwriter = cwriter;
		this.ccontent = ccontent;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getCwriter() {
		return cwriter;
	}

	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
}
