
package com.example.maks.city_test.json_objects;


import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class City implements Parcelable {

    public Integer id;
    public String name;
    public String nameEn;
    public String nameUa;


    protected City(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        nameEn = in.readString();
        nameUa = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(nameEn);
        dest.writeString(nameUa);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
