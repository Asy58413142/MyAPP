package contentprovider.day.com.cn.day714_stickylistheaderslistview;

/**
 * Created by ann on 2016/7/15.
 */
public class Item {

    private int groupId;
    private String itemName;
    private String itemInfo;


    public Item(int groupId, String itemName, String itemInfo) {
        this.groupId = groupId;
        this.itemName = itemName;
        this.itemInfo = itemInfo;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }
}
