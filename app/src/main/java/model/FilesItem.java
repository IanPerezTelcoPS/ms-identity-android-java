package model;

import com.google.gson.annotations.SerializedName;

public class FilesItem {
    public @SerializedName("name") String name;
    public @SerializedName("webUrl") String webUrl;
    public @SerializedName("size") int size;
    public @SerializedName("parentReference") ParentReference parentReference;
    public @SerializedName("folder") Folder folder;
    public @SerializedName("id") String id;

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

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ParentReference getParentReference() {
        return parentReference;
    }

    public void setParentReference(ParentReference parentReference) {
        this.parentReference = parentReference;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @Override
    public String toString() {
        return "FilesResponseModel{" +
                "name='" + name + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", size=" + size +
                ", parentReference=" + parentReference +
                ", folder=" + folder +
                '}';
    }
}
