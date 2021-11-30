package kr.ac.mokwon.gongcafe;

public class Data {

    private String cafeName;
    private String cafeInfo;
    private int resId;

    public String getCafeName(){
        return cafeName;
    }

    public void setCafeName(String cafeName){
        this.cafeName = cafeName;
    }

    public String getCafeInfo(){
        return cafeInfo;
    }

    public void setCafeInfo(String cafeInfo){
        this.cafeInfo = cafeInfo;
    }

    public int getResId(){
        return resId;
    }

    public void setResId(int resId){
        this.resId = resId;
    }
}