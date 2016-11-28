package contentprovider.day.com.cn.day704_messager_clientservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ann on 2016/7/4.
 *
 * 序列化和反序列化的参数顺序要保持一致
 */
public class Student implements Parcelable {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Student(Parcel in) {
        in.readInt();
        in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        //反序列化
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    //序列化
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
    }
}
