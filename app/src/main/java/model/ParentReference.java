package model;

import com.google.gson.annotations.SerializedName;

public class ParentReference {
    public @SerializedName("driveType") String driveType;
    public @SerializedName("driveId") String driveId;
    public @SerializedName("id") String id;
    public @SerializedName("name") String name;
    public @SerializedName("path") String path;
    public @SerializedName("siteId") String siteId;

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getDriveId() {
        return driveId;
    }

    public void setDriveId(String driveId) {
        this.driveId = driveId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        return "ParentReference{" +
                "driveType='" + driveType + '\'' +
                ", driveId='" + driveId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", siteId='" + siteId + '\'' +
                '}';
    }
}
