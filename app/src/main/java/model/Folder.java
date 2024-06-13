package model;

import com.google.gson.annotations.SerializedName;

public class Folder {

    public @SerializedName("childCount") int childCount;
    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "childCount=" + childCount +
                '}';
    }
}
