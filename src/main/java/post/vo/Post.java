package post.vo;

public class Post {

	private int pid;
	private String ptitle;
	private String pwriter;
	private String pcontent;
	private String pdate;
	private int plike;
	private int pview;
	private int pcomment;
	
	public Post() {
		
	}

	public Post(int pid, String ptitle, String pwriter, String pcontent, String pdate, int plike, int pview,
			int pcomment) {
		super();
		this.pid = pid;
		this.ptitle = ptitle;
		this.pwriter = pwriter;
		this.pcontent = pcontent;
		this.pdate = pdate;
		this.plike = plike;
		this.pview = pview;
		this.pcomment = pcomment;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public String getPwriter() {
		return pwriter;
	}

	public void setPwriter(String pwriter) {
		this.pwriter = pwriter;
	}

	public String getPcontent() {
		return pcontent;
	}

	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public int getPlike() {
		return plike;
	}

	public void setPlike(int plike) {
		this.plike = plike;
	}

	public int getPview() {
		return pview;
	}

	public void setPview(int pview) {
		this.pview = pview;
	}

	public int getPcomment() {
		return pcomment;
	}

	public void setPcomment(int pcomment) {
		this.pcomment = pcomment;
	}
 }
