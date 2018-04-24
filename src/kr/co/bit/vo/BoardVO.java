package kr.co.bit.vo;

public class BoardVO {
    private String no;
    private String title;
    private String content;
    private String hit;
    private String reg_date;
    private String user_no;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardVO() {
    }

    public BoardVO(String no, String title, String content, String hit, String reg_date, String user_no) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.reg_date = reg_date;
        this.user_no = user_no;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "no='" + no + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", hit='" + hit + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", user_no='" + user_no + '\'' +
                '}';
    }
}
