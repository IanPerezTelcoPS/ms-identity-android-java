package model;

import android.support.v4.media.RatingCompat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilesResponseModel {
    public @SerializedName("value") List<FilesItem> values;

    public List<FilesItem> getValues() {
        return values;
    }

    public void setValues(List<FilesItem> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "FilesResponseModel{" +
                "values=" + values +
                '}';
    }
}
