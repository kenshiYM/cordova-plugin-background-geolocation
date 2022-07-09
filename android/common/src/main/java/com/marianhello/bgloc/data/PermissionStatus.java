package com.marianhello.bgloc.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.location.DetectedActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by heartmon on 24.05.2021
 */

public class PermissionStatus implements Parcelable {
    private String type;
    private boolean isEnabled;
    private boolean doNotAskAgain;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeByte(this.isEnabled ? (byte) 1 : (byte) 0);
        dest.writeByte(this.doNotAskAgain ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.type = source.readString();
        this.isEnabled = source.readByte() != 0;
        this.doNotAskAgain = source.readByte() != 0;
    }

    public PermissionStatus(String type, boolean isEnabled) {
        this(type, isEnabled, false);
    }

    public PermissionStatus(String type, boolean isEnabled, boolean doNotAskAgain) {
        this.type = type;
        this.isEnabled = isEnabled;
        this.doNotAskAgain = doNotAskAgain;
    }

    protected PermissionStatus(Parcel in) {
        this.type = in.readString();
        this.isEnabled = in.readByte() != 0;
        this.doNotAskAgain = in.readByte() != 0;
    }

    /**
     * Returns location as JSON object.
     * @throws JSONException
     */
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("isEnabled", isEnabled);
        json.put("doNotAskAgain", doNotAskAgain);
        return json;
    }

    public static final Creator<PermissionStatus> CREATOR = new Creator<PermissionStatus>() {
        @Override
        public PermissionStatus createFromParcel(Parcel source) {
            return new PermissionStatus(source);
        }

        @Override
        public PermissionStatus[] newArray(int size) {
            return new PermissionStatus[size];
        }
    };
}
