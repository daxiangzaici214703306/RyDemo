package com.hsns.rydemo.model;

public class ModelHomeEntrance {

    private String name;//姓名
    private String age;//年龄
    private String height;//身高
    private String weight;//体重
    private String phone;//电话

    public ModelHomeEntrance(String name, String age, String height, String weight, String phone) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 判断是否存在对应的键值
     * @param value 键值
     */
    public boolean hasObject(String value){
        if(value!=null){
            if(value.equals("name")||value.equals("age")||value.equals("height")||value.equals("weight")||value.equals("phone")){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取对应的值
     * @param value 属性
     * @return  获取的结果
     */
    public String getValue(String value){
        String result="";
        switch (value){
            case "name":
                result=getName();
                break;
            case "age":
                result=getAge();
                break;
            case "height":
                result=getHeight();
                break;
            case "weight":
                result=getWeight();
                break;
            case "phone":
                result=getPhone();
                break;
        }
        return result;
    }
}
