package contentprovider.day.com.cn.day714_stickylistheaderslistview;

/**
 * Created by ann on 2016/7/15.
 */
public class Group {


    private int groupId;
    private String groupName;


    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
