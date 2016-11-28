package study.com.cn.day809_module.bean;

/**
 * Created by ann on 2016/8/9.
 */
public class MDDrawerMenu {

    public String name;
    public int img;

    @Override
    public String toString() {
        return "MDDrawerMenu{" +
                "name='" + name + '\'' +
                ", img=" + img +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
